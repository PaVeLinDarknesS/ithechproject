package com.itech.library.service;

import com.itech.library.dto.AuthorDto;
import com.itech.library.dto.BookDto;
import com.itech.library.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorDto addAuthor(AuthorDto author);

    AuthorDto updateAuthor(Author author);

    AuthorDto deleteAuthor(Author author);

    List<Author> getAllAuthors();

    Optional<Author> getAuthorByFio(String firstName, String lastName);

    boolean addBookInAuthor(BookDto bookDto, AuthorDto authorDto);

    boolean removeBookInAuthor(BookDto bookDto, AuthorDto authorDto);

}
