package com.memento.backend.repo;

import com.memento.backend.model.ArchivedNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivedNoteRepo extends JpaRepository<ArchivedNote, Integer> {
}