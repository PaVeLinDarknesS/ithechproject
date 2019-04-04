package com.itech.library.converter.impl;

import com.itech.library.converter.DtoConverter;
import com.itech.library.dto.AuthorDto;
import com.itech.library.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoConverter implements DtoConverter<AuthorDto, Author> {

    @Override
    public AuthorDto entityToDto(Author author) {
        AuthorDto authorDto = null;
        if (author != null) {
            authorDto = new AuthorDto.Builder()
                    .setId(author.getId())
                    .setFirstName(author.getFirstName())
                    .setLastName(author.getLastName())
                    .build();
        }
        return authorDto;
    }

    @Override
    public Author dtoToEntity(AuthorDto author) {
        if (author != null) {
            Author authorEntity = new Author(author.getFirstName(), author.getLastName());
            authorEntity.setId(author.getId());
            return authorEntity;
        }
        return null;
    }
}
