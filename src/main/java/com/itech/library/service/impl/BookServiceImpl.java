package com.itech.library.service.impl;

import com.itech.library.converter.impl.BookDtoConverter;
import com.itech.library.dto.BookDto;
import com.itech.library.entity.Book;
import com.itech.library.repository.BookRepository;
import com.itech.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDtoConverter bookConverter;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public List<Book> getBooksByAuthorFio(String firstName, String lastName) {
        List<Book> books = new ArrayList<>();
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            books = bookRepository.getBooksByAuthorFio(firstName, lastName);
        }
        return books;
    }

    @Override
    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.getBookByTitle(title);
    }


    @Override
    public BookDto addBook(BookDto book) {
        Optional<Book> findBook = bookRepository.getBookByTitle(book.getTitle());
        if (findBook.isPresent()) {
            BookDto bookDto = bookConverter.entityToPojo(findBook.get());
            bookDto.setId(0);
            return bookDto;
        } else {
            Book addBook = bookRepository.addBook(bookConverter.pojoToEntity(book));
            return bookConverter.entityToPojo(addBook);
        }
    }

    @Override
    @Transactional
    public BookDto updateBook(BookDto book) {
        Optional<Book> findBookOptional = bookRepository.getBookById(book.getId());
        if (findBookOptional.isPresent()) {
            Book findBook = findBookOptional.get();

            findBook.setTitle(book.getTitle());
            findBook.setCount(book.getCount());
            findBook.setYear(book.getYear());

            Book updateBook = bookRepository.updateBook(findBook);
            return bookConverter.entityToPojo(updateBook);
        }
        return new BookDto(-1);
    }

    @Override
    public BookDto deleteBook(BookDto book) {
        Optional<Book> findBookOptional = bookRepository.getBookById(book.getId());
        if (findBookOptional.isPresent()) {
            Book findBook = findBookOptional.get();
            if (findBook.getUsers() != null && findBook.getUsers().size() > 0) {
                return new BookDto(0);
            }
            Book deleteBook = bookRepository.deleteBook(findBook);
            return bookConverter.entityToPojo(deleteBook);
        }
        return new BookDto(-1);
    }
}