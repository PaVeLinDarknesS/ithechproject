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
    public void getBookById_ifBookFind_returnBookOptional() {
        Optional<Book> book = bookRepository.getBookById(1);
        Assert.assertTrue(book.isPresent());
    }

    @Test
    public void getBookById_ifBookNotFind_returnEmptyOptional() {
        Optional<Book> book = bookRepository.getBookById(0);
        Assert.assertFalse(book.isPresent());
    }

    @Test
    public void getBookByTitle_ifBookFind_returnBookOptional() {
        Optional<Book> book = bookRepository.getBookByTitle("Title1");
        Assert.assertTrue(book.isPresent());
        Assert.assertEquals("Title1", book.get().getTitle());
    }

    @Test
    public void getBookByTitle_ifBookNotFind_returnEmptyOptional() {
        Optional<Book> book = bookRepository.getBookByTitle("");
        Assert.assertFalse(book.isPresent());
    }

    @Test
    public void getBooksByAuthorId_ifBooksExist_returnBookList() {
        List<Book> books = bookRepository.getBooksByAuthorId(1);
        Assert.assertNotEquals(0, books.size());
    }

    @Test
    public void getBooksByAuthorId_ifBooksNotExist_returnEmptyList() {
        List<Book> books = bookRepository.getBooksByAuthorId(0);
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void getBooksByAuthorFio_ifBooksExist_returnBookList() {
        List<Book> books = bookRepository.getBooksByAuthorFio("First1", "Last1");
        Assert.assertNotEquals(0, books.size());
    }

    @Test
    public void getBooksByAuthorFio_ifBooksNotExist_returnEmptyList() {
        List<Book> books = bookRepository.getBooksByAuthorFio("Not", "Not");
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void getAllBooks_returnListBooks() {
        List<Book> books = bookRepository.getAllBooks();
        Assert.assertNotEquals(0, books.size());
    }

    @Test
    public void addBook_returnNewBook() {
        Book book = bookRepository.addBook(new Book("BookForTest", 2015, 5));
        Optional<Book> getAddBook = bookRepository.getBookByTitle("BookForTest");
        Assert.assertTrue(getAddBook.isPresent());
        Assert.assertEquals(book, getAddBook.get());
    }


    @Test
    public void updateBook_ifBookExist_returnUpdateBook() {
        Book addBook = bookRepository.getBookById(3).get();
        addBook.setTitle("UpdateBook");
        Book updateBook = bookRepository.updateBook(addBook);
        Assert.assertEquals("UpdateBook", bookRepository.getBookById(3).get().getTitle());
        Assert.assertEquals(addBook, updateBook);
    }

    @Test
    public void updateBook_ifBookNotExist_returnAddBook() {
        List<Book> books = bookRepository.getAllBooks();
        Book book = bookRepository.updateBook(new Book("UpdateNotExistBook", 2019, 2));
        List<Book> newBooks = bookRepository.getAllBooks();
        Assert.assertEquals(books.size() + 1, newBooks.size());
        Assert.assertNotNull(book.getId());
    }

    @Test
    public void deleteBook_returnDeleteBook() {
        Book deleteBook = bookRepository.getBookById(2).get();
        Book book = bookRepository.deleteBook(deleteBook);
        Assert.assertEquals(deleteBook, book);
        Assert.assertFalse(bookRepository.getBookByTitle(deleteBook.getTitle()).isPresent());
    }
}
