package com.itech.library.service.impl;

import com.itech.library.dto.AuthorDto;
import com.itech.library.dto.BookDto;
import com.itech.library.entity.Author;
import com.itech.library.entity.Book;
import com.itech.library.exeption.AuthorNotFoundException;
import com.itech.library.exeption.DeleteAuthorContainBookException;
import com.itech.library.repository.AuthorRepository;
import com.itech.library.service.AuthorService;
import com.itech.library.service.BookService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookService bookService;

    @InjectMocks
    private AuthorService authorService = new AuthorServiceImpl();

    private List<Author> authors;

    @Before
    public void before() {
        authors = new ArrayList<>();
        Author defaultAuthor = new Author("", "");
        Author fillAuthor = new Author("Test", "Test");
        Author addAuthor = new Author("Add", "Add");
        addAuthor.setId(1);
        Set<Book> setBooks = new HashSet<>();
        setBooks.add(new Book("Title1", 2015, 5));
        setBooks.add(new Book("Title2", 2019, 2));
        addAuthor.setBooks(setBooks);
        authors.add(defaultAuthor);
        authors.add(fillAuthor);
        authors.add(addAuthor);

    }

    @After
    public void after() {
        authors = null;
    }

    @Test
    public void getAuthorById_ifAuthorExist_returnAuthorOptional() {
        when(authorRepository.getAuthorById(1)).thenReturn(Optional.of(authors.get(1)));
        Optional<Author> author = authorService.getAuthorById(1);
        Assert.assertTrue(author.isPresent());
    }

    @Test
    public void getAuthorById_ifAuthorIdLess1_returnEmptyOptional() {
        Optional<Author> author = authorService.getAuthorById(0);
        Assert.assertFalse(author.isPresent());
    }

    @Test
    public void getAuthorByFio_ifAuthorExist_returnAuthorOptional() {
        when(authorRepository.getAuthorByFio("Unknown", "Unknown")).thenReturn(Optional.of(authors.get(1)));
        Optional<Author> author = authorService.getAuthorByFio("Unknown", "Unknown");
        Assert.assertTrue(author.isPresent());
    }

    @Test
    public void getAuthorByFio_ifIncorrectParams_returnEmptyOptional() {
        Optional<Author> author = authorService.getAuthorByFio("", "");
        Assert.assertFalse(author.isPresent());
    }

    @Test
    public void addAuthor_ifAuthorNotExist_returnAddAuthor() {
        when(authorRepository.getAuthorByFio("Test", "Test")).thenReturn(Optional.empty());
        when(authorRepository.addAuthor(authors.get(1))).thenReturn(authors.get(2));
        Author author = authorService.addAuthor(new AuthorDto.Builder()
                .setFirstName("Test")
                .setLastName("Test")
                .build());
        Assert.assertEquals(authors.get(2), author);
    }

    @Test
    public void addAuthor_ifAuthorExist_returnFindAuthor() {
        when(authorRepository.getAuthorByFio("Fill", "Fill")).thenReturn(Optional.of(authors.get(1)));
        Author author = authorService.addAuthor(new AuthorDto.Builder().setLastName("Fill").setFirstName("Fill").build());
        Assert.assertEquals(authors.get(1), author);
    }

    @Test(expected = AuthorNotFoundException.class)
    public void updateAuthor_ifAuthorNotExist_throwException() throws AuthorNotFoundException {
        when(authorRepository.getAuthorById(1)).thenReturn(Optional.empty());
        authorService.updateAuthor(new AuthorDto(1));
    }

    @Test
    public void updateAuthor_ifAuthorExist_returnUpdateAuthor() throws AuthorNotFoundException {
        when(authorRepository.getAuthorById(1)).thenReturn(Optional.of(authors.get(1)));
        when(authorRepository.updateAuthor(authors.get(1))).thenReturn(authors.get(2));
        Author author = authorService.updateAuthor(new AuthorDto(1));
        Assert.assertEquals(authors.get(2), author);
    }

    @Test(expected = AuthorNotFoundException.class)
    public void deleteAuthor_ifAuthorNotExist_throwException() throws AuthorNotFoundException, DeleteAuthorContainBookException {
        when(authorRepository.getAuthorById(1)).thenReturn(Optional.empty());
        authorService.deleteAuthor(1);
    }

    @Test(expected = DeleteAuthorContainBookException.class)
    public void deleteAuthor_ifAuthorContainBooks_throwException() throws AuthorNotFoundException, DeleteAuthorContainBookException {
        when(authorRepository.getAuthorById(1)).thenReturn(Optional.of(authors.get(2)));
        authorService.deleteAuthor(1);
    }

    @Test
    public void deleteAuthor_ifAuthorValid_returnDeleteAuthor() throws AuthorNotFoundException, DeleteAuthorContainBookException {
        when(authorRepository.getAuthorById(1)).thenReturn(Optional.of(authors.get(1)));
        when(authorRepository.deleteAuthor(authors.get(1))).thenReturn(authors.get(1));
        Author author = authorService.deleteAuthor(1);
        Assert.assertEquals(authors.get(1), author);
    }


    @Test
    public void addBookInAuthor_ifBookAdd_returnTrue() {
        when(authorRepository.getAuthorByFio("", "")).thenReturn(Optional.of(authors.get(2)));
        when(bookService.getBookByTitle("")).thenReturn(Optional.of(new Book("Title3", 2016, 2)));
        Assert.assertTrue(authorService.addBookInAuthor(
                new BookDto.Builder().setTitle("").build(),
                new AuthorDto.Builder().setFirstName("").setLastName("").build())
        );
    }

    @Test
    public void addBookInAuthor_ifBookNotAdd_returnFalse() {
        when(authorRepository.getAuthorByFio("", "")).thenReturn(Optional.of(authors.get(2)));
        when(bookService.getBookByTitle("")).thenReturn(Optional.empty());
        Assert.assertFalse(authorService.addBookInAuthor(
                new BookDto.Builder().setTitle("").build(),
                new AuthorDto.Builder().setFirstName("").setLastName("").build())
        );
    }

    @Test
    public void removeBookInAuthor_ifBookRemove_returnTrue() {
        when(authorRepository.getAuthorByFio("", "")).thenReturn(Optional.of(authors.get(2)));
        when(bookService.getBookByTitle("")).thenReturn(Optional.of(new Book("Title1", 2015, 5)));
        Assert.assertTrue(authorService.removeBookInAuthor(
                new BookDto.Builder().setTitle("").build(),
                new AuthorDto.Builder().setFirstName("").setLastName("").build())
        );
    }

    @Test
    public void removeBookInAuthor_ifBookNotRemove_returnFalse() {
        when(authorRepository.getAuthorByFio("", "")).thenReturn(Optional.of(authors.get(1)));
        when(bookService.getBookByTitle("")).thenReturn(Optional.of(new Book("Title1", 2015, 5)));
        Assert.assertFalse(authorService.removeBookInAuthor(
                new BookDto.Builder().setTitle("").build(),
                new AuthorDto.Builder().setFirstName("").setLastName("").build())
        );
    }

    @Test
    public void getBooksByAuthorId_returnListBook() {
        when(authorRepository.getAuthorById(1)).thenReturn(Optional.of(authors.get(1)));
        Set<Book> books = authorService.getBooksByAuthorId(1);
        Assert.assertEquals(authors.get(1).getBooks().size(), books.size());
    }
}