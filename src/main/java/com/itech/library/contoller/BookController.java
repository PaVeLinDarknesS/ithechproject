package com.itech.library.contoller;

import com.itech.library.Constant;
import com.itech.library.dto.BookDto;
import com.itech.library.entity.Book;
import com.itech.library.exeption.BookNotFoundException;
import com.itech.library.exeption.DeleteBookHaveByUserException;
import com.itech.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    // Get Book
    @GetMapping("/book/")
    public ModelAndView getAllBook(ServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books", bookService.getAllBooks());
        modelAndView.setViewName(Constant.View.Book.ALL);
        return modelAndView;
    }

    @GetMapping("/book/{id}")
    public ModelAndView getBookById(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            modelAndView.addObject("book", book.get());
            modelAndView.setViewName(Constant.View.Book.ONE);
        } else {
            modelAndView.addObject("error", "Book with Id = " + id + " don't find");
            modelAndView.setViewName(Constant.View.Error.ERROR_BOOK);
        }
        return modelAndView;
    }


    // Delete Book
    @PostMapping("/book/{id}/delete")
    public ModelAndView deleteBook(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            Book book = bookService.deleteBook(id);
            modelAndView.setViewName(Constant.View.Success.SUCCESS_BOOK);
            modelAndView.addObject("message", "Book '" + book.getTitle() + "' was successful delete");
        } catch (BookNotFoundException | DeleteBookHaveByUserException e) {
            modelAndView.setViewName(Constant.View.Error.ERROR_BOOK);
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }


    // Update Book
    @GetMapping("/book/{id}/update")
    public ModelAndView updateBookById(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            modelAndView.addObject("book", book.get());
            modelAndView.setViewName(Constant.View.Book.UPDATE);
        } else {
            modelAndView.addObject("error", "Book with Id = " + id + " don't find");
            modelAndView.setViewName(Constant.View.Error.ERROR_BOOK);
        }

        return modelAndView;
    }

    @PostMapping("/book/{id}/update")
    public ModelAndView updateBook(@Valid BookDto bookDto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.addObject("book", bookDto);
            modelAndView.addObject("errors", result.getAllErrors());
            modelAndView.setViewName(Constant.View.Book.UPDATE);
        } else {

            try {
                Book book = bookService.updateBook(bookDto);
                modelAndView.setViewName(Constant.View.Success.SUCCESS_BOOK);
                modelAndView.addObject("message", "Book '" + book.getTitle() + "' was successful Update");
            } catch (BookNotFoundException e) {
                modelAndView.addObject("error", e.getMessage());
                modelAndView.setViewName(Constant.View.Error.ERROR_BOOK);
            }
        }
        return modelAndView;
    }


    // Create Book
    @GetMapping("/book/create")
    public ModelAndView showAddBookPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("book", new Book());
        modelAndView.setViewName(Constant.View.Book.CREATE);
        return modelAndView;
    }

    @PostMapping("/book/create")
    public ModelAndView addBook(@Valid BookDto bookDto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.addObject("book", bookDto);
            modelAndView.addObject("errors", result.getAllErrors());
            modelAndView.setViewName(Constant.View.Book.CREATE);
        } else {
            Book book = bookService.addBook(bookDto);
            modelAndView.setViewName(Constant.View.Success.SUCCESS_BOOK);
            modelAndView.addObject("message", "Book '" + book.getTitle() + "' was successful Create");
        }
        return modelAndView;
    }

}
