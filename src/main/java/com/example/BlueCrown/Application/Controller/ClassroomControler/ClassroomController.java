package com.example.BlueCrown.Application.Controller.ClassroomControler;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@CrossOrigin(value="*")
@RestController
@RequestMapping("/Classroom")
public class ClassroomController {
   
  @PostMapping()
  public String addClassroom(@RequestBody ClassroomModel model) {
      return new String();
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

  


        }
