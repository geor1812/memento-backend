package com.memento.backend.service;

import com.memento.backend.model.ArchivedNote;
import com.memento.backend.model.Folder;
import com.memento.backend.repo.FolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {
    @Autowired
    FolderRepo folderRepo;

    public ResponseEntity<List<Folder>> getAllFolders() {
        List<Folder> archiveList = folderRepo.findAll();
        if(archiveList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(archiveList,HttpStatus.OK);
    }
}
