package com.example.BlueCrown.Application.service.ClassroomServices;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BlueCrown.Application.Exceptions.ClassroomNotFound;
import com.example.BlueCrown.Application.Model.AdminModel.Admin;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;
import com.example.BlueCrown.Application.Repository.ClassroomRepo;
import com.example.BlueCrown.Application.Utility.User;
import com.example.BlueCrown.Application.service.AdminServices.AdminService;
import com.example.BlueCrown.Application.service.NotesService.NotesService;

/*
 * Classroom services
 */

@Service
public class ClassroomService {
   @Autowired
    private ClassroomRepo repo;
    @Autowired
    private AdminService  AdminService;
    @Autowired
     private NotesService Nservice;
    @Autowired 
    User user;

    ///geting All classroom list
    public List<ClassroomModel> GetAllClassroom(String email){
      return AdminService.getByEmail(email).getClassrooms();
    }

    //Add Clasroom
    @Transactional
    public ResponseEntity<?> addClassroom(ClassroomModel classroom) {
      Admin admin=user.getCurrentUser();
      System.out.println(admin);
      repo.save(classroom); 
      admin.getClassrooms().add(classroom);
      AdminService.saveAdmin(admin);
      return new ResponseEntity<>(HttpStatus.CREATED);
  }

    //Delete Classroom
    @Transactional
    public ResponseEntity<?> deleteClassroom(String id) throws ClassroomNotFound{
    	Admin admin=user.getCurrentUser();
    	ClassroomModel classroom=getClassroomById(id);
      System.out.println("Classroom have to be deleted"+classroom);
    	if(classroom==null) {
    		return new ResponseEntity<>(new ClassroomNotFound("Class not Found !!!"),HttpStatus.NOT_FOUND);
    	}
    	else {
    		Nservice.deleteAllnotesBYId(classroom.getNotesList());
    		repo.delete(classroom);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}
    	}

    //Upadte Classroom
    @Transactional
    public ResponseEntity<?> UpdateClassroom(ClassroomModel UpdatedClassroom,String classid){
           List<NotesModel> notes= getClassroomById(classid).getNotesList();
           UpdatedClassroom.getNotesList().addAll(notes);
           repo.save(UpdatedClassroom);
           return new ResponseEntity<>(HttpStatus.OK);
    
    }
        @Transactional
    public ResponseEntity<?> UpdateClassroom(ClassroomModel UpdatedClassroom){
           repo.save(UpdatedClassroom);
           return new ResponseEntity<>(HttpStatus.OK);
    
    }
  

    //geting Classroom by Classroom ID
    public ClassroomModel getClassroomById(String id){
      Optional<ClassroomModel> Class= repo.findById(id); 
      if(!Class.isPresent())
       return null;
      ClassroomModel classroom=Class.get();
       return classroom;
    }
    public List<ClassroomModel> getUserClassroom(){
      Admin admin=user.getCurrentUser();
      return admin.getClassrooms();
    }
    public ResponseEntity<?> deleteNote(String ClassId,String NoteId){
      boolean find=false;
      System.out.println("delelte Sevice invoked");
    	ClassroomModel classroom=getClassroomById(ClassId);
    	if(classroom==null) {
    		return new ResponseEntity<>(new ClassroomNotFound("Class not Found !!!"),HttpStatus.NOT_FOUND);
    	}
    	else {
    		for (NotesModel note : classroom.getNotesList()) {
          System.out.println("Note in loop"+note);
          if(note.getId().equals(NoteId)){
           System.out.println(" note:"+note);
    			  classroom.getNotesList().remove(note);
    		  	repo.save(classroom);
    			  Nservice.DeleteNote(note);
            find=true;
            break;
            
          }   
        }
        if(find=false){
          return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        else{
          return new ResponseEntity<>(HttpStatus.OK);
        }
    	
    	}
    	
    }
    public boolean isExist(String id){
      if(!repo.existsById(id)){return false;}
    else{return true;}
    }


  
}
