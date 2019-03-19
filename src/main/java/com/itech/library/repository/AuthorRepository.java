package com.itech.library.repository;

import com.itech.library.entity.Author;

import java.util.Optional;

public interface AuthorRepository {

    Optional<Author> getAuthorById(int id);

    Author addAuthor(Author author);

    Author updateAuthor(Author author);

    Author deleteAuthor(Author author);

//    List<Author> getAllAuthors();

//    Optional<Author> findOne(Author author);
}
