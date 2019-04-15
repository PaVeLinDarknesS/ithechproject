package com.itech.library.contoller;

import com.itech.library.Constant;
import com.itech.library.exeption.BookCountLessZeroExeption;
import com.itech.library.exeption.BookNotFoundException;
import com.itech.library.exeption.TakeSameBookExeption;
import com.itech.library.exeption.UserNotFoundException;
import com.itech.library.service.BookService;
import com.itech.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage() {
        return "redirect:/book/";
    }


    // Add Book in User
    @PostMapping(value = "/book/user/add")
    public String addBooksInUser(Authentication authentication, Integer[] books, Model model) {

        List<String> addError = new LinkedList<>();
        if (books != null) {
            for (Integer book : books) {
                try {
                    userService.addBookInUser(book, authentication.getName());
                } catch (BookCountLessZeroExeption | TakeSameBookExeption e) {
                    addError.add(e.getMessage());
                }
            }
        } else {
            addError.add("You have chosen nothing");
        }
        model.addAttribute("errors", addError);
        model.addAttribute("books", bookService.getAllBooks());

        return Constant.View.Book.ALL;
    }


    @GetMapping(value = "/book/user")
    public String getBooksInUser(Authentication authentication, Model model) {

        String view;
        try {
            model.addAttribute("books", userService.getAllBookFromUserLogin(authentication.getName()));
            view = Constant.View.Book.BOOKS_IN_USER;
        } catch (UserNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            view = Constant.View.Error.ERROR_BOOK;
        }
        return view;
    }

    // Delete Book in User
    @PostMapping(value = "/book/user/delete")
    public String deleteBooksInUser(Authentication authentication, Integer[] books, Model model) {
        String view;
        List<String> deleteError = new LinkedList<>();

        if (books != null) {
            for (Integer book : books) {
                try {
                    userService.removeBookInUser(book, authentication.getName());
                } catch (BookNotFoundException e) {
                    deleteError.add(e.getMessage());
                }
            }
        } else {
            deleteError.add("You have chosen nothing");
        }

        try {
            model.addAttribute("books", userService.getAllBookFromUserLogin(authentication.getName()));
            model.addAttribute("errors", deleteError);
            view = Constant.View.Book.BOOKS_IN_USER;
        } catch (UserNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            view = Constant.View.Error.ERROR_BOOK;
        }
        return view;
    }

}
