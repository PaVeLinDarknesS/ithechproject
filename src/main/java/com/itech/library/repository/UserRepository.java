package com.itech.library.repository;

import com.itech.library.entity.Book;
import com.itech.library.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> getUserById(int id);

    Optional<User> getUserByLogin(String login);

    User addUser(User user);

    Optional<User> getUserByAllField(User user);

    User updateUser(User updateUser);

    User deleteUser(User deleteUser);

    /**
     * Work with Set<Book>
     */
    void addBookInUser(Book book, User user);

    void removeBookInUser(Book book, User user);
}
