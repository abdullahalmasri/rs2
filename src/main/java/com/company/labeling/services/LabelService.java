package com.company.labeling.services;

import com.company.labeling.data.LabelDto;

import java.util.List;

public interface LabelService {

    List<LabelDto> findAllLabel();

    void saveListOfLabel(List<LabelDto> labelDtos);

    void deleteLabel(LabelDto labelDto);

    LabelDto createLabel(LabelDto labelDto);

}
