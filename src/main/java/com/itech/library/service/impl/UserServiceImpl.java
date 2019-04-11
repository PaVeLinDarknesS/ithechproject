package com.itech.library.service.impl;

import com.itech.library.dto.UserDto;
import com.itech.library.entity.Book;
import com.itech.library.entity.User;
import com.itech.library.exeption.BookCountLessZeroExeption;
import com.itech.library.exeption.TakeSameBookExeption;
import com.itech.library.exeption.UserExistException;
import com.itech.library.exeption.UserNotFoundException;
import com.itech.library.repository.BookRepository;
import com.itech.library.repository.UserRepository;
import com.itech.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<User> getUserByLogin(String login) {
        Optional<User> user = Optional.empty();
        if (!StringUtils.isEmpty(login)) {
            user = userRepository.getUserByLogin(login);
        }
        return user;
    }

    @Override
    public boolean checkExistUser(UserDto userDto) {
        return userRepository.getUserByAllField(
                new User(userDto.getLogin(), userDto.getPassword())
        ).isPresent();
    }

    @Override
    public User addUser(UserDto user) throws UserExistException {
        Optional<User> optionalUser = userRepository.getUserByLogin(user.getLogin());
        if (!optionalUser.isPresent()) {
            return userRepository.addUser(new User(user.getLogin(), user.getPassword()));
        } else {
            throw new UserExistException("User with login_" + user.getLogin() + "_ exist");
        }
    }

    @Override
    public User updateUser(UserDto userDto) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.getUserById(userDto.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setLogin(userDto.getLogin());
            user.setPassword(userDto.getPassword());

            return userRepository.updateUser(user);
        }
        throw new UserNotFoundException("User with id_" + userDto.getId() + "_ not found");
    }

    @Override
    public User deleteUser(UserDto userDto) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.getUserByAllField(
                new User(userDto.getLogin(), userDto.getPassword()));

        if (optionalUser.isPresent()) {
            removeAllBookInUser(userDto.getLogin());
            return userRepository.deleteUser(optionalUser.get());
        }
        throw new UserNotFoundException("User with id_" + userDto.getId() + "_ not found");
    }

    @Override
    @Transactional
    public boolean addBookInUser(Integer bookId, String login) throws BookCountLessZeroExeption, TakeSameBookExeption {
        boolean result = false;
        Optional<Book> book = bookRepository.getBookById(bookId);
        Optional<User> user = userRepository.getUserByLogin(login);

        if (book.isPresent() && user.isPresent()) {
            if (book.get().getCount() > 0) {
                if (userRepository.addBookInUser(book.get(), user.get())) {
                    result = true;
                } else {
                    throw new TakeSameBookExeption("This book '" + book.get().getTitle() + "'exist in  current user");
                }
            } else {
                throw new BookCountLessZeroExeption("You cannot take book '" + book.get().getTitle() +
                        "', because now count of free = 0");
            }
        }
        return result;
    }

    @Override
    @Transactional
    public boolean removeBookInUser(Integer bookId, String login) {
        boolean result = false;
        Optional<Book> book = bookRepository.getBookById(bookId);
        Optional<User> user = userRepository.getUserByLogin(login);

        if (book.isPresent() && user.isPresent() && user.get().getBooks().contains(book.get())) {
            userRepository.removeBookInUser(book.get(), user.get());
            result = true;
        }
        return result;
    }

    @Override
    public boolean removeAllBookInUser(String login) {
        boolean result = false;
        Optional<User> userOptional = userRepository.getUserByLogin(login);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Set<Book> books = user.getBooks();

            books.forEach(book -> {
                book.getUsers().remove(user);
                book.setCount(book.getCount() + 1);
            });
            user.setBooks(new HashSet<>());

            result = true;
        }
        return result;
    }

    @Override
    @Transactional
    public Set<Book> getAllBookFromUserLogin(String login) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.getUserByLogin(login);
        if (userOptional.isPresent()) {
            userOptional.get().getBooks().size();
            return userOptional.get().getBooks();
        }
        throw new UserNotFoundException("User with login '" + login + "' not found");
    }
}
