package com.company.labeling.services;

import com.company.labeling.dao.LabelEntity;
import com.company.labeling.data.LabelDto;

import java.util.List;
import java.util.Set;

public interface LabelService {
    LabelEntity findByName(String name);

    List<LabelDto> findAllLabel();

    void saveListOfLabel(Set<LabelDto> labelDtos);

//    void save(LabelDto labelDto);

    void deleteLabel(LabelDto labelDto);

    LabelDto createLabel(LabelDto labelDto);

}
