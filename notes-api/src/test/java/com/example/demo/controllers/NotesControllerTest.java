package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entities.Note;
import com.example.demo.services.NoteService;

class NoteControllerTest {

    @InjectMocks
    NoteController noteController;

    @Mock
    NoteService noteService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNotes() {
        Iterable<Note> notes = new ArrayList<>();
        when(noteService.getNotes()).thenReturn(notes);
        Iterable<Note> result = noteController.getNotes();
        assertNotNull(result);
    }

    @Test
    void testCreateNote() {
        Note note = new Note();
        when(noteService.addNote(note)).thenReturn(1);
        Integer result = noteController.addNote(note);
        assertNotNull(result);
        assertEquals(1, result);
    }

}
