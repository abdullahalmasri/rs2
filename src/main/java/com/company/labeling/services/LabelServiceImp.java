package com.company.labeling.services;

import com.company.labeling.dao.LabelEntity;
import com.company.labeling.dao.sql.LabelRepo;
import com.company.labeling.data.LabelDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabelServiceImp implements LabelService{
    private final LabelRepo labelRepo;
    private final ObjectMapper objectMapper;

    public LabelServiceImp(LabelRepo labelRepo, ObjectMapper objectMapper) {
        this.labelRepo = labelRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<LabelDto> findAllLabel() {
        List<LabelEntity> labelEntities = labelRepo.findAll();
        List<LabelDto> labelDtos = new ArrayList<>();
        for(LabelEntity labelEntity:labelEntities){
            LabelDto labelDto = new LabelDto();
            labelDto.setId(labelEntity.getId());
            labelDto.setName(labelDto.getName());
            labelDto.setNoteEntities(labelDto.getNoteEntities());
            labelDtos.add(labelDto);
        }
        return labelDtos;
    }

    @Override
    public void saveListOfLabel(List<LabelDto> labelDtos) {
        List<LabelEntity> labelEntities = new ArrayList<>();
        for(LabelDto labelDto:labelDtos){
            LabelEntity labelEntity = new LabelEntity();
            labelEntity.setName(labelDto.getName());
            labelEntity.setNoteEntities(labelDto.getNoteEntities());
            labelEntities.add(labelEntity);
        }
        labelRepo.saveAll(labelEntities);
    }

    @Override
    public void deleteLabel(LabelDto labelDto) {


        labelRepo.deleteById(labelDto.getId());

    }

    @Override
    public LabelDto createLabel(LabelDto labelDto) {
        LabelEntity labelEntity = new LabelEntity();
        labelEntity.setName(labelDto.getName());
        labelEntity.setNoteEntities(labelDto.getNoteEntities());
        labelRepo.save(labelEntity);
        return labelDto;
    }
}
