package com.memento.backend;

import com.memento.backend.controller.NoteController;
import com.memento.backend.model.Item;
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
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NoteIntegrationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    NoteRepo noteRepo;



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
        Note n1 = new Note( "test", "test");
        Note n2 = new Note( "test", "test");

        entityManager.persist(n1);
        entityManager.persist(n2);

        Note foundNote = noteRepo.findById(n2.getId()).get();

        assertThat(foundNote).isEqualTo(n2);
    }

    @Test
    public void should_find_notes_that_contain_searchterm_in_name() {
        Note n1 = new Note( "testcow", "test");
        Note n2 = new Note( "test", "dog");
        Note n3 = new Note("testcow","pig");
        entityManager.persist(n1);
        entityManager.persist(n2);
        entityManager.persist(n3);

        List<Note> noteList = noteRepo.findByTitleContaining("testcow");
        assertThat(noteList).hasSize(2);
        assertThat(noteList).contains(n1,n3);
    }

    @Test
    public void should_update_note(){

        Note n = new Note("title","content",false);
        entityManager.persist(n);

        Item i1 = new Item("test",false, n);
        Item i2 = new Item("test2",false, n);
        entityManager.persist(i1);
        entityManager.persist(i2);

        Set<Item> items = Set.of(i1,i2);

        Note updatedNote = new Note("utitle","ucontent",false, items);

        Note _n = noteRepo.findById(n.getId()).get();
        _n.setTitle(updatedNote.getTitle());
        _n.setContent(updatedNote.getContent());
        _n.setChecklist(updatedNote.getChecklist());
        _n.setItems(updatedNote.getItems());
        noteRepo.save(_n);

        Note checkN = noteRepo.findById(n.getId()).get();
        assertThat(checkN.getId()).isEqualTo(n.getId());
        assertThat(checkN.getTitle()).isEqualTo(updatedNote.getTitle());
        assertThat(checkN.getContent()).isEqualTo(updatedNote.getContent());
        assertThat(checkN.getChecklist()).isEqualTo(updatedNote.getChecklist());
        assertThat(checkN.getItems()).isEqualTo(updatedNote.getItems());
    }

}
