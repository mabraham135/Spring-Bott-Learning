package com.example.easynotes.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;

@Controller

public class NoteController {
	@Autowired
    NoteRepository noteRepository;
	private static final Logger logger = LogManager.getLogger(NoteController.class);
	// Get All Notes
	@GetMapping("/notes")
	public List<Note> getAllNotes() {
	    return noteRepository.findAll();
	}
	
	// Create a new Note
	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		logger.info("saving....");
	    return noteRepository.save(note);
	}
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Simple");
		model.addAttribute("name", "namee");
		return "home";
	}
	
	// Get a Single Note
	@GetMapping("/notes/{id}")
	public ResponseEntity<Note> getNoteById(@PathVariable(value = "id") Long noteId,Model model) {
	    Note note = noteRepository.findOne(noteId);
	    if(note == null) {
	        return ResponseEntity.notFound().build();
	    }
		logger.info("fetching....");
	    return ResponseEntity.ok().body(note);
	    
	}
	
	// Update a Note
	@PutMapping("/notes/{id}")
	public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long noteId, 
	                                       @Valid @RequestBody Note noteDetails) {
	    Note note = noteRepository.findOne(noteId);
	    if(note == null) {
	        return ResponseEntity.notFound().build();
	    }
	    note.setTitle(noteDetails.getTitle());
	    note.setContent(noteDetails.getContent());

	    Note updatedNote = noteRepository.save(note);
	    logger.info("updatedNote....");
	    return ResponseEntity.ok(updatedNote);
	}
	
	// Delete a Note
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
	    Note note = noteRepository.findOne(noteId);
	    if(note == null) {
	        return ResponseEntity.notFound().build();
	    }

	    noteRepository.delete(note);
	    logger.info("delete....");
	    return ResponseEntity.ok().build();
	}

}
