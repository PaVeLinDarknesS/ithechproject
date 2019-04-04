package com.itech.library.converter.impl;

import com.itech.library.converter.DtoConverter;
import com.itech.library.dto.AuthorDto;
import com.itech.library.dto.BookDto;
import com.itech.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter implements DtoConverter<BookDto, Book> {

    @Autowired
    private AuthorDtoConverter authorDtoConverter;

    @Override
    public BookDto entityToDto(Book book) {
        if (book != null) {
            AuthorDto authorDto = authorDtoConverter.entityToDto(book.getAuthor());

            BookDto bookDto = new BookDto.Builder()
                    .setId(book.getId())
                    .setTitle(book.getTitle())
                    .setCount(book.getCount())
                    .setYear(book.getYear())
                    .setAuthor(authorDto)
                    .build();
            return bookDto;
        }
        return null;
    }

    @Override
    public Book dtoToEntity(BookDto book) {
        if (book != null) {
            Book bookEntity = new Book(book.getTitle(), book.getYear(), book.getCount());
            if (book.getAuthor() != null) {
                bookEntity.setAuthor(authorDtoConverter.dtoToEntity(book.getAuthor()));
            }
            return bookEntity;
        }
        return null;
    }
}
