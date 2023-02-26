package com.company.labeling.dao;

import com.company.labeling.data.NoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoteMapper {
    NoteMapper MAPPER = Mappers.getMapper(NoteMapper.class);

    NoteDto mapToNoteDto(NoteEntity noteEntity);

    NoteEntity mapToNoteEntity(NoteDto noteDto);
}
