package com.itech.library.converter.impl;

import com.itech.library.converter.PojoConverter;
import com.itech.library.entity.Book;
import com.itech.library.pojo.AuthorPojo;
import com.itech.library.pojo.BookPojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class BookPojoConverter implements PojoConverter<Optional<BookPojo>, Optional<Book>> {

    @Autowired
    private AuthorPojoConverter authorPojoConverter;

    @Override
    public Optional<BookPojo> entityToPojo(Optional<Book> bookEntity) {
        if (bookEntity.isPresent()) {
            Book book = bookEntity.get();
            Optional<AuthorPojo> authorPojo = authorPojoConverter.entityToPojo(Optional.ofNullable(book.getAuthor()));

            BookPojo bookPojo = new BookPojo.Builder()
                    .setId(book.getId())
                    .setTitle(book.getTitle())
                    .setCount(book.getCount())
                    .setYear(book.getYear())
                    .setAuthor(authorPojo.isPresent() ? authorPojo.get() : null)
                    .build();
            return Optional.ofNullable(bookPojo);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> pojoToEntity(Optional<BookPojo> bookPojo) {
        if (bookPojo.isPresent()) {
            BookPojo book = bookPojo.get();
            Book bookEntity = new Book(book.getTitle(), book.getYear(), book.getCount());
            return Optional.ofNullable(bookEntity);
        }
        return Optional.empty();
    }
}
