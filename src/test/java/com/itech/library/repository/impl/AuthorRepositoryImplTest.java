package com.itech.library.repository.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.entity.Author;
import com.itech.library.repository.AuthorRepository;
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

    @Test
    public void getAuthorByIdPositive() {
        Optional<Author> user = authorRepository.getAuthorById(1);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void getAuthorByIdPositiveNegative() {
        Optional<Author> user = authorRepository.getAuthorById(1);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void addAuthorPositive() {
        Author addAuthor = new Author("AuthorForTest", "AuthorForTest");
        Author author = authorRepository.addAuthor(addAuthor);
        Optional<Author> getAuthor = authorRepository.getAuthorById(author.getId());
        Assert.assertTrue(getAuthor.isPresent());
        Assert.assertEquals(author, getAuthor.get());
    }

    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    public void addAuthorNegative_AddExistAuthor() {
        Author addAuthor = new Author("First1", "Last1");
        Author author = authorRepository.addAuthor(addAuthor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAuthorNegative_AddNull() {
        Author author = authorRepository.addAuthor(null);
    }

    @Test
    public void updateAuthorPositive() {
        Author addAuthor = new Author("UpdateAuthor", "UpdateAuthor");
        authorRepository.addAuthor(addAuthor);
        addAuthor.setFirstName("NewNameAuthor");
        Author author = authorRepository.updateAuthor(addAuthor);
        Assert.assertEquals("NewNameAuthor", author.getFirstName());
    }

    @Test
    public void updateAuthorPositive_UpdateNonexistentAuthor() {
        Author addAuthor = new Author("UpdateNotExistAuthor", "UpdateAuthor");
        List<Author> authors = authorRepository.getAllAuthors();
        Author author = authorRepository.updateAuthor(addAuthor);
        List<Author> newAuthors = authorRepository.getAllAuthors();
        Assert.assertEquals(authors.size() + 1, newAuthors.size());
    }

    @Test
    public void deleteAuthorPositive() {
        Author deleteAuthor = new Author("DeleteAuthor", "DeleteAuthor");
        authorRepository.addAuthor(deleteAuthor);
        Assert.assertTrue(authorRepository.findOne(deleteAuthor).isPresent());
        Author author = authorRepository.deleteAuthor(deleteAuthor);
        Assert.assertEquals(deleteAuthor, author);
        Assert.assertFalse(authorRepository.findOne(deleteAuthor).isPresent());
    }

    @Test
    public void deleteAuthorNegative() {
        Author deleteAuthor = new Author("DeleteAuthor", "DeleteAuthor");
        Assert.assertFalse(authorRepository.findOne(deleteAuthor).isPresent());
        Author author = authorRepository.deleteAuthor(deleteAuthor);
        Assert.assertNull(author.getId());
    }

    @Test
    public void getAllAuthorsPositive() {
        List<Author> authors = authorRepository.getAllAuthors();
        Assert.assertNotEquals(0, authors.size());
    }

    @Test
    public void findOnePositive() {
        Author findAuthor = new Author("First1", "Last1");
        Optional<Author> author = authorRepository.findOne(findAuthor);
        Assert.assertTrue(author.isPresent());
    }

    @Test
    public void findOneNegative() {
        Author findAuthor = new Author("Admin", "Admin");
        Optional<Author> author = authorRepository.findOne(findAuthor);
        Assert.assertFalse(author.isPresent());
    }
}