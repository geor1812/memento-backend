package com.memento.backend.repo;

import com.memento.backend.model.Folder;
import com.memento.backend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note, Integer> {
    List<Note> findByFolder(Folder folder);
    List<Note> findByFolderAndTitleContaining(Folder folder, String searchTerm);
}
