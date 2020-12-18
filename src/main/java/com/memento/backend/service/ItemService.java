package com.memento.backend.service;

import com.memento.backend.exception.ResourceNotFoundException;
import com.memento.backend.model.Item;
import com.memento.backend.model.Note;
import com.memento.backend.repo.ItemRepo;
import com.memento.backend.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ItemService {
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    NoteRepo noteRepo;

    //Create an item
    public ResponseEntity<Item> createItem(Integer noteId, Item item) {
        Note note = noteRepo.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        item.setNote(note);
        return new ResponseEntity<>(itemRepo.save(item), HttpStatus.OK);
    }

    //Update a customer
    public ResponseEntity<Item> updateItem(int id, Item item) {
        Item _item = itemRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        _item.setId(item.getId());
        _item.setContent(item.getContent());
        _item.setChecked(item.isChecked());
        return new ResponseEntity<>(itemRepo.save(_item), HttpStatus.OK);
    }
}

