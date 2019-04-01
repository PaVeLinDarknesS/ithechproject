package com.itech.library.service.impl;

import com.itech.library.converter.impl.AuthorDtoConverter;
import com.itech.library.dto.AuthorDto;
import com.itech.library.entity.Author;
import com.itech.library.repository.AuthorRepository;
import com.itech.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorDtoConverter authorDtoConverter;


    @Override
    public AuthorDto addAuthor(AuthorDto author) {
        Optional<Author> optionalAuthor = authorRepository.getAuthorByFio(author.getFirstName(), author.getLastName());
        if (optionalAuthor.isPresent()) {
            AuthorDto authorDto = authorDtoConverter.entityToPojo(optionalAuthor.get());
            authorDto.setId(0);
            return authorDto;
        } else {
            Author addAuthor = authorRepository.addAuthor(authorDtoConverter.pojoToEntity(author));
            return authorDtoConverter.entityToPojo(addAuthor);
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
            return authorDtoConverter.entityToPojo(updateAuthor);
        }
        return new AuthorDto(-1);
    }

    @Override
    @Transactional
    public AuthorDto deleteAuthor(Author author) {
        Optional<Author> findAuthorOptional = authorRepository.getAuthorById(author.getId());
        if (findAuthorOptional.isPresent()) {
            Author findAuthor = findAuthorOptional.get();
            if (findAuthor.getBooks() != null && findAuthor.getBooks().size() > 0) {
                return new AuthorDto(0);
            }
            Author deleteAuthor = authorRepository.deleteAuthor(findAuthor);
            return authorDtoConverter.entityToPojo(deleteAuthor);
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


}
