package com.memento.backend.service;

import com.memento.backend.exception.ResourceNotFoundException;
import com.memento.backend.model.Note;
import com.memento.backend.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    NoteRepo noteRepo;


    public ResponseEntity<List<Note>> getAllNotes() {
            List<Note> noteList = noteRepo.findAll();
         if(noteList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
         return new ResponseEntity<>(noteList,HttpStatus.OK);
    }

    public ResponseEntity<Note> getNoteById(int id){
        Note note = noteRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        return new ResponseEntity<>(note, HttpStatus.OK);
    }


}
