package com.example.BlueCrown.Application.Model.NotesModel;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class NotesModel {
    @Id
    private String id;  // 'Id' is now lowercase 'id'
    private String title;
    private String contentType;
    private byte[] data;

    // Constructor
    public NotesModel(String title, String contentType, byte[] data) {
        this.title = title;
        this.contentType = contentType;
        this.data = data;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {  // Corrected capitalization for parameter
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NotesModel [id=" + id + ", title=" + title + ", contentType=" + contentType + "]";
    }
}
