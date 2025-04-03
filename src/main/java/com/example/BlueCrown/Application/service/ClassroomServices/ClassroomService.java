package com.example.BlueCrown.Application.service.ClassroomServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BlueCrown.Application.Model.AdminModel.AdminModel;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Repository.AdminRepo;
import com.example.BlueCrown.Application.Repository.ClassroomRepo;

@Service
public class ClassroomService {
   
    private ClassroomRepo repo;
  
    private AdminRepo adminRepo;

    @Autowired
    public ClassroomService(ClassroomRepo repo, AdminRepo adminRepo) {
        this.repo = repo;
        this.adminRepo = adminRepo;
    }
    
    
    public ClassroomModel createClassroom(ClassroomModel classroom,String AdminEmail)
    {    System.out.println("sercahing for admin email : "+AdminEmail);
        Optional<AdminModel> am=adminRepo.findByEmail(AdminEmail);
        if(am.isPresent())
        {
            System.out.println("service am presesnt");
            AdminModel adminModel =am.get();
            classroom.setAdmin(adminModel);
            System.out.println(classroom);
            return repo.save(classroom);
        }else{
            System.out.println("service is null");
            return null;
        }
        
    }
    @Transactional
    public Boolean removeClassroom(String id) {  
        System.out.println("Checking if classroom exists: " + id);
        
        if(repo.findById(id).isPresent()) {
            System.out.println("Classroom found. Deleting...");
            repo.deleteById(id);
            System.out.println("Classroom deleted successfully.");
            return true;
        } else {
            System.out.println("Classroom not found. Returning false.");
            return false;
        }
    }
    
}
