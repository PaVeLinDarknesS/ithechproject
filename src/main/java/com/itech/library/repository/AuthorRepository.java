package com.itech.library.repository;

import com.itech.library.entity.Author;
import com.itech.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Optional<Author> getAuthorById(int id);

    Optional<Author> getAuthorByFio(String firstName, String lastName);

    List<Author> getAllAuthors();

    Author addAuthor(Author author);

    Author updateAuthor(Author author);

    Author deleteAuthor(Author author);

    /**
     * Working with Set<book>
     */
    void addBookInAuthor(Book book, Author author);

    void removeBookInAuthor(Book book, Author author);
}
