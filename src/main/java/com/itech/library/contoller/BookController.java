package com.itech.library.contoller;

import com.itech.library.Constant;
import com.itech.library.dto.BookDto;
import com.itech.library.entity.Book;
import com.itech.library.exeption.BookNotFoundException;
import com.itech.library.exeption.DeleteBookHaveByUserException;
import com.itech.library.service.AuthorService;
import com.itech.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    // Get Book
    @GetMapping("/book/")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return Constant.View.Book.ALL;
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable("id") Integer id, Model model) {
        String view;
        Optional<Book> book = bookService.getBookById(id);

        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            view = Constant.View.Book.ONE;
        } else {
            model.addAttribute("error", "Book with Id = " + id + " don't find");
            view = Constant.View.Error.ERROR_BOOK;
        }
        return view;
    }


    // Delete Book
    @PostMapping("/book/{id}/delete")
    public String deleteBook(@PathVariable("id") Integer id, Model model) {
        String view;

        try {
            Book book = bookService.deleteBook(id);
            model.addAttribute("message", "Book '" + book.getTitle() + "' was successful delete");
            view = Constant.View.Success.SUCCESS_BOOK;
        } catch (BookNotFoundException | DeleteBookHaveByUserException e) {
            model.addAttribute("error", e.getMessage());
            view = Constant.View.Error.ERROR_BOOK;
        }
        return view;
    }


    // Update Book
    @GetMapping("/book/{id}/update")
    public String updateBook(@PathVariable("id") Integer id, Model model) {
        String view;
        Optional<Book> book = bookService.getBookById(id);

        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("authors", authorService.getAllAuthors());
            view = Constant.View.Book.UPDATE;
        } else {
            model.addAttribute("error", "Book with Id = " + id + " don't find");
            view = Constant.View.Error.ERROR_BOOK;
        }

        return view;
    }

    @PostMapping("/book/{id}/update")
    public String updateBook(@Valid BookDto bookDto, BindingResult result, Model model) {
        String view;

        if (result.hasErrors()) {
            model.addAttribute("book", bookDto);
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("errors", result.getAllErrors());
            view = Constant.View.Book.UPDATE;
        } else {

            try {
                Book book = bookService.updateBook(bookDto);
                model.addAttribute("message", "Book '" + book.getTitle() + "' was successful Update");
                view = Constant.View.Success.SUCCESS_BOOK;
            } catch (BookNotFoundException e) {
                model.addAttribute("error", e.getMessage());
                view = Constant.View.Error.ERROR_BOOK;
            }
        }
        return view;
    }


    // Create Book
    @GetMapping("/book/create")
    public String createBook(Model model) {
        model.addAttribute("book", new BookDto());
        model.addAttribute("authors", authorService.getAllAuthors());
        return Constant.View.Book.CREATE;
    }

    @PostMapping("/book/create")
    public String createBook(@Valid BookDto bookDto, BindingResult result, Model model) {
        String view;

        if (result.hasErrors()) {
            model.addAttribute("book", bookDto);
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("errors", result.getAllErrors());
            view = Constant.View.Book.CREATE;
        } else {
            Book book = bookService.addBook(bookDto);
            model.addAttribute("message", "Book '" + book.getTitle() + "' was successful Create");
            view = Constant.View.Success.SUCCESS_BOOK;
        }
        return view;
    }

}