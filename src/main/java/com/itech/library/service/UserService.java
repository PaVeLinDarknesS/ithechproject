package com.itech.library.service;

import com.itech.library.dto.BookDto;
import com.itech.library.dto.UserDto;
import com.itech.library.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByLogin(String login);

    boolean checkExistUser(UserDto userDto);

    User addUser(UserDto user);

    User updateUser(UserDto userDto);

    User deleteUser(UserDto userDto);

    boolean addBookInUser(BookDto bookDto, UserDto userDto);

    boolean removeBookInUser(BookDto bookDto, UserDto userDto);

    boolean removeAllBookInUser(UserDto userDto);

}
