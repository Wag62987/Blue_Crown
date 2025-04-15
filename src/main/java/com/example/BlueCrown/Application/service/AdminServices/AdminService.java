package com.example.BlueCrown.Application.service.AdminServices;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.Exceptions.AdminNotFound;
import com.example.BlueCrown.Application.Model.AdminModel.*;
import com.example.BlueCrown.Application.Repository.AdminRepo;


@Service
@Component
public class AdminService {

    @Autowired
     private AdminRepo Repo;

    //////////Creating new user/////////
    public  Admin saveAdmin(Admin Admin) {
    	System.out.println("service triggered");
        return Repo.save(Admin);
    }
    ///for get edmin
    public  Admin getByEmail(String email){                  
       return Repo.findByEmail(email)
       .orElseThrow(()-> new AdminNotFound("new AdminNotFound(\"Admin not found by email: \" + adminDTO.getEmail())"));
    }

    ///is admin exist
    public Admin getAdmin(AdminDTO adminDTO) {
    return Repo.findByEmail(adminDTO.getEmail())
    .filter(admin -> admin.getPassword().equals(adminDTO.getPassword()))
    .orElse(null);
    }
}

