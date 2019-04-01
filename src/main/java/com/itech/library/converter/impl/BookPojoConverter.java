package com.itech.library.converter.impl;

import com.itech.library.converter.PojoConverter;
import com.itech.library.entity.Book;
import com.itech.library.pojo.AuthorPojo;
import com.itech.library.pojo.BookPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookPojoConverter implements PojoConverter<BookPojo, Book> {

    @Autowired
    private AuthorPojoConverter authorPojoConverter;

    @Override
    public BookPojo entityToPojo(Book book) {
        if (book != null) {
            AuthorPojo authorPojo = authorPojoConverter.entityToPojo(book.getAuthor());

            BookPojo bookPojo = new BookPojo.Builder()
                    .setId(book.getId())
                    .setTitle(book.getTitle())
                    .setCount(book.getCount())
                    .setYear(book.getYear())
                    .setAuthor(authorPojo)
                    .build();
            return bookPojo;
        }
        return null;
    }

    @Override
    public Book pojoToEntity(BookPojo book) {
        if (book != null) {
            Book bookEntity = new Book(book.getTitle(), book.getYear(), book.getCount());
            if (book.getAuthor() != null) {
                bookEntity.setAuthor(authorPojoConverter.pojoToEntity(book.getAuthor()));
            }
            return bookEntity;
        }
        return null;
    }
}
