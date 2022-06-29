package org.health.project.web;

import lombok.AllArgsConstructor;
import org.health.project.dtos.NotePadDto;
import org.health.project.services.NotePadService.NotePadService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class NoteRestController {
    private NotePadService notePadService;

    @GetMapping(path = "/notes")
    public List<NotePadDto> getAllNotes(){
        return notePadService.getAllNotes();
    }


    @GetMapping(path = "/notes/{id}")
    public NotePadDto getNote(@PathVariable Long id){
        return notePadService.getNote(id);
    }


    @PostMapping(path = "/notes")
    public NotePadDto createNote(@RequestBody NotePadDto notePadDto){
        notePadDto.setDateCreated(new Date());
        return notePadService.saveNote(notePadDto);
    }

    @PutMapping(path = "/notes/{id}")
    public NotePadDto modifyNote(@RequestBody NotePadDto notePadDto, @PathVariable Long id) {
        notePadDto.setId(id);
        return notePadService.updateNote(notePadDto);
    }


    @DeleteMapping(path = "/notes/{id}")
    public void deleteNote(@PathVariable Long id){
        notePadService.deleteNote(id);
    }


}
