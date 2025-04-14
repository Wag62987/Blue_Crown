package com.example.BlueCrown.Application.service.ClassroomServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.Exceptions.ClassroomNotFound;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Repository.ClassroomRepo;
import com.example.BlueCrown.Application.service.AdminServices.AdminService;

@Service
public class ClassroomService {
   @Autowired
    private ClassroomRepo repo;
    AdminService  AdminService;
    ///geting All classroom list
    public List<ClassroomModel> GetAllClassroom(String email){
      return AdminService.getByEmail(email).getClassrooms();
    }

    //Add Clasroom
    public ResponseEntity<?> addClassroom(ClassroomModel classroom, String email) {
      repo.save(classroom);
      return new ResponseEntity<>( AdminService.getByEmail(email).getClassrooms().add(classroom),HttpStatus.ACCEPTED);
  }
   
    //Upadte Classroom
    public ResponseEntity<?> UpdateClassroom(ClassroomModel UpdatedClassroom){
      Optional<ClassroomModel> classroom=repo.findById(UpdatedClassroom.getClassroomId());
      if(classroom.isPresent()){
        return new ResponseEntity<>(repo.save(UpdatedClassroom),HttpStatus.ACCEPTED);
      }else{
        return new ResponseEntity<>(new ClassroomNotFound("Classroom not exist"),HttpStatus.NOT_FOUND);
      }
    }
    //Delete Classroom
    public ResponseEntity<?> deleteClassroom(String id,String email){
      ClassroomModel classroom= repo.deleteById(id);
      return new ResponseEntity<>(AdminService.getByEmail(email).getClassrooms().remove(classroom),HttpStatus.OK);
    }


  
}
