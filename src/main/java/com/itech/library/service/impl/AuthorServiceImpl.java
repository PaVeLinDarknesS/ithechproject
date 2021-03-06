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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;


    @Override
    public Author addAuthor(AuthorDto author) {
        return authorRepository.getAuthorByFio(author.getFirstName(), author.getLastName())
                .orElseGet(() -> authorRepository.addAuthor(
                        new Author(author.getFirstName(), author.getLastName())
                ));
    }

    @Override
    public Author updateAuthor(AuthorDto author) throws AuthorNotFoundException {
        Optional<Author> optionalAuthor = authorRepository.getAuthorById(author.getId());
        if (optionalAuthor.isPresent()) {
            Author findAuthor = optionalAuthor.get();

            findAuthor.setFirstName(author.getFirstName());
            findAuthor.setLastName(author.getLastName());

            return authorRepository.updateAuthor(findAuthor);
        }
        throw new AuthorNotFoundException("Author with id_" + author.getId() + "_ don't found");
    }

    @Override
    @Transactional
    public Author deleteAuthor(int id) throws AuthorNotFoundException, DeleteAuthorContainBookException {
        Optional<Author> findAuthorOptional = authorRepository.getAuthorById(id);
        if (findAuthorOptional.isPresent()) {
            Author findAuthor = findAuthorOptional.get();
            if (!CollectionUtils.isEmpty(findAuthor.getBooks())) {
                throw new DeleteAuthorContainBookException(
                        "Author " + findAuthor.getFirstName() + " " + findAuthor.getLastName() + " contain Book");
            }
            return authorRepository.deleteAuthor(findAuthor);
        }
        throw new AuthorNotFoundException("Author with id_" + id + "_ don't found");
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }

    @Override
    public Optional<Author> getAuthorByFio(String firstName, String lastName) {
        Optional<Author> author = Optional.empty();
        if (!StringUtils.isEmpty(firstName) && !StringUtils.isEmpty(lastName)) {
            author = authorRepository.getAuthorByFio(firstName, lastName);
        }
        return author;
    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        Optional<Author> author = Optional.empty();
        if (id > 0) {
            author = authorRepository.getAuthorById(id);
        }
        return author;
    }

    @Override
    public boolean addBookInAuthor(BookDto bookDto, AuthorDto authorDto) {
        boolean result = false;
        Optional<Book> book = bookService.getBookByTitle(bookDto.getTitle());
        Optional<Author> author = authorRepository.getAuthorByFio(authorDto.getFirstName(), authorDto.getLastName());
        if (book.isPresent() && author.isPresent()) {
            authorRepository.addBookInAuthor(book.get(), author.get());
            result = true;
        }
        return result;
    }

    @Override
    public boolean removeBookInAuthor(BookDto bookDto, AuthorDto authorDto) {
        boolean result = false;
        Optional<Book> book = bookService.getBookByTitle(bookDto.getTitle());
        Optional<Author> author = authorRepository.getAuthorByFio(authorDto.getFirstName(), authorDto.getLastName());
        if (book.isPresent() && author.isPresent()) {
            if (author.get().getBooks().contains(book.get())) {
                authorRepository.removeBookInAuthor(book.get(), author.get());
                result = true;
            }
        }
        return result;
    }

    @Override
    @Transactional
    public Set<Book> getBooksByAuthorId(int authorId) {
        Set<Book> books = new HashSet<>();
        Optional<Author> authorByFio = authorRepository.getAuthorById(authorId);
        if (authorByFio.isPresent()) {
            authorByFio.get().getBooks().size();
            books = authorByFio.get().getBooks();
        }
        return books;
    }
}
