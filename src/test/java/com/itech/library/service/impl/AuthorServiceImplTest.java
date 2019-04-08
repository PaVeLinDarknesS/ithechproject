package com.itech.library.service.impl;

import com.itech.library.dto.AuthorDto;
import com.itech.library.dto.BookDto;
import com.itech.library.repository.AuthorRepository;
import com.itech.library.service.AuthorService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceImplTest {

    //@Autowired
    private AuthorService authorService = new AuthorServiceImpl();

    @Mock
    private AuthorRepository authorRepository;
/*
    @Autowired
    private BookService bookService;

    @Autowired
    private BookDtoConverter bookDtoConverter;
*/


    @Test
    public void testMock() {

        //when(authorService.getAuthorByFio("a", "")).thenReturn(Optional.empty());
        when(authorRepository.getAuthorByFio("", "")).thenReturn(Optional.empty());

        Assert.assertFalse(authorService.getAuthorByFio("", "").isPresent());
        //verify(authorService).getAuthorByFio("", "");

    }


    @Test
    @Ignore
    public void getAllAuthors() {
        //Book bookDto = bookService.getBookByTitle("Title1").get();
        //Book deleteBook = bookService.deleteBook(bookDtoConverter.entityToDto(bookDto));

    }

    @Test
    @Ignore
    public void getAuthorByFio() {
        //Optional<Author> author = authorService.getAuthorByFio(null, "Last1");
        //Assert.assertTrue(author.isPresent());
    }

    @Test
    @Ignore
    public void addBookInAuthor() {
        /*BookDto bookDto = new BookDto.Builder().setTitle("Title4").build();
        BookDto bookDto1 = new BookDto.Builder().setTitle("Title3").build();
        AuthorDto authorDto = new AuthorDto.Builder()
                .setFirstName("First1")
                .setLastName("Last1")
                .build();
*/
        //authorService.addBookInAuthor(bookDto, authorDto);
        //authorService.addBookInAuthor(bookDto1, authorDto);
        Assert.assertFalse(false);
    }

    @Test
    @Ignore
    public void removeBookInAuthor_ifAuthorNotContainBook_false() {
       /* BookDto bookDto = new BookDto.Builder().setTitle("Title3").build();
        AuthorDto authorDto = new AuthorDto.Builder()
                .setFirstName("First1")
                .setLastName("Last1")
                .build();
*/
        //boolean result = authorService.removeBookInAuthor(bookDto, authorDto);
        //Assert.assertFalse(result);
    }

    @Test
    @Ignore
    public void removeBookInAuthor_ifAuthorContainBook_true() {
        /*BookDto bookDto = new BookDto.Builder().setTitle("Title2").build();
        AuthorDto authorDto = new AuthorDto.Builder()
                .setFirstName("First1")
                .setLastName("Last1")
                .build();
*/
        //boolean result = authorService.removeBookInAuthor(bookDto, authorDto);
        //Assert.assertTrue(result);
    }
}