package com.memento.backend;

import com.memento.backend.controller.NoteController;
import com.memento.backend.model.Note;
import com.memento.backend.repo.NoteRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NoteIntegrationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    NoteRepo noteRepo;

    @Autowired
    NoteController noteController;


    @BeforeEach
    public void init() {noteRepo.deleteAll();}

    @Test
    public void should_find_no_notes() {
        List<Note> noteList = noteRepo.findAll();
        assertThat(noteList).isEmpty();
    }

    @Test
    public void should_find_all_notes(){
        noteRepo.save(new Note(1, "test", "test"));
        noteRepo.save(new Note(2, "test", "test"));
        noteRepo.save(new Note(3, "test", "test"));

        List<Note> noteList = noteRepo.findAll();
        assertThat(noteList).hasSize(3);
    }

    @Test
    public void should_find_note_by_id(){
        Note n1 = new Note(1, "test", "test");
        Note n2 = new Note(2, "test", "test");

        entityManager.persist(n1);
        entityManager.persist(n2);

        Note foundNote = noteRepo.findById(n2.getId()).get();

        assertThat(foundNote).isEqualTo(n2);
    }

}
