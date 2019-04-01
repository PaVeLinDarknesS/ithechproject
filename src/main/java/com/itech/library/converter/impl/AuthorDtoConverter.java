package com.itech.library.converter.impl;

import com.itech.library.converter.PojoConverter;
import com.itech.library.entity.Author;
import com.itech.library.dto.AuthorDto;
import org.springframework.stereotype.Service;

@Service
public class AuthorDtoConverter implements PojoConverter<AuthorDto, Author> {

    @Override
    public AuthorDto entityToPojo(Author author) {
        if (author != null) {
            AuthorDto authorDto = new AuthorDto.Builder()
                    .setId(author.getId())
                    .setFirstName(author.getFirstName())
                    .setLastName(author.getLastName())
                    .build();
            return authorDto;
        }
        return null;
    }

    @Override
    public Author pojoToEntity(AuthorDto author) {
        if (author != null) {
            Author authorEntity = new Author(author.getFirstName(), author.getLastName());
            authorEntity.setId(author.getId());
            return authorEntity;
        }
        return null;
    }
}
