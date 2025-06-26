package com.example.BlueCrown.Application.Controller.ClassroomControler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlueCrown.Application.Exceptions.ClassroomNotFound;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
/*
 * Controller for Classroom
 */

@CrossOrigin(value="*")
@RestController
@Controller
@RequestMapping("/Admin/Classrooms")
public class ClassroomController {

  @Autowired
   private ClassroomService service;

  @PostMapping()
  public String addClassroom(@ModelAttribute ClassroomModel classroom) {
    if(classroom!=null)
      service.addClassroom(classroom);
      return "dashboard";
  }

  //Updating classroom
  @PutMapping("/{id}")
  public ResponseEntity<?> putMethodName(@PathVariable("id") String classid, @RequestBody ClassroomModel classroom) {
    if(service.isExist(classid))
      return service.UpdateClassroom(classroom,classid);
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }
  // Deleting classroom
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteClassroom(@PathVariable String id) throws ClassroomNotFound{
  return service.deleteClassroom(id);
  }

  // geting list of classroom
  @GetMapping()
  public ResponseEntity<List<ClassroomModel>> getClassroom() {
    return ResponseEntity.ok(service.getUserClassroom());
  }
  
        }
