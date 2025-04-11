package com.example.BlueCrown.Application.service.AdminServices;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.AdminNotFound;
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
    private static  AdminRepo Repo;

    //////////Creating new user/////////
    public  Admin saveAdmin(Admin Admin) {
    	System.out.println("service triggered");
        return Repo.save(Admin);
    }

    public Admin getAdmin(AdminDTO adminDTO) {
        return Repo.findByEmail(adminDTO.getEmail())
                   .orElseThrow(() -> new AdminNotFound("Admin not found by email: " + adminDTO.getEmail()));
    }
}

