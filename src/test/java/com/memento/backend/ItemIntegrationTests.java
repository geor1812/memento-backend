package com.memento.backend;

import com.memento.backend.model.Item;
import com.memento.backend.model.Note;
import com.memento.backend.repo.ItemRepo;
import com.memento.backend.repo.NoteRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemIntegrationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    NoteRepo noteRepo;

    @Autowired
    ItemRepo itemRepo;




    @Test
    public void should_find_item_with_correct_note(){
        Note n1 = new Note("test", "test");

        entityManager.persist(n1);

        Note foundNote = noteRepo.findById(n1.getId()).get();

        Item i1 = new Item("test",false, foundNote);

        entityManager.persist(i1);

        Item foundItem = itemRepo.findById(i1.getId()).get();

        assertThat(foundItem.getNote()).isEqualTo(n1);
    }
}

