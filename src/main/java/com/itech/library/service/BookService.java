package com.itech.library.service;

import com.itech.library.pojo.BookPojo;

import java.util.List;

public interface BookService {

    List<BookPojo> getAllBooks();

    List<BookPojo> getBooksByAuthorFio(String firstName, String lastName);
//TODO Write
    /*
    Book addBook(Book book);
    Book deleteBook(Book book);
    Book updateBook(Book book);
    Optional<Book> getBookById(int id);
    Optional<Book> getBookByTitle(String title);
    Optional<Book> findOne(Book book);
    List<Book> getBooksByAuthorId(int author);
    */
}
