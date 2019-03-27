package com.itech.library.converter.impl;

import com.itech.library.converter.PojoConverter;
import com.itech.library.entity.User;
import com.itech.library.pojo.UserPojo;

import java.util.Optional;

public class UserPojoConverter implements PojoConverter<Optional<UserPojo>, Optional<User>> {

    @Override
    public Optional<UserPojo> entityToPojo(Optional<User> userEntity) {
        if (userEntity.isPresent()) {
            User user = userEntity.get();
            UserPojo userPojo = new UserPojo.Builder()
                    .setId(user.getId())
                    .setLogin(user.getLogin())
                    .setPassword(user.getPassword())
                    .build();
            return Optional.ofNullable(userPojo);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> pojoToEntity(Optional<UserPojo> userPojo) {
        if (userPojo.isPresent()) {
            UserPojo user = userPojo.get();
            User userEntity = new User(user.getLogin(), user.getPassword());
            return Optional.ofNullable(userEntity);
        }
        return Optional.empty();
    }
}
