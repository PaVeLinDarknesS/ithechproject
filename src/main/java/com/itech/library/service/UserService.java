package com.itech.library.service;

import com.itech.library.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> getUserByLogin(String login);
//TODO Write

    /*
    Optional<User> getUserById(int id);
    User addUser(User user);
    Optional<User> findOne(User user);
    User updateUser(User updateUser);
    void addBookInUser(Book book, User user);
    void removeBookInUser(Book book, User user);
    */
}
