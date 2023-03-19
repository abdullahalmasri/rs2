package com.company.labeling.dao;

import com.company.labeling.data.LabelDto;
import com.company.labeling.data.NoteDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LabelMapperImp implements LabelMapper {
    @Override
    public LabelDto mapToLabelDto(LabelEntity labelEntity) {
        LabelDto labelDto = new LabelDto();
        labelDto.setId(labelEntity.getId());
        labelDto.setName(labelEntity.getName());
        labelDto.setNoteDtos(labelEntity.getNoteEntities().stream()
                .map(noteEntity ->
                        new NoteDto(noteEntity.getId(),noteEntity.getTitle(),noteEntity.getContent(),null))
                .collect(Collectors.toList()));
        return labelDto;
    }

    @Override
    public LabelEntity mapToLabelEntity(LabelDto labelDto) {
        LabelEntity labelEntity = new LabelEntity();
        labelEntity.setId(labelDto.getId());
        labelEntity.setName(labelDto.getName());
        labelEntity.setNoteEntities(labelDto.getNoteDtos().stream()
                .map(noteDto ->
                        new NoteEntity(noteDto.getTitle(),noteDto.getContent()))
                .collect(Collectors.toList()));
        return labelEntity;
    }
}
