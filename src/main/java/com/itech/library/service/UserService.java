package com.itech.library.service;

import com.itech.library.dto.UserDto;
import com.itech.library.entity.Book;
import com.itech.library.entity.User;
import com.itech.library.exeption.*;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    Optional<User> getUserByLogin(String login);

    boolean checkExistUser(UserDto userDto);

    User addUser(UserDto user) throws UserExistException;

    User updateUser(UserDto userDto) throws UserNotFoundException;

    User deleteUser(UserDto userDto) throws UserNotFoundException;

    boolean addBookInUser(Integer bookId, String login) throws BookCountLessZeroExeption, TakeSameBookExeption;

    boolean removeBookInUser(Integer bookId, String login) throws BookNotFoundException;

    boolean removeAllBookInUser(String login);

    Set<Book> getAllBookFromUserLogin(String login) throws UserNotFoundException;
}
