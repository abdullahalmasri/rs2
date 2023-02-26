package com.company.labeling.dao;

import com.company.labeling.data.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    @Mapping(source = "name", target = "name")
    UserDto mapToUserDto(UserEntity user);

    UserEntity mapToUser(UserDto userDto);
}
