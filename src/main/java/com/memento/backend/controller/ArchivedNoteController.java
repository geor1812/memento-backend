package com.memento.backend.controller;

import com.memento.backend.model.ArchivedNote;
import com.memento.backend.service.ArchivedNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:8081"
})
@RestController
public class ArchivedNoteController {
    @Autowired
    ArchivedNoteService archiveService;

    //Get all notes
    @GetMapping("/archive")
    public ResponseEntity<List<ArchivedNote>> getAllNotes() { return archiveService.getAllArchivedNotes(); }

    //Archive a note
    @PostMapping("/archive")
    public ResponseEntity<ArchivedNote> createNote(@RequestBody ArchivedNote note) {
        return archiveService.createArchivedNote(note);
    }
}
