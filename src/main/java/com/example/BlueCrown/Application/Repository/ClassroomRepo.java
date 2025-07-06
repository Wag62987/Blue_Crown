package com.example.BlueCrown.Application.Repository;




import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;

/*
 * Repository of Classroom
 */
@Repository
public interface ClassroomRepo extends MongoRepository<ClassroomModel,String> {

    void deleteById(String Id);

    public Optional<ClassroomModel> findeByjoinCode(String id);

    
}
