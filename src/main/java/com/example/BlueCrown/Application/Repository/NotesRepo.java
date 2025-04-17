package com.example.BlueCrown.Application.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;

/*
 * Repository of Notes
 */
public interface NotesRepo extends MongoRepository<NotesModel,String>{

    
} 
