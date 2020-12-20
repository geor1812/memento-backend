package com.memento.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notes")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)

public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(nullable = false)
    private boolean checklist;

    public void setChecklist(boolean checklist) {
        this.checklist = checklist;
    }

    public boolean getChecklist() {
        return this.checklist;
    }

    @OneToMany(mappedBy = "note")
    private Set<Item> items;

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Item> getItems() {
        return this.items;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "folder_id", referencedColumnName = "id", nullable = false)
    private Folder folder;

    //Constructors for testing purposes
    public Note(int id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Note(String title, String content, Boolean checklist){
        this.title = title;
        this.content = content;
        this.checklist = checklist;
    }

    public Note(String title, String content, Boolean checklist, Set<Item> items){
        this.title = title;
        this.content = content;
        this.checklist = checklist;
    }

    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }
}

