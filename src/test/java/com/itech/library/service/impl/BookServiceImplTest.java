package com.itech.library.service.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.entity.Book;
import com.itech.library.entity.User;
import com.itech.library.pojo.BookPojo;
import com.itech.library.repository.AuthorRepository;
import com.itech.library.repository.BookRepository;
import com.itech.library.repository.UserRepository;
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
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
@Transactional
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorRepository authorRepository;

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

    @Test
    public void getBookByTitle() {
        Optional<BookPojo> book = bookService.getBookByTitle("Title1");
        Assert.assertTrue(book.isPresent());
    }


    @Test
    public void addBook() {
        BookPojo bookPojo = new BookPojo.Builder().setTitle("My").setCount(1).build();
        BookPojo bookPojo1 = new BookPojo.Builder().setTitle("Title1").setCount(1).build();

        BookPojo addNewBook = bookService.addBook(bookPojo);
        BookPojo addExistBook = bookService.addBook(bookPojo1);

        Assert.assertEquals(0, addExistBook.getId().intValue());
        Assert.assertNotEquals(0, addNewBook.getId().intValue());
    }

    @Test
    public void updateBook() {
        BookPojo getBookPojo = bookService.getBookByTitle("Title1").get();
        getBookPojo.setYear(1111);

        BookPojo updateBookPojo = bookService.updateBook(getBookPojo);
        Assert.assertEquals(getBookPojo.getId(), updateBookPojo.getId());

    }

    @Test
    public void deleteBook() {
        //Book getBook = bookRepository.getBookByTitle("Title2").get();
        //userRepository.removeBookInUser(getBook, getBook.getUsers().iterator().next());

        BookPojo getBookPojo = bookService.getBookByTitle("Title1").get();
        BookPojo deleteBook = bookService.deleteBook(getBookPojo);

        //Assert.assertEquals(getBookPojo, deleteBook);

        Optional<BookPojo> findDeleteBook = bookService.getBookByTitle("Title1");
        //Assert.assertFalse(findDeleteBook.isPresent());

    }

    @Test
    public void deleteAuthor() {
        User user = userRepository.getUserById(1).get();
        User deleteUser = userRepository.deleteUser(user);

        Assert.assertNotEquals(0, deleteUser.getId().intValue());

        Optional<User> userFind = userRepository.getUserByLogin("Admin");

    }

    @Test
    public void deleteBookInAuthor() {
        User user = userRepository.getUserById(1).get();
        List<Book> books = bookRepository.getBooksByAuthorId(1);
        userRepository.removeBookInUser(books.get(0), user);
        Assert.assertNotEquals(0, user.getId().intValue());

        Optional<User> userFind = userRepository.getUserByLogin("Admin");

    }
}