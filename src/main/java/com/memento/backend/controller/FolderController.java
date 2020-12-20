package com.memento.backend.controller;

import com.memento.backend.model.ArchivedNote;
import com.memento.backend.model.Folder;
import com.memento.backend.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class FolderController {
    @Autowired
    FolderService folderService;
    @GetMapping("/folders")
    public ResponseEntity<List<Folder>> getAllFolders() { return folderService.getAllFolders(); }

    @PostMapping("/folders")
    public ResponseEntity<Folder> createFolder(@RequestBody Folder folder) {
        return folderService.createFolder(folder);
    }

    @PutMapping("/folders/{id}")
    public ResponseEntity<Folder> updateFolder(@PathVariable("id") Integer id, @RequestBody Folder folder) {
        return folderService.updateFolder(id,folder);
    }

    @DeleteMapping("/folders/{id}")
    public ResponseEntity<HttpStatus> deleteFolder(@PathVariable("id") int id) {
        return folderService.deleteFolder(id);
    }
}
