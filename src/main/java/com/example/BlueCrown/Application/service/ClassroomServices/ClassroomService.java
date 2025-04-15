package com.example.BlueCrown.Application.service.ClassroomServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.Exceptions.ClassroomNotFound;
import com.example.BlueCrown.Application.Model.AdminModel.Admin;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Repository.AdminRepo;
import com.example.BlueCrown.Application.Repository.ClassroomRepo;
import com.example.BlueCrown.Application.service.AdminServices.AdminService;

@Service
public class ClassroomService {
   @Autowired
    private ClassroomRepo repo;
    @Autowired
    AdminService  AdminService;
    @Autowired
    AdminRepo adminRepo;
    ///geting All classroom list
    public List<ClassroomModel> GetAllClassroom(String email){
      return AdminService.getByEmail(email).getClassrooms();
    }

    //Add Clasroom
    public ResponseEntity<?> addClassroom(ClassroomModel classroom, String email) {
      repo.save(classroom);
      Admin admin=AdminService.getByEmail(email);
      System.out.println(admin);
      admin.getClassrooms().add(classroom);
      adminRepo.save(admin);
      return new ResponseEntity<>(HttpStatus.CREATED);
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
      if(repo.existsById(id)){
     Optional<ClassroomModel> OPclass=repo.findById(id);
     ClassroomModel classroom =OPclass.get();
      repo.delete(classroom);
      return new ResponseEntity<>(AdminService.getByEmail(email).getClassrooms().remove(classroom),HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public ClassroomModel getClassroomById(String id){
      return repo.findById(id);
    }


  
}
