package com.itech.library.converter.impl;

import com.itech.library.converter.PojoConverter;
import com.itech.library.dto.UserDto;
import com.itech.library.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDtoConverter implements PojoConverter<Optional<UserDto>, Optional<User>> {

    @Override
    public Optional<UserDto> entityToPojo(Optional<User> userEntity) {
        if (userEntity.isPresent()) {
            User user = userEntity.get();
            UserDto userDto = new UserDto.Builder()
                    .setId(user.getId())
                    .setLogin(user.getLogin())
                    .setPassword(user.getPassword())
                    .build();
            return Optional.ofNullable(userDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> pojoToEntity(Optional<UserDto> userPojo) {
        if (userPojo.isPresent()) {
            UserDto user = userPojo.get();
            User userEntity = new User(user.getLogin(), user.getPassword());
            return Optional.ofNullable(userEntity);
        }
        return Optional.empty();
    }
}
