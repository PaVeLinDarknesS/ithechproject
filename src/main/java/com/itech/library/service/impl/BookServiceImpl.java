package com.itech.library.service.impl;

import com.itech.library.converter.impl.BookDtoConverter;
import com.itech.library.dto.BookDto;
import com.itech.library.entity.Book;
import com.itech.library.exeption.BookNotFoundException;
import com.itech.library.exeption.DeleteBookHaveByUserException;
import com.itech.library.repository.BookRepository;
import com.itech.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
        if (!StringUtils.isEmpty(firstName) && !StringUtils.isEmpty(lastName)) {
            books = bookRepository.getBooksByAuthorFio(firstName, lastName);
        }
        return books;
    }

    @Override
    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.getBookByTitle(title);
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public Book addBook(BookDto book) {
        Optional<Book> findBook = bookRepository.getBookByTitle(book.getTitle());
        if (findBook.isPresent()) {
            return findBook.get();
        } else {
            Book addBook = bookRepository.addBook(bookConverter.dtoToEntity(book));
            return addBook;
        }
    }

    @Override
    public Book updateBook(BookDto book) throws BookNotFoundException {
        Optional<Book> findBookOptional = bookRepository.getBookById(book.getId());
        if (findBookOptional.isPresent()) {
            Book findBook = findBookOptional.get();

            findBook.setTitle(book.getTitle());
            findBook.setCount(book.getCount());
            findBook.setYear(book.getYear());

            return bookRepository.updateBook(findBook);
        }
        throw new BookNotFoundException("Book with id_" + book.getId() + "_ don't found");
    }

    @Override
    public Book deleteBook(BookDto book) throws BookNotFoundException, DeleteBookHaveByUserException {
        Optional<Book> findBookOptional = bookRepository.getBookById(book.getId());
        if (findBookOptional.isPresent()) {
            Book findBook = findBookOptional.get();
            if (!CollectionUtils.isEmpty(findBook.getUsers())) {
                throw new DeleteBookHaveByUserException("Book with id_" + book.getId() + "_ have some user");
            }
            Book deleteBook = bookRepository.deleteBook(findBook);
            return deleteBook;
        }
        throw new BookNotFoundException("Book with id_" + book.getId() + "_ not found");
    }
}