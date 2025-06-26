package com.example.BlueCrown.Application.Model.ClassroomModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;
import com.mongodb.lang.Nullable;

import jakarta.validation.constraints.NotNull;


@Document(value="Classrooms")
@TypeAlias("classroom")
public class ClassroomModel {
	
    @Id
    private String classroomId;
    @NotNull
    private String classroomName;
    @NotNull
    private String classroomType;
    private String joinCode;
    @DBRef
    @Nullable
     private List<NotesModel> NotesList=new ArrayList<>();

    public String getJoinCode() {
        return joinCode;
    }
    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }
    public String getClassroomId() {
        return classroomId;
    }
    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }
    public String getClassroomName() {
        return classroomName;
    }
    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }
    public String getClassroomType() {
        return classroomType;
    }
    public void setClassroomType(String classroomType) {
        this.classroomType = classroomType;
    }

    public List<NotesModel> getNotesList() {
        return NotesList;
    }
    public void setNotesList(List<NotesModel> notesList) {
        NotesList = notesList;
    }
  public void ClearNotesList() {
        NotesList.removeAll(NotesList);
    }
    @Override
    public String toString() {
        return "ClassroomModel [classroomId=" + classroomId + ", classroomName=" + classroomName + ", classroomType="
                + classroomType + "]";
    }
  
    
}
