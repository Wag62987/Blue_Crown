package com.example.BlueCrown.Application.service.AdminServices;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.Model.AdminModel.*;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Repository.AdminRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Component
public class AdminService {

    @Autowired
    private  AdminRepo AdminData;
     
    /////geting all admins//////////////
    public List<AdminModel> getAllAdmins() {
        return AdminData.findAll();
    }
    //////////Creating new user/////////
    public  AdminModel saveAdmin(AdminModel Admin) {
    	System.out.println("service triggered");
        return AdminData.save(Admin);
    }

    ///////Checking if existe/////////////
    public Optional<AdminModel> isExist(String email,String password) {
      return AdminData.findByEmail(email).filter(admin->password!=null&&password.equals(admin.getPassword()));
    }
    public Optional<AdminModel> getAdminByEmail( String email){
        return AdminData.findByEmail(email);
    }		
}

