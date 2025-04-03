package com.example.BlueCrown.Application.Repository;
import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BlueCrown.Application.Model.AdminModel.AdminModel;

@Repository
public interface AdminRepo extends MongoRepository<AdminModel,String>{

	
	 Optional<AdminModel>findByEmail(String email);

	}

	