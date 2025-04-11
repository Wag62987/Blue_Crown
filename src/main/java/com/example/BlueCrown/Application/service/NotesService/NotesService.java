package com.example.BlueCrown.Application.service.NotesService;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;
import com.example.BlueCrown.Application.Repository.NotesRepo;

public class NotesService {
    
    @Autowired
    NotesRepo notesRepo;
    public Boolean saveNotes(NotesModel notes)
    {
        if(notes!=null)
        {
            notesRepo.save(notes);
            return true;
        }
        return false;
    }
}
