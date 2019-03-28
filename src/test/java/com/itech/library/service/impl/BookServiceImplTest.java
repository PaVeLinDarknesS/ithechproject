package com.itech.library.service.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.pojo.BookPojo;
import com.itech.library.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
@Transactional
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void getAllBooks() {
        List<BookPojo> bookPojos = bookService.getAllBooks();
        Assert.assertTrue(bookPojos.size() > 0);
    }

    @Test
    public void getBooksByAuthorFio() {
        List<BookPojo> list = bookService.getBooksByAuthorFio("First1", "Last1");
        Assert.assertNotEquals(0, list.size());
    }
}