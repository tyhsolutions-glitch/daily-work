package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Note;
import com.example.demo.repositories.NotesRepository;

@Service
public class NoteService {

    @Autowired
    NotesRepository notesRepository;

    public Iterable<Note> getNotes() {
        return notesRepository.findAll();
    }

    public Integer addNote(Note note) {
        notesRepository.save(note);
        return (int) note.getId();
    }

    public Optional<Note> getNoteById(Integer id) {
        return notesRepository.findById(id);
    }

    public void deleteNote(Integer id) {
        notesRepository.deleteById(id);
    }
}
