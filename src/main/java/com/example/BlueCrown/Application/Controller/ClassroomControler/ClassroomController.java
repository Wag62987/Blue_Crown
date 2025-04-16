package com.example.BlueCrown.Application.Controller.ClassroomControler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(value="*")
@RestController
@RequestMapping("/Classroom")
public class ClassroomController {
   @Autowired
  ClassroomService service;
  @PostMapping()
  public ResponseEntity<?> addClassroom(@Valid @RequestBody() ClassroomModel classroom,HttpSession session) {
    System.out.println("Add CLassroom COntroller");
    System.out.println(classroom);

    if(classroom!=null)
      return service.addClassroom(classroom,(String)session.getAttribute("user"));
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping()
  public ResponseEntity<?> putMethodName(ClassroomModel classrrom) {
      return service.UpdateClassroom(classrrom);
      

  }
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteClassroom(@PathVariable String id,HttpSession session){
  return service.deleteClassroom(id,(String) session.getAttribute("user"));
  }

  @GetMapping()
  public List<ClassroomModel> getClassroom(HttpSession session) {
      return service.GetAllClassroom( (String)session.getAttribute("user"));
  }
  

  


        }
