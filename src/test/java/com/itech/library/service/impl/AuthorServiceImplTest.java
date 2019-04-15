package com.itech.library.service.impl;

import com.itech.library.entity.Author;
import com.itech.library.repository.AuthorRepository;
import com.itech.library.service.AuthorService;
import com.itech.library.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService = new AuthorServiceImpl();
/*

    @Autowired
    private BookDtoConverter bookDtoConverter;
*/


    @Test
    public void testMock() {

        //when(authorService.getAuthorByFio("a", "")).thenReturn(Optional.empty());
        when(authorRepository.getAuthorByFio("", "")).thenReturn(Optional.empty());

        //verify(authorService).getAuthorByFio("", "");

    }

    @Test
    public void getAuthorById_ifAuthorExist_returnAuthorOptional() {
        when(authorRepository.getAuthorById(1)).thenReturn(Optional.of(new Author()));
        Optional<Author> author = authorService.getAuthorById(1);
        Assert.assertTrue(author.isPresent());
    }

    @Test
    public void getAuthorById_ifAuthorNotExist_returnEmptyOptional() {
        when(authorRepository.getAuthorById(0)).thenReturn(Optional.empty());
        Optional<Author> author = authorService.getAuthorById(0);
        Assert.assertFalse(author.isPresent());
    }


    @Test
    public void getAuthorByFio() {

    }



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
    public void addBookInAuthor() {
    }

    @Test
    public void removeBookInAuthor() {
    }

    @Test
    public void getBooksByAuthorId() {
    }
}