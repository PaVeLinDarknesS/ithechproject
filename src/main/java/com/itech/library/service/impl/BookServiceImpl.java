package com.itech.library.service.impl;

import com.itech.library.entity.Book;
import com.itech.library.pojo.BookPojo;
import com.itech.library.repository.BookRepository;
import com.itech.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    //TODO Write
    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<BookPojo> getAllBooks() {
        List<Book> booksFromBd = bookRepository.getAllBooks();
        List<BookPojo> books = new ArrayList<>();
        booksFromBd.stream().forEach(book -> books.add(new BookPojo.Builder()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setCount(book.getCount())
                .setYear(book.getYear())
                .build()));
        return books;
    }
}
