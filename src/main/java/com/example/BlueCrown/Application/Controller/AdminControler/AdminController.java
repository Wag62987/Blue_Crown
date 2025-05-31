/// //////////////////////////////////////////////////////
/// ///////////////////////////////////////////////////
/////////////ALl Admin RestControllers/////////////////////
/// //////////////////////////////////////////////////////
/// ///////////////////////////////////////////////////
package com.example.BlueCrown.Application.Controller.AdminControler;
import com.example.BlueCrown.Application.service.AdminServices.AdminService;

import org.springframework.ui.Model;
import com.example.BlueCrown.Application.Model.AdminModel.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


/*
 * Controler for Admins
 */

@CrossOrigin(origins = "*")
@Controller // Converts all responses to JSON
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
	 private AdminService service;

    // Adding new user
     @PostMapping
     ResponseEntity<String> NewUser(@RequestBody Admin admin){
        service.saveAdmin(admin);
        return new ResponseEntity<>("Added",HttpStatus.CREATED);
     }

     //check Admin exist or not
     @GetMapping("/dashboard")
     public String dashboard(Model model){
      String username=SecurityContextHolder.getContext().getAuthentication().getName();
      Admin admin=service.getInfoByUsername(username);
      System.out.println(admin.getClassrooms());
      model.addAttribute("Classroom_List", admin.getClassrooms());
        return"dashboard";
        }
        @GetMapping("/profile")
        public String getMethodName(Model model) {
         
          String username= SecurityContextHolder.getContext().getAuthentication().getName();
            Admin admin= service.getInfoByUsername(username) ;
            model.addAttribute("Username",admin.getUsername());
            model.addAttribute("Email",admin.getEmail());
            model.addAttribute("classrooms", admin.getClassrooms().size());
          
             return "profile";
        }
        @GetMapping("/Notesview/{id}")
        public String notesview(@PathVariable("id") String classId,Model model) {
          System.out.println(classId);
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
    

    


   