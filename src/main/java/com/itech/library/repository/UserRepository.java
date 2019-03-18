package com.itech.library.repository;

import com.itech.library.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> getUserById(int id);

    Optional<User> getUserByLogin(String login);

    User addUser(User user);

    List<User> findOne(User user);
}
