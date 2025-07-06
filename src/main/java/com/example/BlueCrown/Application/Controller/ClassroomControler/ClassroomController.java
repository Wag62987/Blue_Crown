package com.example.BlueCrown.Application.Controller.ClassroomControler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlueCrown.Application.Exceptions.ClassroomNotFound;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomDTO;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;
/*
 * Controller for Classroom
 */

@CrossOrigin(value="*")
@RestController

@RequestMapping({"/Admin/Classrooms","/User/Classrooms"})
public class ClassroomController {

  @Autowired
   private ClassroomService service;

    // geting list of classroom
  @GetMapping()
  public ResponseEntity<List<ClassroomDTO>> getClassroom() {
   
    return ResponseEntity.ok(service.getUserClassroom());
  }

   @PreAuthorize("hasAnyRole('Admin')")
  @PostMapping()
  public ResponseEntity<?> addClassroom(@RequestBody ClassroomDTO classroom) {
    if(classroom==null){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else{
      service.addClassroom(classroom);
     return new ResponseEntity<>(HttpStatus.CREATED);
    }
  }

  //Updating classroom
   @PreAuthorize("hasAnyRole('Admin')")
  @PutMapping("Upadate/{id}")
  public ResponseEntity<?> putMethodName(@PathVariable("id") String classid, @RequestBody ClassroomModel classroom) throws ClassroomNotFound {
    if(service.isExist(classid))
      return service.UpdateClassroom(classroom,classid);
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }
  // Deleting classroom
   @PreAuthorize("hasAnyRole('Admin')")
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteClassroom(@PathVariable String id) throws ClassroomNotFound{
  return service.deleteClassroom(id);
  }

 
  
        }
