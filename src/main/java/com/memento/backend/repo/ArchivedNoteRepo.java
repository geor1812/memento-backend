package com.memento.backend.repo;

import com.memento.backend.model.ArchivedNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivedNoteRepo extends JpaRepository<ArchivedNote, Integer> {
}