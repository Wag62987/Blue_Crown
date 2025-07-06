package com.example.BlueCrown.Application.service.NotesService;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BlueCrown.Application.Exceptions.ClassroomNotFound;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;
import com.example.BlueCrown.Application.Repository.NotesRepo;
import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;
/*
 * Notes Services
 */
@Service

public class NotesService {
    
    @Autowired
    private NotesRepo notesRepo;
    @Autowired
    @Lazy
    private ClassroomService ClassroomService;
   
    //Adding new Notes
    @Transactional
    public ResponseEntity<?> saveNotes(NotesModel notes, ClassroomModel classroom)
    {  
        if(classroom==null){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        else{
            notesRepo.save(notes);
            classroom.getNotesList().add(notes);
            ClassroomService.UpdateClassroom(classroom);
            return new ResponseEntity<>(HttpStatus.CREATED);
            }
    }
        // Deleting notes as per Classroom
    	@Transactional
        public ResponseEntity<?> DeleteNote(NotesModel note) {
    		notesRepo.delete(note);
    		return new ResponseEntity<>(HttpStatus.OK);
       
    }
    // Geting list of notes as per Classroom
    public List<NotesModel> getNotelist(String classid) throws ClassroomNotFound {
        ClassroomModel classroom =ClassroomService.getClassroomByCode(classid);
        return classroom.getNotesList();
    } 

    // Geting notes By Notes ID
    public NotesModel getNoteById(String id){
        Optional<NotesModel> OPnote= notesRepo.findById(id); 
        if(!OPnote.isPresent())
         return null;
        NotesModel note=OPnote.get();
         return note;
      }
    //Deleting all notes
    @Transactional
    public void deleteAllnotesBYId(List<NotesModel> list) {
    		notesRepo.deleteAll(list);
    }
}
