package com.itech.library.repository.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.entity.Book;
import com.itech.library.repository.AuthorRepository;
import com.itech.library.repository.BookRepository;
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
public class BookRepositoryImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void addBookPositive() {
        Book addBook = new Book("BookForTest", 2015, 5);
        Book book = bookRepository.addBook(addBook);
        Optional<Book> getAddBook = bookRepository.getBookByTitle("BookForTest");
        Assert.assertTrue(getAddBook.isPresent());
        Assert.assertEquals(book, getAddBook.get());
    }

    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    public void addUserBook_AddExistBook() {
        Book addBook = new Book("Title1", 2010, 2);
        Book book = bookRepository.addBook(addBook);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addBookNegative_AddNull() {
        Book book = bookRepository.addBook(null);
    }

    @Test
    public void getBookByTitlePositive() {
        Optional<Book> book = bookRepository.getBookByTitle("Title1");
        Assert.assertTrue(book.isPresent());
        Assert.assertEquals("Title1", book.get().getTitle());
    }

    @Test
    public void getBookByTitleNegative() {
        Optional<Book> book = bookRepository.getBookByTitle("");
        Assert.assertFalse(book.isPresent());
    }


    @Test
    public void updateBookPositive() {
        Book addBook = new Book("UpdateBook", 2019, 10);
        bookRepository.addBook(addBook);
        addBook.setCount(1);
        Book book = bookRepository.updateBook(addBook);
        Assert.assertEquals(1, addBook.getCount().intValue());
    }

    @Test
    public void updateBookNegative_UpdateNonexistentBook() {
        Book addBook = new Book("UpdateNotExistBook", 2019, 2);
        List<Book> books = bookRepository.getAllBooks();
        Book book = bookRepository.updateBook(addBook);
        List<Book> newBooks = bookRepository.getAllBooks();
        Assert.assertEquals(books.size() + 1, newBooks.size());
    }

    @Test
    public void deleteBookPositive() {
        Book deleteBook = new Book("Deletebook", 2018, 2);
        bookRepository.addBook(deleteBook);
        Assert.assertTrue(bookRepository.findOne(deleteBook).isPresent());
        Book book = bookRepository.deleteBook(deleteBook);
        Assert.assertEquals(deleteBook, book);
        Assert.assertFalse(bookRepository.findOne(deleteBook).isPresent());
    }

    @Test
    public void deleteBookNegative() {
        Book deleteBook = new Book("Deletebook", 2018, 2);
        Assert.assertFalse(bookRepository.findOne(deleteBook).isPresent());
        Book book = bookRepository.deleteBook(deleteBook);
        Assert.assertNull(book.getId());
    }

    @Test
    public void getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        Assert.assertNotEquals(0, books.size());
    }

    @Test
    public void getBookByIdPositive() {
        Optional<Book> book = bookRepository.getBookById(1);
        Assert.assertTrue(book.isPresent());
    }

    @Test
    public void getUserByIdNegative() {
        Optional<Book> book = bookRepository.getBookById(0);
        Assert.assertFalse(book.isPresent());
    }


    @Test
    public void findOnePositive() {
        Book oneBook = new Book("Title1", 2010, 2);
        Optional<Book> book = bookRepository.findOne(oneBook);
        Assert.assertTrue(book.isPresent());
        Assert.assertEquals(oneBook.getTitle(), book.get().getTitle());
        Assert.assertEquals(oneBook.getCount(), book.get().getCount());
        Assert.assertEquals(oneBook.getYear(), book.get().getYear());
    }

    @Test
    public void findOneNegative() {
        Book oneBook = new Book("None", 1111, 1);
        Optional<Book> book = bookRepository.findOne(oneBook);
        Assert.assertFalse(book.isPresent());
    }

    @Test
    public void getBookByAuthorPositive() {
        List<Book> books = bookRepository.getBooksByAuthorId(1);
        Assert.assertNotEquals(0, books.size());
    }

    @Test
    public void getBookByAuthorNegative_NonExistentAuthor() {
        List<Book> books = bookRepository.getBooksByAuthorId(0);
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void getBookByAuthorFioPositive() {
        List<Book> books = bookRepository.getBooksByAuthorFio("First1", "Last1");
        Assert.assertNotEquals(0, books.size());
    }
}