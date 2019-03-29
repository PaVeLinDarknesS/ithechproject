package com.itech.library.service;

import com.itech.library.pojo.BookPojo;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookPojo> getAllBooks();

    List<BookPojo> getBooksByAuthorFio(String firstName, String lastName);

    Optional<BookPojo> getBookByTitle(String title);

    BookPojo addBook(BookPojo book);

    BookPojo updateBook(BookPojo book);

    BookPojo deleteBook(BookPojo book);
}
