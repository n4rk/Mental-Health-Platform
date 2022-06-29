package org.health.project.repositories;

import org.health.project.entities.NotePad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotePadRespository extends JpaRepository<NotePad,Long> {
    @Query(value="SELECT * FROM note_pad ORDER BY date_created DESC", nativeQuery=true)
    List<NotePad> getAllNotes();
}
