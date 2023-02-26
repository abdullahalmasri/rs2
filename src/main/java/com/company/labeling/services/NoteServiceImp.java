package com.company.labeling.services;

import com.company.labeling.dao.NoteEntity;
import com.company.labeling.dao.NoteMapper;
import com.company.labeling.dao.sql.NoteRepo;
import com.company.labeling.data.NoteDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NoteServiceImp implements NoteService {
    @Autowired
    private NoteRepo noteRepo;
    private final ObjectMapper objectMapper;

    public NoteServiceImp(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<NoteDto> findAllNote() {
        List<NoteEntity> noteEntities = noteRepo.findAll();
        List<NoteDto> noteDtos = new ArrayList<>();
        for (NoteEntity noteEntity : noteEntities) {
            NoteDto noteDto = new NoteDto();
            noteDto.setId(noteEntity.getId());
            noteDto.setContent(noteEntity.getContent());
            noteDto.setLabelEntity(noteEntity.getLabelEntity());
            noteDto.setTitle(noteEntity.getTitle());
            noteDtos.add(noteDto);

        }
        return noteDtos;
    }

    @Override
    public void saveListOfNote(List<NoteDto> noteDtos) {
        List<NoteEntity> noteEntities = new ArrayList<>();
        for (NoteDto noteDto : noteDtos) {
            NoteEntity noteEntity = new NoteEntity();
//            noteEntity.setId(noteDto.getId());
            noteEntity.setContent(noteDto.getContent());
            noteEntity.setLabelEntity(noteDto.getLabelEntity());
            noteEntity.setTitle(noteDto.getTitle());
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
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setTitle(noteDto.getTitle());
        noteEntity.setContent(noteDto.getContent());
        noteEntity.setLabelEntity(noteDto.getLabelEntity());
        noteRepo.save(noteEntity);
        log.info("the new note created yaaaay :) ");
        return noteDto;
    }

    @Override
    public void editNote(NoteDto noteDto) {
        NoteEntity noteEntity = noteRepo.findById(noteDto.getId()).get();
        if (noteEntity != null) {
            noteEntity.setContent(noteDto.getContent());
            noteEntity.setLabelEntity(noteDto.getLabelEntity());
            noteEntity.setTitle(noteDto.getTitle());
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
            NoteDto noteDto = new NoteDto();
            noteDto.setId(noteEntity.getId());
            noteDto.setContent(noteEntity.getContent());
            noteDto.setLabelEntity(noteEntity.getLabelEntity());
            noteDto.setTitle(noteEntity.getTitle());
            noteDtos.add(noteDto);
        }
        Page<NoteDto> noteDtos1 = new PageImpl<>(noteDtos);
        log.info("the data [{}]", noteDtos1.getContent());
        return noteDtos1;
    }
}
