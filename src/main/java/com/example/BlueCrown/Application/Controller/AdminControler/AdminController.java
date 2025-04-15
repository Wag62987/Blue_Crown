/// //////////////////////////////////////////////////////
/// ///////////////////////////////////////////////////
/////////////ALl Admin RestControllers/////////////////////
/// //////////////////////////////////////////////////////
/// ///////////////////////////////////////////////////
package com.example.BlueCrown.Application.Controller.AdminControler;
import org.springframework.http.HttpHeaders;
import com.example.BlueCrown.Application.service.AdminServices.AdminService;

import jakarta.servlet.http.HttpSession;

import com.example.BlueCrown.Application.Model.AdminModel.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController // Converts all responses to JSON
@RequestMapping("/Admin") // Base URL for all endpoints
public class AdminController {
    @Autowired
	 private AdminService service;


     @PostMapping
     ResponseEntity<String> addUser(@RequestBody Admin admin){
        service.saveAdmin(admin);
        return new ResponseEntity<>("Added",HttpStatus.CREATED);
     }
     @PostMapping("/Login")
    public ResponseEntity<Admin> authUser(@RequestBody AdminDTO adminDTO, HttpSession session) {
        Admin admin = service.getAdmin(adminDTO); 
        if (admin != null) {
            session.setAttribute("user", admin.getEmail());
            System.out.println("Seesion id: "+session.getId());
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


      @PostMapping("/Logout")
      ResponseEntity<Void> Logout(HttpSession session){  
            session.invalidate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location","/Login");
            return new ResponseEntity<>(HttpStatus.OK);
      }
     }


   