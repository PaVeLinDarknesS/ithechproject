package com.itech.library.service;

import com.itech.library.dto.BookDto;
import com.itech.library.entity.Book;
import com.itech.library.exeption.BookNotFoundException;
import com.itech.library.exeption.DeleteBookHaveByUserException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    List<Book> getBooksByAuthorFio(String firstName, String lastName);

    Optional<Book> getBookByTitle(String title);

    Book addBook(BookDto book);

    Book updateBook(BookDto book) throws BookNotFoundException;

    Book deleteBook(BookDto book) throws BookNotFoundException, DeleteBookHaveByUserException;
}
