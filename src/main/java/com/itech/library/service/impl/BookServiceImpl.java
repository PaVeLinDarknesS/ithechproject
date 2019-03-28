package com.itech.library.service.impl;

import com.itech.library.converter.impl.BookPojoConverter;
import com.itech.library.entity.Book;
import com.itech.library.pojo.BookPojo;
import com.itech.library.repository.BookRepository;
import com.itech.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    //TODO Write
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookPojoConverter bookConverter;

    @Override
    public List<BookPojo> getAllBooks() {
        List<Book> booksFromBd = bookRepository.getAllBooks();
        List<BookPojo> books = new ArrayList<>();
        booksFromBd.stream().forEach(book ->
                books.add(bookConverter.entityToPojo(Optional.ofNullable(book)).get()));
        return books;
    }

    @Override
    public List<BookPojo> getBooksByAuthorFio(String firstName, String lastName) {
        List<BookPojo> books = new ArrayList<>();
        if (firstName != null && lastName != null && firstName.length() > 0 && lastName.length() > 0) {
            List<Book> booksEntity = bookRepository.getBooksByAuthorFio(firstName, lastName);
            booksEntity.stream().forEach(book -> books.add(bookConverter.entityToPojo(Optional.of(book)).get()));
        }
        return books;
    }
}
