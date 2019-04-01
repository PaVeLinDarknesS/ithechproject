package com.itech.library.service;

import com.itech.library.dto.BookDto;
import com.itech.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    List<Book> getBooksByAuthorFio(String firstName, String lastName);

    Optional<BookDto> getBookByTitle(String title);

    BookDto addBook(BookDto book);

    BookDto updateBook(BookDto book);

    BookDto deleteBook(BookDto book);
}
