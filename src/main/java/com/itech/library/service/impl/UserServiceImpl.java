package com.itech.library.service.impl;

import com.itech.library.converter.impl.UserDtoConverter;
import com.itech.library.dto.BookDto;
import com.itech.library.dto.UserDto;
import com.itech.library.entity.Book;
import com.itech.library.entity.User;
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
    private UserDtoConverter userConverter;

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
        return userRepository.getUserByAllField(userConverter.dtoToEntity(userDto)).isPresent();
    }

    @Override
    public User addUser(UserDto user) throws UserExistException {
        Optional<User> optionalUser = userRepository.getUserByLogin(user.getLogin());
        if (!optionalUser.isPresent()) {
            return userRepository.addUser(userConverter.dtoToEntity(user));
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
        Optional<User> optionalUser = userRepository.getUserByAllField(userConverter.dtoToEntity(userDto));
        if (optionalUser.isPresent()) {
            removeAllBookInUser(userDto);
            return userRepository.deleteUser(optionalUser.get());
        }
        throw new UserNotFoundException("User with id_" + userDto.getId() + "_ not found");
    }

    @Override
    public boolean addBookInUser(BookDto bookDto, UserDto userDto) {
        boolean result = false;
        Optional<Book> book = bookRepository.getBookByTitle(bookDto.getTitle());
        Optional<User> user = userRepository.getUserByLogin(userDto.getLogin());

        if (book.isPresent() && user.isPresent()) {
            userRepository.addBookInUser(book.get(), user.get());
            result = true;
        }
        return result;
    }

    @Override
    @Transactional
    public boolean removeBookInUser(BookDto bookDto, UserDto userDto) {
        boolean result = false;
        Optional<Book> book = bookRepository.getBookByTitle(bookDto.getTitle());
        Optional<User> user = userRepository.getUserByLogin(userDto.getLogin());

        if (book.isPresent() && user.isPresent() && user.get().getBooks().contains(book.get())) {
            userRepository.removeBookInUser(book.get(), user.get());
            result = true;
        }
        return result;
    }

    @Override
    @Transactional
    public boolean removeAllBookInUser(UserDto userDto) {
        boolean result = false;
        Optional<User> userOptional = userRepository.getUserByLogin(userDto.getLogin());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Set<Book> books = user.getBooks();

            books.stream().forEach(book -> {
                book.getUsers().remove(user);
                book.setCount(book.getCount() + 1);
            });
            user.setBooks(new HashSet<>());

            result = true;
        }
        return result;
    }
}
