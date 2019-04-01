package com.itech.library.service;

import com.itech.library.dto.AuthorDto;
import com.itech.library.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorDto addAuthor(AuthorDto author);

    AuthorDto updateAuthor(Author author);

    AuthorDto deleteAuthor(Author author);

    List<Author> getAllAuthors();

    Optional<Author> getAuthorByFio(String firstName, String lastName);

    //void addBookInAuthor(Book book, Author author);

    //void removeBookInAuthor(Book book, Author author);

}
