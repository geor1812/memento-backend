package com.memento.backend.controller;

import com.memento.backend.model.Note;
import com.memento.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    
}
