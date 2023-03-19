package com.company.labeling.services;

import com.company.labeling.dao.LabelEntity;
import com.company.labeling.dao.LabelMapper;
import com.company.labeling.dao.NoteEntity;
import com.company.labeling.dao.NoteMapper;
import com.company.labeling.dao.sql.NoteRepo;
import com.company.labeling.data.LabelDto;
import com.company.labeling.data.NoteDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NoteServiceImp implements NoteService {
    @Autowired
    private NoteRepo noteRepo;
    private final ObjectMapper objectMapper;
    private final LabelService labelService;
    private final NoteMapper noteMapper;
    private final LabelMapper labelMapper;

    public NoteServiceImp(ObjectMapper objectMapper, LabelService labelService, NoteMapper noteMapper, LabelMapper labelMapper) {
        this.objectMapper = objectMapper;
        this.labelService = labelService;
        this.noteMapper = noteMapper;
        this.labelMapper = labelMapper;
    }

    @Override
    public List<NoteDto> findAllNote() {
        List<NoteEntity> noteEntities = noteRepo.findAll();
        List<NoteDto> noteDtos = new ArrayList<>();
        for (NoteEntity noteEntity : noteEntities) {
            NoteDto noteDto = noteMapper.mapToNoteDto(noteEntity);
            noteDtos.add(noteDto);

        }
        return noteDtos;
    }

    @Override
    public void saveListOfNote(List<NoteDto> noteDtos) {
        List<NoteEntity> noteEntities = new ArrayList<>();
        for (NoteDto noteDto : noteDtos) {
            NoteEntity noteEntity = noteMapper.mapToNoteEntity(noteDto);
            noteEntities.add(noteEntity);
        }

        noteRepo.saveAll(noteEntities);
    }


    @Override
    public void deleteNote(Long noteDto) {
        noteRepo.deleteById(noteDto);
        log.info("the note with id [{}] has been deleted :(  visit us again ", noteDto);

    }

    @Override
    public NoteDto createNote(NoteDto noteDto) {
        NoteEntity noteEntity =
                noteMapper.mapToNoteEntity(noteDto);


        for (LabelDto labelDto : noteDto.getLabels()) {

            LabelEntity labelEntity = labelService.findByName(labelDto.getName());

            if (labelEntity != null) {
                List<NoteEntity> noteEntities =
                        labelEntity.getNoteEntities().stream().collect(Collectors.toList());
                noteEntities.forEach(noteEntity1 -> deleteNote(noteEntity1.getId()));

                noteEntities.add(noteEntity);

                labelEntity.setNoteEntities(noteEntities);
                labelService.createLabel(labelMapper.mapToLabelDto(labelEntity));



            } else {

                List<NoteDto> noteDtos = new ArrayList<>();

                noteDtos.add(noteDto);

                labelDto.setNoteDtos(noteDtos);
                labelService.createLabel(labelDto);

            }
        }

        log.info("the new note created yaaaay :) ");
        return noteDto;
    }

    @Override
    public void editNote(NoteDto noteDto) {
        NoteEntity noteEntity = noteRepo.findById(noteDto.getId()).get();
        if (noteEntity != null) {
            noteEntity = noteMapper.mapToNoteEntity(noteDto);
            noteRepo.save(noteEntity);
            log.info("the not with id [{}] has been updated successfully ", noteDto.getId());
        } else {
            log.info("the note with id [{}] is not found", noteDto.getId());
        }
    }

    @Override
    public Page<NoteDto> findPageData(Pageable pageable) {
        Page<NoteEntity> noteEntities = noteRepo.findAll(pageable);
        List<NoteDto> noteDtos = new ArrayList<>();
        for (NoteEntity noteEntity : noteEntities.getContent()) {
            NoteDto noteDto = noteMapper.mapToNoteDto(noteEntity);
            noteDtos.add(noteDto);
        }
        Page<NoteDto> noteDtos1 = new PageImpl<>(noteDtos);
        log.info("the data [{}]", noteDtos1.getContent());
        return noteDtos1;
    }
}
