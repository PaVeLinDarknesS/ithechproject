package com.itech.library.repository.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.entity.Book;
import com.itech.library.repository.BookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class BookRepositoryImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void addBook() {
    }

    @Test
    public void deleteBook() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void getAllBook() {
    }

    @Test
    public void getBookById() {
        List<Book> books = bookRepository.getAllBooks();
    }

    @Test
    public void getBookByAuthor() {
    }
}