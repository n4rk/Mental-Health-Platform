package org.health.project.services.NotePadService;

import org.health.project.dtos.NotePadDto;

import java.util.List;

public interface NotePadService {
    NotePadDto saveNote(NotePadDto notePadDto);
    NotePadDto updateNote(NotePadDto notePadDto);
    void deleteNote(Long id);
    List<NotePadDto> getAllNotes();
    NotePadDto getNote(Long id);
}
