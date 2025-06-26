/// //////////////////////////////////////////////////////
/// ///////////////////////////////////////////////////
/////////////ALl Admin RestControllers/////////////////////
/// //////////////////////////////////////////////////////
/// ///////////////////////////////////////////////////
package com.example.BlueCrown.Application.Controller.AdminControler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BlueCrown.Application.Exceptions.AdminAleradyExist;
import com.example.BlueCrown.Application.Model.AdminModel.Admin;
import com.example.BlueCrown.Application.Utility.User;
import com.example.BlueCrown.Application.service.AdminServices.AdminService;


/*
 * Controler for Admins
 */


@Controller // Converts all responses to JSON
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
	 private AdminService service;
   @Autowired
   private User user;

    // Adding new user
     @PostMapping("/auth")
     public String AddUser(@ModelAttribute("Admin") Admin admin,Model model){
        try {
          service.saveAdmin(admin);
        } catch (AdminAleradyExist e) {
          // TODO Auto-generated catch block
          System.out.println(e);
          model.addAttribute("Message",e.getMessage());
 model.addAttribute("admin", admin); 
            return "LoginRegister";
        }
         model.addAttribute("admin",new Admin()); 
        return "LoginRegister";
     }
    
      @GetMapping("/auth")
      public String login(Model model){
        
          model.addAttribute("admin", new Admin());
        return "LoginRegister";
      }
     //check Admin exist or not
      @GetMapping("/dashboard")
      public String dashboard(Model model){
       Admin admin=user.getCurrentUser();
      model.addAttribute("Classroom_List", admin.getClassrooms());
    System.out.println(admin);
        return"dashboard";
        }
        @GetMapping("/profile")
        public String getMethodName(Model model) throws Exception {
         
            Admin admin=user.getCurrentUser();
        
    
            model.addAttribute("Username",admin.getUsername());
            model.addAttribute("Email",admin.getEmail());
            model.addAttribute("classrooms", admin.getClassrooms().size());
          
             return "profile";
        }

        @GetMapping("/Notesview/{id}")
        public String notesview(@PathVariable("id") String classId,Model model) {
          model.addAttribute("ClassId", classId);
            return "Notesview";
        }
        @GetMapping("/CreateClassroom")
        public String createClassroom() {
            return "CreateClassroom";
        }
        @GetMapping("/temp")
        public String getMethodName() {
            return"temp";
        }
        
        
        
              
    } 
    

    


   