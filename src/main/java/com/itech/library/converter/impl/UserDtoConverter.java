package com.itech.library.converter.impl;

import com.itech.library.converter.PojoConverter;
import com.itech.library.dto.UserDto;
import com.itech.library.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserDtoConverter implements PojoConverter<UserDto, User> {

    @Override
    public UserDto entityToPojo(User user) {
        if (user != null) {
            UserDto userDto = new UserDto.Builder()
                    .setId(user.getId())
                    .setLogin(user.getLogin())
                    .setPassword(user.getPassword())
                    .build();
            return userDto;
        }
        return null;
    }

    @Override
    public User pojoToEntity(UserDto user) {
        if (user != null) {
            User userEntity = new User(user.getLogin(), user.getPassword());
            return userEntity;
        }
        return null;
    }
}
