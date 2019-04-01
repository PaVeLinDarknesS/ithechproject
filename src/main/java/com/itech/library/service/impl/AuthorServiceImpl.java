package com.itech.library.service.impl;

import com.itech.library.converter.impl.AuthorDtoConverter;
import com.itech.library.dto.AuthorDto;
import com.itech.library.dto.BookDto;
import com.itech.library.entity.Author;
import com.itech.library.entity.Book;
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
    public AuthorDto addAuthor(AuthorDto author) {
        Optional<Author> optionalAuthor = authorRepository.getAuthorByFio(author.getFirstName(), author.getLastName());
        if (optionalAuthor.isPresent()) {
            AuthorDto authorDto = authorDtoConverter.entityToDto(optionalAuthor.get());
            authorDto.setId(0);
            return authorDto;
        } else {
            Author addAuthor = authorRepository.addAuthor(authorDtoConverter.dtoToEntity(author));
            return authorDtoConverter.entityToDto(addAuthor);
        }
    }

    @Override
    public AuthorDto updateAuthor(Author author) {
        Optional<Author> optionalAuthor = authorRepository.getAuthorById(author.getId());
        if (optionalAuthor.isPresent()) {
            Author findAuthor = optionalAuthor.get();

            findAuthor.setFirstName(author.getFirstName());
            findAuthor.setLastName(author.getLastName());

            Author updateAuthor = authorRepository.updateAuthor(findAuthor);
            return authorDtoConverter.entityToDto(updateAuthor);
        }
        return new AuthorDto(-1);
    }

    @Override
    @Transactional
    public AuthorDto deleteAuthor(Author author) {
        Optional<Author> findAuthorOptional = authorRepository.getAuthorById(author.getId());
        if (findAuthorOptional.isPresent()) {
            Author findAuthor = findAuthorOptional.get();
            if (!CollectionUtils.isEmpty(findAuthor.getBooks())) {
                return new AuthorDto(0);
            }
            Author deleteAuthor = authorRepository.deleteAuthor(findAuthor);
            return authorDtoConverter.entityToDto(deleteAuthor);
        }
        return new AuthorDto(-1);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }

    @Override
    public Optional<Author> getAuthorByFio(String firstName, String lastName) {
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            return authorRepository.getAuthorByFio(firstName, lastName);
        }
        return Optional.empty();
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
