package com.example.lab10.converters;

import com.example.lab10.model.UserDao;
import com.example.lab10.model.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    UserDao mapUserDtoToDao(UserDto user);
    UserDto mapUserDaoToDto(UserDao user);
}
