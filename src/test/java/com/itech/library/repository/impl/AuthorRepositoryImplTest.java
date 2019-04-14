package com.itech.library.repository.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.entity.Author;
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
public class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void getAuthorById_ifAuthorFind_returnAuthorOptional() {
        Optional<Author> user = authorRepository.getAuthorById(1);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void getAuthorById_ifAuthorNotExist_returnEmptyOptional() {
        Optional<Author> user = authorRepository.getAuthorById(0);
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void getAuthorByFio_ifAuthorExist_returnAuthorOptional() {
        Optional<Author> author = authorRepository.getAuthorByFio("First1", "Last1");
        Assert.assertTrue(author.isPresent());
    }

    @Test
    public void getAuthorByFio_ifAuthorNotExist_returnEmptyOptional() {
        Optional<Author> author = authorRepository.getAuthorByFio("Admin", "Admin");
        Assert.assertFalse(author.isPresent());
    }

    @Test
    public void getAllAuthors_returnListAuthor() {
        List<Author> authors = authorRepository.getAllAuthors();
        Assert.assertNotEquals(0, authors.size());
    }


    @Test
    public void addAuthor_returnNewAuthor() {
        Author addAuthor = new Author("AuthorForTest", "AuthorForTest");
        Author author = authorRepository.addAuthor(addAuthor);
        Optional<Author> getAuthor = authorRepository.getAuthorById(author.getId());
        Assert.assertTrue(getAuthor.isPresent());
        Assert.assertEquals(author, getAuthor.get());
    }

    @Test
    public void updateAuthor_ifAuthorExist_returnUpdateAuthor() {
        Author updateAuthor = authorRepository.getAuthorById(2).get();
        updateAuthor.setFirstName("NewNameAuthor");
        authorRepository.updateAuthor(updateAuthor);
        Author author = authorRepository.getAuthorById(2).get();
        Assert.assertEquals(updateAuthor.getFirstName(), author.getFirstName());
    }

    @Test
    public void updateAuthor_ifAuthorNotExist_returnNewAuthor() {
        List<Author> authors = authorRepository.getAllAuthors();
        authorRepository.updateAuthor(new Author("UpdateNotExistAuthor", "UpdateAuthor"));
        List<Author> newAuthors = authorRepository.getAllAuthors();
        Assert.assertEquals(authors.size() + 1, newAuthors.size());
    }

    @Test
    public void deleteAuthor_returnDeleteAuthor() {
        Author deleteAuthor = authorRepository.getAuthorById(2).get();
        authorRepository.deleteAuthor(deleteAuthor);
        Assert.assertFalse(authorRepository.getAuthorById(2).isPresent());
    }

    @Test
    public void addBookInAuthor() {
        Book book = bookRepository.getBookById(3).get();
        Optional<Author> authorInBook = Optional.ofNullable(book.getAuthor());

        Author author = authorRepository.getAuthorById(1).get();
        int count = author.getBooks().size();
        authorRepository.addBookInAuthor(book, author);

        Assert.assertFalse(authorInBook.isPresent());
        Assert.assertEquals(count + 1, author.getBooks().size());
        Assert.assertEquals(author, book.getAuthor());
    }

    @Test
    public void removeBookInAuthor() {
        Book book = bookRepository.getBookById(2).get();
        Author authorInBook = book.getAuthor();

        Author author = authorRepository.getAuthorById(1).get();
        int count = author.getBooks().size();
        authorRepository.removeBookInAuthor(book, author);

        Assert.assertEquals(authorInBook, author);
        Assert.assertEquals(count - 1, author.getBooks().size());
        Assert.assertFalse(Optional.ofNullable(book.getAuthor()).isPresent());
    }
}