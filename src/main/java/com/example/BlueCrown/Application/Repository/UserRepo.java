package com.example.BlueCrown.Application.Repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.BlueCrown.Application.Model.UserModel.User;
/*
 * Repository of User
 */
@Repository
public interface UserRepo extends MongoRepository<User,String>{

	
	Optional<User> findByEmail(String email);

    Optional<User> findBy(String username);

    Optional<User> findFirstByEmail(String email);

    boolean existsByEmail(String email);


  
	}

	