package com.example.BlueCrown.Application.service.ClassroomServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BlueCrown.Application.Model.AdminModel.Admin;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Repository.AdminRepo;
import com.example.BlueCrown.Application.Repository.ClassroomRepo;
import com.example.BlueCrown.Application.service.AdminServices.AdminService;

@Service
public class ClassroomService {
   
    private ClassroomRepo repo;
    
    List<ClassroomModel> GetAllClassroom(String email){
        Optional<Admin> admin=AdminService.getAdminByEmail(email);
      if(admin.isPresent()){
        
        Admin Admin=admin.get();
        
        return Admin.getClassrooms();
    }
    return null;
    }

  
}
