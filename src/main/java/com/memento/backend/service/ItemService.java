package com.memento.backend.service;

import com.memento.backend.exception.ResourceNotFoundException;
import com.memento.backend.model.Item;
import com.memento.backend.model.Note;
import com.memento.backend.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ItemRepo itemRepo;

    //Create an item
    public ResponseEntity<Item> createItem(Item item) {
        return new ResponseEntity<>(itemRepo.save(item), HttpStatus.CREATED);
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

