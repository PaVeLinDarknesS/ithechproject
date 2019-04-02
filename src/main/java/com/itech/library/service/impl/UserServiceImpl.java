package com.itech.library.service.impl;

import com.itech.library.converter.impl.UserDtoConverter;
import com.itech.library.dto.UserDto;
import com.itech.library.entity.User;
import com.itech.library.repository.UserRepository;
import com.itech.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    //TODO Write

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDtoConverter userConverter;

    @Override
    public Optional<User> getUserByLogin(String login) {
        Optional<User> user = Optional.empty();
        if (!StringUtils.isEmpty(login)) {
            user = userRepository.getUserByLogin(login);
        }
        return user;
    }

    @Override
    public boolean checkExistUser(UserDto userDto) {
        boolean result = false;
        result = userRepository.getUser(userConverter.pojoToEntity(userDto)).isPresent();
        return result;
    }

    @Override
    public User addUser(UserDto user) {
        User addUser;
        Optional<User> optionalUser = userRepository.getUserByLogin(user.getLogin());
        if (!optionalUser.isPresent()) {
            addUser = userRepository.addUser(userConverter.pojoToEntity(user));
        } else {
            addUser = null;
        }
        return addUser;
    }
}
