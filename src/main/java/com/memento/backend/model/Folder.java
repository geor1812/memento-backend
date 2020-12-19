package com.memento.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "folders")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", nullable = false)
    private int id;

    @Lob
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "folder")
    private Set<Note> notes;

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public Set<Note> getNotes() {
        return this.notes;
    }

}
