package org.health.project.mappers.NotePadMapper;

import org.health.project.dtos.NotePadDto;
import org.health.project.entites.NotePad;

public interface NotePadMapper {
    NotePad from_NotePadDto_to_NotePad(NotePadDto notePadDto);
    NotePadDto from_NotePad_to_NotePadDto(NotePad notePad);
}
