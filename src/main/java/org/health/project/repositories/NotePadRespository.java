package org.health.project.repositories;

import org.health.project.entites.NotePad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotePadRespository extends JpaRepository<NotePad,Long> {
}
