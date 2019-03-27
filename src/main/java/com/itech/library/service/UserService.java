package com.itech.library.service;

import com.itech.library.pojo.UserPojo;

import java.util.Optional;

public interface UserService {

    Optional<UserPojo> getUserByLogin(String login);
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
