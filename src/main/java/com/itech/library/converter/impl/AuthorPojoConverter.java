package com.itech.library.converter.impl;

import com.itech.library.converter.PojoConverter;
import com.itech.library.entity.Author;
import com.itech.library.pojo.AuthorPojo;
import org.springframework.stereotype.Service;

@Service
public class AuthorPojoConverter implements PojoConverter<AuthorPojo, Author> {

    @Override
    public AuthorPojo entityToPojo(Author author) {
        if (author != null) {
            AuthorPojo authorPojo = new AuthorPojo.Builder()
                    .setId(author.getId())
                    .setFirstName(author.getFirstName())
                    .setLastName(author.getLastName())
                    .build();
            return authorPojo;
        }
        return null;
    }

    @Override
    public Author pojoToEntity(AuthorPojo author) {
        if (author != null) {
            Author authorEntity = new Author(author.getFirstName(), author.getLastName());
            authorEntity.setId(author.getId());
            return authorEntity;
        }
        return null;
    }
}
