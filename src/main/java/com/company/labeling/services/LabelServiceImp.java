package com.company.labeling.services;

import com.company.labeling.dao.LabelEntity;
import com.company.labeling.dao.LabelMapper;
import com.company.labeling.dao.sql.LabelRepo;
import com.company.labeling.data.LabelDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class LabelServiceImp implements LabelService {
    private final LabelRepo labelRepo;
    private final ObjectMapper objectMapper;
    private final LabelMapper labelMapper;

    public LabelServiceImp(LabelRepo labelRepo, ObjectMapper objectMapper, LabelMapper labelMapper) {
        this.labelRepo = labelRepo;
        this.objectMapper = objectMapper;
        this.labelMapper = labelMapper;
    }

    @Override
    public LabelEntity findByName(String name) {
        return labelRepo.findByName(name);
    }

    @Override
    public List<LabelDto> findAllLabel() {
        List<LabelEntity> labelEntities = labelRepo.findAll();
        List<LabelDto> labelDtos = new ArrayList<>();
        for (LabelEntity labelEntity : labelEntities) {
            LabelDto labelDto = labelMapper.mapToLabelDto(labelEntity);
            labelDtos.add(labelDto);
        }
        return labelDtos;
    }

    @Override
    public void saveListOfLabel(Set<LabelDto> labelDtos) {
        List<LabelEntity> labelEntities = new ArrayList<>();
        for (LabelDto labelDto : labelDtos) {
            LabelEntity labelEntity = labelMapper.mapToLabelEntity(labelDto);
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
        LabelEntity labelEntity = labelMapper.mapToLabelEntity(labelDto);
        labelRepo.save(labelEntity);
        log.info("the label has been created");
        return labelDto;
    }
}
