package com.itech.library.service;

import com.itech.library.dto.AuthorDto;
import com.itech.library.dto.BookDto;
import com.itech.library.entity.Author;
import com.itech.library.exeption.AuthorNotFoundException;
import com.itech.library.exeption.DeleteAuthorContainBookException;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author addAuthor(AuthorDto author);

    Author updateAuthor(AuthorDto author) throws AuthorNotFoundException;

    Author deleteAuthor(AuthorDto author) throws AuthorNotFoundException, DeleteAuthorContainBookException;

    List<Author> getAllAuthors();

    Optional<Author> getAuthorByFio(String firstName, String lastName);

    boolean addBookInAuthor(BookDto bookDto, AuthorDto authorDto);

    boolean removeBookInAuthor(BookDto bookDto, AuthorDto authorDto);

}
