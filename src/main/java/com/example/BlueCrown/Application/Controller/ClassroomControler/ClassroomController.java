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
  public ResponseEntity<?> addClassroom(@RequestBody ClassroomModel classroom,HttpSession session) {

      return service.addClassroom(classroom,(String)session.getAttribute("user"));
  }

  @PutMapping()
  public ResponseEntity<?> putMethodName(ClassroomModel classrrom) {
      return service.UpdateClassroom(classrrom);
      

  }
  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteClassroom(String id,HttpSession session){
  return service.deleteClassroom(id,(String) session.getAttribute("user"));
  }

  @GetMapping()
  public List<ClassroomModel> getClassroom(HttpSession session) {
      return service.GetAllClassroom( (String)session.getAttribute("user"));
  }
  

  


        }
