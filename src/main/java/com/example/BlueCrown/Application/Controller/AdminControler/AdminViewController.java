/// //////////////////////////////////////////////////////
/// ///////////////////////////////////////////////////
/////////////ALl User RestControllers/////////////////////
/// //////////////////////////////////////////////////////
/// ///////////////////////////////////////////////////
package com.example.BlueCrown.Application.Controller.AdminControler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BlueCrown.Application.Model.UserModel.User;
import com.example.BlueCrown.Application.Utility.CurrentUser;
import com.example.BlueCrown.Application.service.UserServices.UserService;


/*
 * Controler for Admins
 */


@Controller // Converts all responses to JSON
@RequestMapping({"/Admin", "/User","/Classroom"})
public class AdminViewController {
    @Autowired
	 private UserService service;
   @Autowired
   private CurrentUser currentUser;

     // Adding new user
     @GetMapping("/auth")
     public String LoginRegister(){
       return "LoginRegister";
     }
    
    
      @PreAuthorize("hasRole('Admin','User')")
      @GetMapping("/dashboard")
      public String dashboard(){
       User user = currentUser.getCurrentUser();
         if(user.getUserType().equals("Admin")){
              return"dashboard";
        }else{
           return"UserDashboard";
        }
      
        }
        @PreAuthorize("hasAnyRole('Admin','User')")
        @GetMapping("/profile")
        public String getMethodName() throws Exception {
            return"profile";
        }
        @PreAuthorize("hasAnyRole('Admin','User')")
        @GetMapping("{id}/Notesview")
        public String notesview(@PathVariable("id") String classId,Model model) {
          model.addAttribute("joinCode", classId);
            return "Notesview";
        }
        @PreAuthorize("hasAnyRole('Admin')")
        @GetMapping("/CreateClassroom")
        public String createClassroom() {
            return "CreateClassroom";
        }
        @PreAuthorize("hasAnyRole('Admin')")
        @GetMapping("/UpdateClassroom/{joinCode}")
        public String UpdateClassroom(@PathVariable("joinCode") String joincode,Model model) {
          model.addAttribute("joinCode", joincode);
            return "UpdateClassroom";
        }
        @PreAuthorize("hasAnyRole('User')")
         @GetMapping("/JoinClassroom")
        public String SearchClassroom() {
            return "SearchClassroom";
        }
        @PreAuthorize("hasAnyRole('Admin')")
         @GetMapping("/{Code}/UploadNotes")
        public String UploadNotes(@PathVariable("Code") String code,Model model) {
          model.addAttribute("Code",code);
            return "UploadNotes";
        }
     
        
        
              
    } 
    

    


   