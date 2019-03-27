package com.itech.library.service.impl;

import com.itech.library.entity.User;
import com.itech.library.pojo.UserPojo;
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

    @Override
    public Optional<UserPojo> getUserByLogin(String login) {
        if (login != null && login.length() > 0) {
            Optional<User> optionalUser = userRepository.getUserByLogin(login);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                UserPojo userPojo = new UserPojo.Builder()
                        .setId(user.getId())
                        .setLogin(user.getLogin())
                        .setPassword(user.getPassword())
                        .build();
                return Optional.of(userPojo);
            }
        }
        return Optional.empty();
    }
}
