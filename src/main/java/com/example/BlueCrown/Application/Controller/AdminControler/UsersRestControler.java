package com.example.BlueCrown.Application.Controller.AdminControler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Model.UserModel.DTO;
import com.example.BlueCrown.Application.Model.UserModel.User;
import com.example.BlueCrown.Application.Utility.CurrentUser;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;
import com.example.BlueCrown.Application.service.UserServices.UserService;



@RestController
@RequestMapping({"/api/user"})
public class UsersRestControler {
    @Autowired
    CurrentUser currentUser;

    @Autowired
    UserService service;
    @Autowired
    ClassroomService Classroomservice;

    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/profile")
    public DTO getMethodName() {
        User user=currentUser.getCurrentUser();
        System.out.println("api called");
        return new DTO(user.getClassrooms().size(),user.getEmail(),user.getUserType(),user.getUsername());
    }
    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/dashbboard")
    public List<ClassroomModel> getClassroom() {
        User user=currentUser.getCurrentUser();
        System.out.println("api called");
        return user.getClassrooms();
    }
    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/Uplaod")
    public ResponseEntity<?> UploadClassroom(@RequestBody ClassroomModel classroom) {
        
        return  Classroomservice.addClassroom(classroom);
    }
     @PreAuthorize("hasRole('User')")
     @PostMapping("/Join")
     public ResponseEntity<?> JoinClassroom(@RequestBody ClassroomModel classroom) {
        
        return  Classroomservice.addClassroom(classroom);
    }
    
    
}
