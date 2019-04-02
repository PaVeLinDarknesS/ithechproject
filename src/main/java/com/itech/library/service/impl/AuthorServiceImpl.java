package com.itech.library.service.impl;

import com.itech.library.converter.impl.AuthorDtoConverter;
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

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorDtoConverter authorDtoConverter;


    @Override
    public Author addAuthor(AuthorDto author) {
        Author addAuthor;
        Optional<Author> optionalAuthor = authorRepository.getAuthorByFio(author.getFirstName(), author.getLastName());
        if (optionalAuthor.isPresent()) {
            addAuthor = optionalAuthor.get();
        } else {
            addAuthor = authorRepository.addAuthor(authorDtoConverter.dtoToEntity(author));
        }
        return addAuthor;
    }

    @Override
    public Author updateAuthor(AuthorDto author) throws AuthorNotFoundException {
        Optional<Author> optionalAuthor = authorRepository.getAuthorById(author.getId());
        if (optionalAuthor.isPresent()) {
            Author findAuthor = optionalAuthor.get();

            findAuthor.setFirstName(author.getFirstName());
            findAuthor.setLastName(author.getLastName());

            Author updateAuthor = authorRepository.updateAuthor(findAuthor);
            return updateAuthor;
        }
        throw new AuthorNotFoundException("Author with id_" + author.getId() + "_ don't found");
    }

    @Override
    @Transactional
    public Author deleteAuthor(AuthorDto author) throws AuthorNotFoundException, DeleteAuthorContainBookException {
        Optional<Author> findAuthorOptional = authorRepository.getAuthorById(author.getId());
        if (findAuthorOptional.isPresent()) {
            Author findAuthor = findAuthorOptional.get();
            if (!CollectionUtils.isEmpty(findAuthor.getBooks())) {
                throw new DeleteAuthorContainBookException(
                        "Author " + author.getFirstName() + " " + author.getLastName() + " contain Book");
            }
            Author deleteAuthor = authorRepository.deleteAuthor(findAuthor);
            return deleteAuthor;
        }
        throw new AuthorNotFoundException("Author with id_" + author.getId() + "_ don't found");
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }

    @Override
    public Optional<Author> getAuthorByFio(String firstName, String lastName) {
        Optional<Author> author = Optional.empty();
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            author = authorRepository.getAuthorByFio(firstName, lastName);
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


}
