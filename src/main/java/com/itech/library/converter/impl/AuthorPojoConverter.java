package com.itech.library.converter.impl;

import com.itech.library.converter.PojoConverter;
import com.itech.library.entity.Author;
import com.itech.library.pojo.AuthorPojo;

import java.util.Optional;

public class AuthorPojoConverter implements PojoConverter<Optional<AuthorPojo>, Optional<Author>> {

    @Override
    public Optional<AuthorPojo> entityToPojo(Optional<Author> authorEntity) {
        if (authorEntity.isPresent()) {
            Author author = authorEntity.get();
            AuthorPojo authorPojo = new AuthorPojo.Builder()
                    .setId(author.getId())
                    .setFirstName(author.getFirstName())
                    .setLastName(author.getLastName())
                    .build();
            return Optional.ofNullable(authorPojo);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> pojoToEntity(Optional<AuthorPojo> authorPojo) {
        if (authorPojo.isPresent()) {
            AuthorPojo author = authorPojo.get();
            Author authorEntity = new Author(author.getFirstName(), author.getLastName());
            return Optional.ofNullable(authorEntity);
        }
        return Optional.empty();
    }
}
