package org.health.project.mappers.NotePadMapper;

import org.health.project.dtos.NotePadDto;
import org.health.project.entities.NotePad;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NotePadMapperImpl implements NotePadMapper {
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public NotePad from_NotePadDto_to_NotePad(NotePadDto notePadDto) {
        NotePad notePad = modelMapper.map(notePadDto,NotePad.class);
        return notePad;
    }

    @Override
    public NotePadDto from_NotePad_to_NotePadDto(NotePad notePad) {
        NotePadDto notePadDto = modelMapper.map(notePad,NotePadDto.class);
        return notePadDto;
    }
}
