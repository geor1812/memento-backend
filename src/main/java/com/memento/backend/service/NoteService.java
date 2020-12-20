package com.memento.backend.service;

import com.memento.backend.exception.ResourceNotFoundException;
import com.memento.backend.model.Folder;
import com.memento.backend.model.Item;
import com.memento.backend.model.Note;
import com.memento.backend.repo.FolderRepo;
import com.memento.backend.repo.ItemRepo;
import com.memento.backend.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class NoteService {
    @Autowired
    NoteRepo noteRepo;
    @Autowired
    ItemService itemService;
    @Autowired
    FolderRepo folderRepo;

    //Get all notes
    public ResponseEntity<List<Note>> getAllNotes(Integer folderId, String searchTerm) {
        List<Note> noteList;

        Folder folder = folderRepo.findById(folderId)
                .orElseThrow(() -> new ResourceNotFoundException("Folder", "id", folderId));

        if(searchTerm == null) {
            noteList = noteRepo.findByFolder(folder);
        } else {

            noteList = noteRepo.findByFolderAndTitleContaining(folder, searchTerm);
        }

         if(noteList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         return new ResponseEntity<>(noteList,HttpStatus.OK);
    }

    //get a note by id
    public ResponseEntity<Note> getNoteById(int id){
        Note note = noteRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    //Create a note
    public ResponseEntity<Note> createNote(Integer folderId, Note note) {
        Folder folder = folderRepo.findById(folderId)
                .orElseThrow(() -> new ResourceNotFoundException("Folder", "id", folderId));
        note.setFolder(folder);
        return new ResponseEntity<>(noteRepo.save(note), HttpStatus.OK);
    }

    //Update a note
    public ResponseEntity<Note> updateNote(int id, Note note) {
        Note _note = noteRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        _note.setId(note.getId());
        _note.setTitle(note.getTitle());
        _note.setContent(note.getContent());
        _note.setChecklist(note.getChecklist());
        _note.setItems(note.getItems());

        Set<Item> itemSet = note.getItems();
        for (Item item:itemSet) {
            itemService.updateItem(item.getId(),item);
        }

        return new ResponseEntity<>(noteRepo.save(_note), HttpStatus.OK);
    }

    //Delete a note
    public ResponseEntity<HttpStatus> deleteNote(int id) {
        noteRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
