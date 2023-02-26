package com.company.labeling.services;

import com.company.labeling.data.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDto> findAllUser();

    void saveListOfUser(List<UserDto> userDtos);

    void deleteUser(UserDto userDto);

    UserDto createUser(UserDto userDto);
}
