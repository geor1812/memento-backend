package com.memento.backend.service;

import com.memento.backend.model.ArchivedNote;
import com.memento.backend.repo.ArchivedNoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivedNoteService {
    @Autowired
    ArchivedNoteRepo repo;

    //Get all archived notes
    public ResponseEntity<List<ArchivedNote>> getAllArchivedNotes() {
        List<ArchivedNote> archiveList = repo.findAll();
        if(archiveList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(archiveList,HttpStatus.OK);
    }

    //Create archived note
    public ResponseEntity<ArchivedNote> createArchivedNote(ArchivedNote archivedNote) {
        return new ResponseEntity<>(repo.save(archivedNote), HttpStatus.CREATED);
    }
}
