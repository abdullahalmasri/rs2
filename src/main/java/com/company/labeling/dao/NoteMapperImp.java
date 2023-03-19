package com.company.labeling.dao;

import com.company.labeling.data.LabelDto;
import com.company.labeling.data.NoteDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NoteMapperImp implements NoteMapper {
    @Override
    public NoteDto mapToNoteDto(NoteEntity noteEntity) {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(noteEntity.getId());
        noteDto.setTitle(noteEntity.getTitle());
        noteDto.setContent(noteEntity.getContent());

        noteDto.setLabels(noteEntity.getLabelEntity().stream()
                .map(labelEntity ->
                        new LabelDto(labelEntity.getId(), labelEntity.getName(), null))
                .collect(Collectors.toList()));

        return noteDto;
    }

    @Override
    public NoteEntity mapToNoteEntity(NoteDto noteDto) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setTitle(noteDto.getTitle());
        noteEntity.setContent(noteDto.getContent());
        return noteEntity;
    }
}
