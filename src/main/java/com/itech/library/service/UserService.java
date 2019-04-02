package com.itech.library.service;

import com.itech.library.dto.BookDto;
import com.itech.library.dto.UserDto;
import com.itech.library.entity.User;
import com.itech.library.exeption.UserExistException;
import com.itech.library.exeption.UserNotFoundException;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByLogin(String login);

    boolean checkExistUser(UserDto userDto);

    User addUser(UserDto user) throws UserExistException;

    User updateUser(UserDto userDto) throws UserNotFoundException;

    User deleteUser(UserDto userDto) throws UserNotFoundException;

    boolean addBookInUser(BookDto bookDto, UserDto userDto);

    boolean removeBookInUser(BookDto bookDto, UserDto userDto);

    boolean removeAllBookInUser(UserDto userDto);

}
