package org.health.project.services.NotePadService;

import lombok.AllArgsConstructor;
import org.health.project.dtos.NotePadDto;
import org.health.project.entites.NotePad;
import org.health.project.mappers.NotePadMapper.NotePadMapper;
import org.health.project.repositories.NotePadRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class NotePadServiceImpl implements NotePadService {
    private NotePadRespository notePadRespository;
    private NotePadMapper notePadMapper;
    @Override
    public NotePadDto saveNote(NotePadDto notePadDto) {
        NotePad notePad = notePadMapper.from_NotePadDto_to_NotePad(notePadDto);
        NotePad savedNote = notePadRespository.save(notePad);
        return notePadMapper.from_NotePad_to_NotePadDto(savedNote);
    }

    @Override
    public NotePadDto updateNote(NotePadDto notePadDto) {
        NotePad notePad = notePadMapper.from_NotePadDto_to_NotePad(notePadDto);
        notePad.setDateModified(new Date());
        NotePad saved = notePadRespository.save(notePad);
        return notePadMapper.from_NotePad_to_NotePadDto(saved);
    }

    @Override
    public void deleteNote(Long id) {
        notePadRespository.deleteById(id);
    }

    @Override
    public List<NotePadDto> getAllNotes() {
        List<NotePad> notePadList = notePadRespository.findAll();
        List<NotePadDto> notePadDtos = notePadList.stream().map(note -> notePadMapper.from_NotePad_to_NotePadDto(note)).collect(Collectors.toList());
        return notePadDtos;
    }

    @Override
    public NotePadDto getNote(Long id) {
        NotePad notePad = notePadRespository.findById(id).get();
        return notePadMapper.from_NotePad_to_NotePadDto(notePad);
    }
}
