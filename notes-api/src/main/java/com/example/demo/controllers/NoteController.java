package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Note;
import com.example.demo.services.NoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notes-api")
@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping
    public Iterable<Note> getNotes() {
        return noteService.getNotes();
    }

    @PostMapping
    public Integer addNote(@RequestBody @Valid Note note) {
        return noteService.addNote(note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Integer id) {
        noteService.deleteNote(id);
    }

    @GetMapping("/{id}")
    public Optional<Note> getNoteById(@PathVariable Integer id) {
        return noteService.getNoteById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        return errors;
    }
}
