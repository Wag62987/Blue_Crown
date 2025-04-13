package com.example.BlueCrown.Application.Model.ClassroomModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value="Classrooms")
@TypeAlias("classroom")
public class ClassroomModel {
    @Id
    private String classroomId;
    private String classroomName;
    private String classroomType;
    
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
    @Override
    public String toString() {
        return "ClassroomModel [classroomId=" + classroomId + ", classroomName=" + classroomName + ", classroomType="
                + classroomType + "]";
    }
  
   
  
    
}
