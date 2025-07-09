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
    @NotNull
    private String joinCode;
    @DBRef
    @Nullable
     private List<NotesModel> NotesList=new ArrayList<>();

    public ClassroomModel(String classroomName, String classroomType, String joinCode,List<NotesModel> notesList) {
        this.classroomName = classroomName;
        this.classroomType = classroomType;
        this.joinCode = joinCode;
        this.NotesList=notesList;
    }

    public ClassroomModel(String classroomName, String classroomType, String joinCode) {
        this.classroomName = classroomName;
        this.classroomType = classroomType;
        this.joinCode = joinCode;
    }

    public ClassroomModel() {
    }

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
            + classroomType + ", joinCode=" + joinCode + ", NotesList=" + NotesList + "]";
  }
  @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ClassroomModel that = (ClassroomModel) o;
    return classroomId != null && classroomId.equals(that.classroomId);
}

@Override
public int hashCode() {
    return classroomId != null ? classroomId.hashCode() : 0;
}
   
  
    
}
