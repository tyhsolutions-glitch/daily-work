package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Note;
import com.example.demo.services.NoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/notes-api")
public class NotesController {
	
	@Autowired
	NoteService noteService;
	
	@GetMapping
	Note getNotes() {
		return noteService.getNotes();
	}
	@PostMapping
	public Note addNote(@RequestBody @Valid Note note) {
	return noteService.createNote(note);
	}
}
