package com.itech.library.service;

import com.itech.library.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> getUserByLogin(String login);

    //boolean checkExistUser(UserDto userDto);

    //UserDto addUser(UserDto user);

    //UserDto updateUser(UserDto updateUser);

    //UserDto deleteUser(UserDto deleteUser);

    //boolean addBookInUser(BookDto book, UserDto user);

    //boolean removeBookInUser(BookDto book, UserDto user);

    //boolean removeAllBookInUser(UserDto user);

}
