package com.example.BlueCrown.Application.Controller.ClassroomControler;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;




@CrossOrigin(value="*")
@RestController
@RequestMapping("/Classroom")
public class ClassroomController {
   
  ClassroomService service;
  @PostMapping()
  public ResponseEntity<String> addClassroom(@RequestBody ClassroomModel classroom,HttpSession session) {

      return service.addClassroom(classroom,(String)session.getAttribute("user"));
  }

  @PutMapping("{id}")
  public String putMethodName(@PathVariable String id) {
      //TODO: process PUT request
      
      return "hello";
  }
  @DeleteMapping("{id}")
  public String deleteClassroom(String id){

    return null;
  }
  @GetMapping()
  public List<ClassroomModel> getClassroom(HttpSession session) {
      return service.GetAllClassroom( (String)session.getAttribute("user"));
  }
  

  


        }
