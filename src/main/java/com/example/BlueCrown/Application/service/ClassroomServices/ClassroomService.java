package com.example.BlueCrown.Application.service.ClassroomServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BlueCrown.Application.Exceptions.AdminAleradyExist;
import com.example.BlueCrown.Application.Exceptions.ClassroomNotFound;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomDTO;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;
import com.example.BlueCrown.Application.Model.UserModel.User;
import com.example.BlueCrown.Application.Repository.ClassroomRepo;
import com.example.BlueCrown.Application.Utility.CurrentUser;
import com.example.BlueCrown.Application.service.NotesService.NotesService;
import com.example.BlueCrown.Application.service.UserServices.UserService;

/*
 * Classroom services
 */

@Service
public class ClassroomService {
  
   @Autowired
    private ClassroomRepo repo;
    @Autowired
    
    private UserService  UserService;
    @Autowired
     private NotesService Nservice;
    @Autowired 
    CurrentUser currentUser;

    ///geting All classroom list
    /// 
      public List<ClassroomDTO> getUserClassroom(){
        User User=currentUser.getCurrentUser();
        List<ClassroomDTO> list=new ArrayList<>();
        switch(User.getUserType()){
         case "Admin":
                      for (ClassroomModel classroom : User.getClassrooms()) {
                        ClassroomDTO dto =new ClassroomDTO(classroom.getClassroomName(),classroom.getClassroomType(),classroom.getJoinCode(),classroom.getNotesList());
                      list.add(dto);
                        }
         break;
         case "User":
                for (ClassroomModel classroom : User.getClassrooms()) {
                        if(!classroom.getClassroomType().equals("Private")){

                          ClassroomDTO dto =new ClassroomDTO(classroom.getClassroomName(),classroom.getClassroomType(),classroom.getJoinCode(),classroom.getNotesList());
                      list.add(dto);
                        }
                      }
         break;
                    }
          return list;
                  }
                    
                      
     public List<ClassroomModel> AllClassroom(){
      return repo.findAll();
     }
  
    

    //Add Clasroom
     @PreAuthorize("hasRole('Admin')")
    @Transactional
    public ResponseEntity<?> addClassroom(ClassroomDTO classroomDTO) {
      User User=currentUser.getCurrentUser();
      String joinCode;
      do {
          joinCode = UUID.randomUUID().toString().substring(0, 8);
      } while (repo.existsByJoinCode(joinCode)); 
      String name=classroomDTO.getClassroomName(),
      classType=classroomDTO.getClassroomType();

     ClassroomModel classroom=new ClassroomModel(name,classType,joinCode);
      repo.save(classroom); 
      User.getClassrooms().add(classroom);
      UserService.UpdateUser(User);
    return new ResponseEntity<>(HttpStatus.OK);
  }
   @PreAuthorize("hasRole('User')")
   @Transactional
    public ResponseEntity<?> addClassroom(String joincode){
      User User=currentUser.getCurrentUser();
      try{
      ClassroomModel classroom =getClassroomByCode(joincode);
      User.getClassrooms().add(classroom);
      UserService.UpdateUser(User);
      }catch(ClassroomNotFound e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(HttpStatus.FOUND);
  }

    //Delete Classroom
    @PreAuthorize("hasRole('Admin')")
    @Transactional
    public ResponseEntity<?> deleteClassroom(String code) throws ClassroomNotFound{
    	ClassroomModel classroom=getClassroomByCode(code);
    	if(classroom==null) {
    		return new ResponseEntity<>(new ClassroomNotFound("Class not Found !!!"),HttpStatus.NOT_FOUND);
    	}
    	else {
    		Nservice.deleteAllnotesBYId(classroom.getNotesList());
        User user=currentUser.getCurrentUser();
        List<ClassroomModel> list=user.getClassrooms();
         list.remove(classroom);
         user.setClassrooms(list);
        UserService.UpdateUser(user);
    		repo.delete(classroom);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}
    	}

    //Upadte Classroom
    @Transactional
    public ResponseEntity<?> UpdateClassroom(ClassroomDTO UpdatedClassroom,String code) throws ClassroomNotFound{
           
           ClassroomModel classroom= getClassroomByCode(code);
           String name=UpdatedClassroom.getClassroomName();
           String type=UpdatedClassroom.getClassroomType();
           classroom.setClassroomName(name);
           classroom.setClassroomType(type);
           if(UpdatedClassroom.getNotesList()!=null){
            classroom.setNotesList(UpdatedClassroom.getNotesList());
           }
           repo.save(classroom);
      
       return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Admin')")
        @Transactional
    public ResponseEntity<?> UpdateClassroom(ClassroomModel UpdatedClassroom,String code) throws ClassroomNotFound{
           ClassroomModel classroom = getClassroomByCode(code);

    classroom.setClassroomName(UpdatedClassroom.getClassroomName());
    classroom.setClassroomType(UpdatedClassroom.getClassroomType());
    classroom.setNotesList(UpdatedClassroom.getNotesList()); 

    repo.save(classroom);
      return new ResponseEntity<>(HttpStatus.OK);
    
    }
  

    //geting Classroom by Classroom ID
    public ClassroomModel getClassroomByCode(String code) throws ClassroomNotFound{
      System.out.println("Code : "+ code);
      Optional<ClassroomModel> Class= repo.findByJoinCode(code); 
      if(!Class.isPresent()){

        System.out.println("Class is null");
      throw new ClassroomNotFound("Inavlid Code");
      }
       else{
      ClassroomModel classroom=Class.get();
       return classroom;
       }
    }
    
    public ResponseEntity<?> deleteNote(String code,String NoteId) throws ClassroomNotFound{
      boolean find=false;
    	ClassroomModel classroom=getClassroomByCode(code);
    	if(classroom==null) {
    		return new ResponseEntity<>(new ClassroomNotFound("Class not Found !!!"),HttpStatus.NOT_FOUND);
    	}
    	else {
    		for (NotesModel note : classroom.getNotesList()) {
          if(note.getId().equals(NoteId)){
    			  classroom.getNotesList().remove(note);
    		  	repo.save(classroom);
    			  Nservice.DeleteNote(note);
            find=true;
            break; 
          }   
        }
        if(!find){
          return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        else{
          return new ResponseEntity<>(HttpStatus.OK);
        }
    	
    	}
    	
    }
    public boolean isExist(String joinCode){
      if(!repo.existsByJoinCode(joinCode)){return false;}
    else{return true;}
    }
    public void JoinClassroom(ClassroomModel classroom) throws AdminAleradyExist{
      User user= currentUser.getCurrentUser();
      user.getClassrooms().add(classroom);
      UserService.UpdateUser(user);
    }

  
}
