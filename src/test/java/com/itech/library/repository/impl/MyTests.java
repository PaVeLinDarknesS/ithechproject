package com.itech.library.repository.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.entity.Book;
import com.itech.library.entity.User;
import com.itech.library.repository.BookRepository;
import com.itech.library.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
@Transactional
public class MyTests {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testManyToMany() {
        User admin = userRepository.getUserByLogin("Admin").get();
        admin.getBooks().size();

        Book book1 = bookRepository.getBookByTitle("Title1").get();
        Book book2 = bookRepository.getBookByTitle("Title2").get();

        admin.addBook(book1);
        admin.addBook(book2);

        admin.getBooks().add(book1);

        userRepository.updateUser(admin);

        Assert.assertEquals(2, admin.getBooks().size());

    }
}
