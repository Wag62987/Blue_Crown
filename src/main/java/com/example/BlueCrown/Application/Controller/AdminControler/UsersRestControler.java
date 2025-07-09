package com.example.BlueCrown.Application.Controller.AdminControler;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlueCrown.Application.Controller.ClassroomControler.ClassroomController;
import com.example.BlueCrown.Application.Exceptions.ClassroomNotFound;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomDTO;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Model.UserModel.DTO;
import com.example.BlueCrown.Application.Model.UserModel.User;
import com.example.BlueCrown.Application.Utility.CurrentUser;
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
        return new DTO(user.getClassrooms().size(),user.getEmail(),user.getUserType(),user.getUsername());
    }
    @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/dashbboard")
    public List<ClassroomModel> getClassroom() {
        User user=currentUser.getCurrentUser();
        return user.getClassrooms();
    }
    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/Uplaod")
    public ResponseEntity<?> UploadClassroom(@RequestBody ClassroomDTO classroom) {
        
        return  Classroomservice.addClassroom(classroom);
    }
     @PreAuthorize("hasRole('User')")
     @PostMapping("/join")
     public ResponseEntity<?> JoinClassroom(@RequestBody String joinCodeRaw){
        ClassroomModel classroom=null;
            String joinCode = joinCodeRaw.replace("\"", "");
        try {
            System.out.println(classroom);
                classroom=Classroomservice.getClassroomByCode(joinCode);
                System.out.println(classroom);
                User user= currentUser.getCurrentUser();
                user.getClassrooms().add(classroom);
                service.UpdateUser(user);
           
        } catch (ClassroomNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
