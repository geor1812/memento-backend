package com.memento.backend.controller;

import com.memento.backend.model.Item;
import com.memento.backend.model.Note;
import com.memento.backend.service.ItemService;
import com.memento.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    @Autowired
    NoteService noteService;
    @Autowired
    ItemService itemService;

    //Create an item
    @CrossOrigin
    @PostMapping("/notes/{noteId}/items")
    public ResponseEntity<Item> createItem(@PathVariable(value = "noteId") Integer noteId, @RequestBody Item item) {
        return itemService.createItem(noteId, item);
    }

    //Delete an item
    @CrossOrigin
    @DeleteMapping("/items/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable(value = "id") Integer id) {
        return itemService.deleteItem(id);
    }

    //Get all notes
    @CrossOrigin
    @GetMapping("/notes/folder/{folderId}")
    public ResponseEntity<List<Note>> getAllNotes(@PathVariable("folderId") Integer folderId, @RequestParam(required = false) String searchTerm) { return noteService.getAllNotes(folderId, searchTerm); }

    //Get a note by id
    @CrossOrigin
    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") int id) { return noteService.getNoteById(id); }

    //Create a note
    @CrossOrigin
    @PostMapping("/notes/create/{folderId}")
    public ResponseEntity<Note> createNote(@PathVariable("folderId") Integer folderId, @RequestBody Note note) {
        return noteService.createNote(folderId, note);
    }

    //Update a note
    @CrossOrigin
    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") int id, @RequestBody Note note) {
        return noteService.updateNote(id, note);
    }

    //Delete a note
    @CrossOrigin
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable("id") int id) {
        return noteService.deleteNote(id);
    }
    
}
