package com.itech.library.service.impl;

import com.itech.library.converter.impl.BookPojoConverter;
import com.itech.library.entity.Book;
import com.itech.library.pojo.BookPojo;
import com.itech.library.repository.BookRepository;
import com.itech.library.repository.UserRepository;
import com.itech.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    //TODO Write
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookPojoConverter bookConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BookPojo> getAllBooks() {
        List<Book> booksFromBd = bookRepository.getAllBooks();
        List<BookPojo> books = new ArrayList<>();
        booksFromBd.stream().forEach(book ->
                books.add(bookConverter.entityToPojo(book)));
        return books;
    }

    @Override
    public List<BookPojo> getBooksByAuthorFio(String firstName, String lastName) {
        List<BookPojo> books = new ArrayList<>();
        if (firstName != null && lastName != null && firstName.length() > 0 && lastName.length() > 0) {
            List<Book> booksEntity = bookRepository.getBooksByAuthorFio(firstName, lastName);
            booksEntity.stream().forEach(book -> books.add(bookConverter.entityToPojo(book)));
        }
        return books;
    }

    @Override
    public Optional<BookPojo> getBookByTitle(String title) {
        if (title != null && title.length() > 0) {
            Optional<Book> bookEntity = bookRepository.getBookByTitle(title);
            if (bookEntity.isPresent()) {
                BookPojo bookPojo = bookConverter.entityToPojo(bookEntity.get());
                return Optional.of(bookPojo);
            }
        }
        return Optional.empty();
    }


    @Override
    public BookPojo addBook(BookPojo book) {
        Optional<Book> findBook = bookRepository.getBookByTitle(book.getTitle());
        if (findBook.isPresent()) {
            BookPojo bookPojo = bookConverter.entityToPojo(findBook.get());
            bookPojo.setId(0);
            return bookPojo;
        } else {
            Book addBook = bookRepository.addBook(bookConverter.pojoToEntity(book));
            return bookConverter.entityToPojo(addBook);
        }
    }

    @Override
    public BookPojo updateBook(BookPojo book) {
        if (book.getId() != null) {
            Optional<Book> findBookOptional = bookRepository.getBookById(book.getId());
            if (findBookOptional.isPresent()) {
                Book convertBook = bookConverter.pojoToEntity(book);
                Book findBook = findBookOptional.get();
                findBook.setTitle(convertBook.getTitle());
                findBook.setCount(convertBook.getCount());
                findBook.setYear(convertBook.getYear());
                findBook.setAuthor(convertBook.getAuthor());
//            findBook.setUsers(convertBook.getUsers());
                Book updateBook = bookRepository.updateBook(findBook);
                return bookConverter.entityToPojo(updateBook);
            }
        }
        return new BookPojo(0);
    }

    @Override
    public BookPojo deleteBook(BookPojo book) {
        Optional<Book> findBookOptional = bookRepository.getBookByTitle(book.getTitle());
        if (findBookOptional.isPresent()) {
            Book findBook = findBookOptional.get();
            if (findBook.getUsers() != null && findBook.getUsers().size() > 0) {
                return new BookPojo(0);
            }
            Book deleteBook = bookRepository.deleteBook(findBook);
            return bookConverter.entityToPojo(deleteBook);
        }
        return new BookPojo(-1);
    }


}
