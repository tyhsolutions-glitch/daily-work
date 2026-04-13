package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.NotesApiApplication;
import com.example.demo.entities.Note;

@Service
public class NoteService {

    private final NotesApiApplication notesApiApplication;

    NoteService(NotesApiApplication notesApiApplication) {
        this.notesApiApplication = notesApiApplication;
    }

    public Note getNotes() {
        Note note = new Note();
        note.setId(1234);
        note.setTitle("Test Note Title");
        note.setContent("Test Note Content");
        return note;
    }
    
    public Note createNote(Note note) {
    	return note;
    }
}