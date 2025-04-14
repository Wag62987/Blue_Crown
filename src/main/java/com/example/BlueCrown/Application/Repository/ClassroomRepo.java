package com.example.BlueCrown.Application.Repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;

@Repository
public interface ClassroomRepo extends MongoRepository<ClassroomModel,String> {

    ClassroomModel deleteById(String Id);

    void setClassroomId(String id);
    
}
