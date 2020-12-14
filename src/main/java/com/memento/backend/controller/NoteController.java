package com.memento.backend.controller;

import com.memento.backend.model.Note;
import com.memento.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:8081"
})
@RestController
public class NoteController {

    @Autowired
    NoteService noteService;

    //Get all notes
    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes() { return noteService.getAllNotes(); }

    //Get a note by id
    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") int id) { return noteService.getNoteById(id); }

    //Create a note
    @PostMapping("/notes")
    public ResponseEntity<Note> createCustomer(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    //Update a note
    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateCustomer(@PathVariable("id") int id, @RequestBody Note note) {
        return noteService.updateNote(id, note);
    }

    //Delete a note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") int id) {
        return noteService.deleteNote(id);
    }
    
}
