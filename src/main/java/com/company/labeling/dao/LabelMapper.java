package com.company.labeling.dao;

import com.company.labeling.data.LabelDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LabelMapper {
    LabelMapper MAPPER = Mappers.getMapper(LabelMapper.class);

    LabelDto mapToLabelDto(LabelEntity labelEntity);

    LabelEntity mapToLabelEntity(LabelDto labelDto);
}
