package com.itech.library.service.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.converter.impl.BookDtoConverter;
import com.itech.library.dto.BookDto;
import com.itech.library.entity.Book;
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
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDtoConverter bookConverter;

    @Test
    public void getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        Assert.assertTrue(books.size() > 0);
    }

    @Test
    public void getBooksByAuthorFio() {
        List<Book> books = bookService.getBooksByAuthorFio("First1", "Last1");
        Assert.assertNotEquals(0, books.size());
    }

    @Test
    public void getBookByTitle() {
        Optional<Book> book = bookService.getBookByTitle("Title1");
        Assert.assertTrue(book.isPresent());
    }


    @Test
    @Transactional
    public void addBook() {
        BookDto bookDto = new BookDto.Builder().setTitle("My").setCount(1).build();
        BookDto bookDto1 = new BookDto.Builder().setTitle("Title1").setCount(1).build();

        BookDto addNewBook = bookService.addBook(bookDto);
        BookDto addExistBook = bookService.addBook(bookDto1);

        Assert.assertEquals(0, addExistBook.getId().intValue());
        Assert.assertNotEquals(0, addNewBook.getId().intValue());
    }

    @Test
    @Transactional
    public void updateBook() {
        Book getBook = bookService.getBookByTitle("Title1").get();
        getBook.setYear(1111);

        BookDto updateBookDto = bookService.updateBook(bookConverter.entityToDto(getBook));
        Assert.assertEquals(getBook.getId(), updateBookDto.getId());
    }

    @Test
    @Transactional
    public void deleteBook() {
        Book getBook = bookService.getBookByTitle("Title3").get();
        BookDto deleteBook = bookService.deleteBook(bookConverter.entityToDto(getBook));
        Optional<Book> findDeleteBook = bookService.getBookByTitle("Title3");
        Assert.assertFalse(findDeleteBook.isPresent());
    }
}