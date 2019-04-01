package com.itech.library.service.impl;

import com.itech.library.converter.impl.UserDtoConverter;
import com.itech.library.entity.User;
import com.itech.library.dto.UserDto;
import com.itech.library.repository.UserRepository;
import com.itech.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<UserDto> getUserByLogin(String login) {
        if (login != null && login.length() > 0) {
            Optional<User> optionalUser = userRepository.getUserByLogin(login);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                Optional<UserDto> userPojo = userConverter.entityToPojo(Optional.of(user));
                return userPojo;
            }
        }
        return Optional.empty();
    }
}
