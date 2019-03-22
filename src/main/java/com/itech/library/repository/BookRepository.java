package com.itech.library.repository;

import com.itech.library.entity.Author;
import com.itech.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book addBook(Book book);

    Book deleteBook(Book book);

    Book updateBook(Book book);

    List<Book> getAllBooks();

    Optional<Book> getBookById(int id);

    List<Book> getBookByAuthorId(int author);
}
