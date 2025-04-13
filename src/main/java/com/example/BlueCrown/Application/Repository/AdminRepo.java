package com.example.BlueCrown.Application.Repository;
import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.BlueCrown.Application.Model.AdminModel.Admin;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;

@Repository
public interface AdminRepo extends MongoRepository<Admin,String>{

	
	Optional<Admin> findByEmail(String email);

    static void addClassroomReferenceByEmail(String email, ClassroomModel classroom) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addClassroomReferenceByEmail'");
    }

	}

	