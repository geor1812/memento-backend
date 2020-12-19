package com.memento.backend.controller;

import com.memento.backend.model.ArchivedNote;
import com.memento.backend.model.Folder;
import com.memento.backend.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:8081"
})
@RestController
public class FolderController {
    @Autowired
    FolderService folderService;
    @GetMapping("/folders")
    public ResponseEntity<List<Folder>> getAllFolders() { return folderService.getAllFolders(); }
}
