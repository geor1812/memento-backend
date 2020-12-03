package com.memento.backend.repo;

import com.memento.backend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note, Integer> {
}
