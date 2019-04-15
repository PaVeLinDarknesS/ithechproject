package com.itech.library.repository;

import com.itech.library.entity.Book;
import com.itech.library.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> getUserById(int id);

    Optional<User> getUserByLogin(String login);

    User addUser(User user);

    User updateUser(User updateUser);

    User deleteUser(User deleteUser);

    /**
     * Work with Set<Book>
     */
    boolean addBookInUser(Book book, User user);

    boolean removeBookInUser(Book book, User user);
}
