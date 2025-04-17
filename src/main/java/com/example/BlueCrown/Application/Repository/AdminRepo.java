package com.example.BlueCrown.Application.Repository;
import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.BlueCrown.Application.Model.AdminModel.Admin;
/*
 * Repository of Admin
 */
@Repository
public interface AdminRepo extends MongoRepository<Admin,String>{

	
	Optional<Admin> findByEmail(String email);

  
	}

	