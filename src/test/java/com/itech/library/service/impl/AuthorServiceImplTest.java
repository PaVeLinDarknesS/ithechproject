package com.itech.library.service.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.converter.impl.BookDtoConverter;
import com.itech.library.dto.AuthorDto;
import com.itech.library.dto.BookDto;
import com.itech.library.entity.Author;
import com.itech.library.entity.Book;
import com.itech.library.service.AuthorService;
import com.itech.library.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
@Transactional
public class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDtoConverter bookDtoConverter;


    @Test
    public void addAuthor() {
    }

    @Test
    public void updateAuthor() {
    }

    @Test
    public void deleteAuthor() {
    }

    @Test
    public void getAllAuthors() {
        Book bookDto = bookService.getBookByTitle("Title1").get();
        BookDto deleteBook = bookService.deleteBook(bookDtoConverter.entityToDto(bookDto));

    }

    @Test
    public void getAuthorByFio() {
        Optional<Author> author = authorService.getAuthorByFio("First1", "Last1");
        Assert.assertTrue(author.isPresent());
    }

    @Test
    public void addBookInAuthor() {
        BookDto bookDto = new BookDto.Builder().setTitle("Title4").build();
        BookDto bookDto1 = new BookDto.Builder().setTitle("Title3").build();
        AuthorDto authorDto = new AuthorDto.Builder()
                .setFirstName("First1")
                .setLastName("Last1")
                .build();

        authorService.addBookInAuthor(bookDto, authorDto);
        authorService.addBookInAuthor(bookDto1, authorDto);
        Assert.assertFalse(false);
    }

    @Test
    public void removeBookInAuthor_ifAuthorNotContainBook_false() {
        BookDto bookDto = new BookDto.Builder().setTitle("Title3").build();
        AuthorDto authorDto = new AuthorDto.Builder()
                .setFirstName("First1")
                .setLastName("Last1")
                .build();

        boolean result = authorService.removeBookInAuthor(bookDto, authorDto);
        Assert.assertFalse(result);
    }

    @Test
    public void removeBookInAuthor_ifAuthorContainBook_true() {
        BookDto bookDto = new BookDto.Builder().setTitle("Title2").build();
        AuthorDto authorDto = new AuthorDto.Builder()
                .setFirstName("First1")
                .setLastName("Last1")
                .build();

        boolean result = authorService.removeBookInAuthor(bookDto, authorDto);
        Assert.assertTrue(result);
    }
}