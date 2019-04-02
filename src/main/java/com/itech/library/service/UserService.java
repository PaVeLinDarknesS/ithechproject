package com.itech.library.service;

import com.itech.library.dto.UserDto;
import com.itech.library.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByLogin(String login);

    boolean checkExistUser(UserDto userDto);

    User addUser(UserDto user);

    //UserDto updateUser(UserDto updateUser);

    //UserDto deleteUser(UserDto deleteUser);

    //boolean addBookInUser(BookDto book, UserDto user);

    //boolean removeBookInUser(BookDto book, UserDto user);

    //boolean removeAllBookInUser(UserDto user);

}
