/// //////////////////////////////////////////////////////
/// ///////////////////////////////////////////////////
/////////////ALl Admin RestControllers/////////////////////
/// //////////////////////////////////////////////////////
/// ///////////////////////////////////////////////////
package com.example.BlueCrown.Application.Controller.AdminControler;

import com.example.BlueCrown.Application.service.AdminServices.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.example.BlueCrown.Application.Model.AdminModel.*;

import java.net.URI;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;





@CrossOrigin(origins = "*")
@RestController // Converts all responses to JSON
@RequestMapping("/Admin") // Base URL for all endpoints
public class AdminController {
    @Autowired
	 private AdminService service;


   /////for creating new Admin//////////  
   @PostMapping("/newAdmin")
   public Boolean addAdmin(@RequestBody AdminModel am) {
	   service.saveAdmin(am);
	return true;
	   
   }

   /////for checking existance of Admin///
  @PostMapping("/check")
   public ResponseEntity<Map<String, String>> AdminExist(@RequestBody AdminDTO req,HttpSession session)
   { 
    if(service.isExist(req.getEmail(), req.getPassword()).isPresent()){
        session.setAttribute("Admin",req.getEmail());
        System.out.println("Session Set in Login: " + session.getAttribute("Admin"));
        return ResponseEntity.ok(Map.of("redirect","Admin/Home"));}
    else{
        return ResponseEntity.status(401).body(Map.of("error", "Invalid Credentials"));
    }
    
   }
 
 
    
}
   


