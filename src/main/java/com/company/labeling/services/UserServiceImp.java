package com.company.labeling.services;

import com.company.labeling.dao.AutoUserMapper;
import com.company.labeling.dao.UserEntity;
import com.company.labeling.dao.sql.UserRepo;
import com.company.labeling.data.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    private static final Object ROLES_12 = "null";
    @Autowired
    private UserRepo userRepo;
    private final ObjectMapper objectMapper;

    public UserServiceImp(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public List<UserDto> findAllUser() {
        List<UserEntity> userEntities = userRepo.findAll();
        return userEntities.stream().map(AutoUserMapper.MAPPER::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public void saveListOfUser(List<UserDto> userDtos) {

        List<UserEntity> userEntities = userDtos.stream().map(AutoUserMapper.MAPPER::mapToUser).collect(Collectors.toList());
        userRepo.saveAll(userEntities);
    }

    @Override
    public void deleteUser(UserDto userDto) {
        userRepo.deleteById(userDto.getId());

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setUsername(userDto.getUsername());
        userRepo.save(userEntity);

        UserEntity newUserEntity = userRepo.findById(userEntity.getId()).orElse(null);
        if (newUserEntity != null) {
            UserDto userDto1 = new UserDto();
            userDto1.setUsername(newUserEntity.getUsername());
            userDto1.setPassword(newUserEntity.getPassword());
            userDto1.setName(newUserEntity.getName());
            userDto1.setId(newUserEntity.getId());
            return userDto;
        } else return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findUserEntitiesByUsername(username);

        UserDto user = new UserDto();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setPassword(userEntity.getPassword());
        user.setUsername(userEntity.getUsername());


        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return String.valueOf(user.getPassword());
            }

            @Override
            public String getUsername() {
                return user.getName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        return userDetails;
    }
}
