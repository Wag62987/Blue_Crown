package com.example.BlueCrown.Application.service.ClassroomServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.AdminNotFound;
import com.example.BlueCrown.Application.Model.AdminModel.Admin;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Repository.AdminRepo;
import com.example.BlueCrown.Application.Repository.ClassroomRepo;
import com.example.BlueCrown.Application.service.AdminServices.AdminService;

@Service
public class ClassroomService {
   @Autowired
    private ClassroomRepo repo;
    AdminService  AdminService;
    public List<ClassroomModel> GetAllClassroom(String email){
      try{
        Admin admin=AdminService.getByEmail(email);
        return admin.getClassrooms();
      }catch(AdminNotFound e){
        return null;
      }
    }
    public ResponseEntity<String> addClassroom(ClassroomModel classroom, String email) {

      repo.save(classroom);
      AdminRepo.addClassroomReferenceByEmail(email,classroom);
      return new  ResponseEntity<>(HttpStatus.OK);
    }

  
}
