package com.example.BlueCrown.Application.service.NotesService;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;
import com.example.BlueCrown.Application.Repository.ClassroomRepo;
import com.example.BlueCrown.Application.Repository.NotesRepo;
import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;

@Service
public class NotesService {
    
    @Autowired
    NotesRepo notesRepo;
    @Autowired
    ClassroomService ClassroomService;
    @Autowired
    ClassroomRepo ClassRepo;
    ///adding notes in Database
    public ResponseEntity<?> saveNotes(NotesModel notes, String id)
    {
        Optional<ClassroomModel> classroom=ClassroomService.getClassroomById(id);
            ClassroomModel classroom=ClassroomService.getClassroomById(id).get();
            System.out.print(classroom);
            notesRepo.save(notes);
           Optional<ClassroomModel> classroom= ClassroomService.getClassroomById(id);
            ClassRepo.save(classroom);
            return new ResponseEntity<>(HttpStatus.CREATED);
        
    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
