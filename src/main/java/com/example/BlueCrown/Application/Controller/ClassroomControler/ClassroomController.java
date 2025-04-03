package com.example.BlueCrown.Application.Controller.ClassroomControler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlueCrown.Application.Model.AdminModel.AdminModel;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomDTO;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Admin")
public class ClassroomController {
    @Autowired
    private ClassroomService service;
    @PostMapping("/newClassroom")
     public ClassroomModel addClassroom( @RequestBody ClassroomModel classroom,HttpSession session)
     { 
        if(session.getAttribute("Admin")!=null)
        { 
          String AdminEmail=(String) session.getAttribute("Admin");
          System.out.println("Admin Emial 2"+AdminEmail);
         ClassroomModel Cm=service.createClassroom(classroom,AdminEmail );
           return Cm;
        }
        return null;
         }
        
      @PostMapping("/Classroom/{id}")
      public Boolean removeClassroom(@PathVariable String id,HttpSession session){
        
        System.out.println("classroom id: "+id);
        if(session.getAttribute("Admin")!=null)
        {  System.out.println("Classroom service run");
        
           return service.removeClassroom(id);
        }
        return false;
      }


        }
