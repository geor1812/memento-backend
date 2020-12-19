package com.memento.backend.repo;

import com.memento.backend.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FolderRepo extends JpaRepository<Folder,Integer> {
}
