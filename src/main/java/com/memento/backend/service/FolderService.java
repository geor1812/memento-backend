package com.memento.backend.service;

import com.memento.backend.exception.ResourceNotFoundException;
import com.memento.backend.model.ArchivedNote;
import com.memento.backend.model.Folder;
import com.memento.backend.model.Item;
import com.memento.backend.model.Note;
import com.memento.backend.repo.FolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FolderService {
    @Autowired
    FolderRepo folderRepo;

    //Get all folders
    public ResponseEntity<List<Folder>> getAllFolders() {
        List<Folder> archiveList = folderRepo.findAll();
        if(archiveList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(archiveList,HttpStatus.OK);
    }

    //Create a folder
    public ResponseEntity<Folder> createFolder(Folder folder) {
        return new ResponseEntity<>(folderRepo.save(folder), HttpStatus.CREATED);
    }

    //Update a folder
    public ResponseEntity<Folder> updateFolder(int id, Folder folder) {
        Folder _folder = folderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Folder", "id", id));
        _folder.setId(id);
        _folder.setTitle(folder.getTitle());
        _folder.setNotes(folder.getNotes());
        return new ResponseEntity<>(folderRepo.save(_folder), HttpStatus.OK);
    }

    //Delete a folder
    public ResponseEntity<HttpStatus> deleteFolder(int id) {
        folderRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
