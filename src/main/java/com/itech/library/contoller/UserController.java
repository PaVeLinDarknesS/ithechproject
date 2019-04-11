package com.itech.library.contoller;

import com.itech.library.Constant;
import com.itech.library.dto.UserDto;
import com.itech.library.exeption.BookCountLessZeroExeption;
import com.itech.library.exeption.TakeSameBookExeption;
import com.itech.library.exeption.UserNotFoundException;
import com.itech.library.service.BookService;
import com.itech.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes("userKey")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    //@GetMapping({"login", ""})
    private String login() {
        return Constant.View.User.LOGIN;
    }

    //@PostMapping("login")
    private String login(@Valid UserDto userDto, BindingResult result, HttpSession session, Model model) {
        String view;

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            model.addAttribute("errors", result.getAllErrors());
            view = Constant.View.User.LOGIN;
        } else if (userService.checkExistUser(userDto)) {
            session.invalidate();
            model.addAttribute("userKey", userDto.getLogin());
            view = Constant.View.User.LOGIN;

        } else {
            model.addAttribute("message", "Invalid login or password");
            view = Constant.View.User.LOGIN;
        }
        return view;
    }


    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is welcome page!");
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(Authentication authentication) {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page - Admin Page!");
        System.out.println(authentication.getName());
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "/dba**", method = RequestMethod.GET)
    public ModelAndView dbaPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page - Database Page!");
        model.setViewName("admin");

        return model;

    }

    // Add Book in User
    @PostMapping(value = "/user/book/add")
    public String addBooksInUser(Authentication authentication, Integer[] books, Model model) {

        List<String> addError = new LinkedList<>();
        for (int i = 0; i < books.length; i++) {
            try {
                userService.addBookInUser(books[i], authentication.getName());
            } catch (BookCountLessZeroExeption | TakeSameBookExeption e) {
                addError.add(e.getMessage());
            }
        }
        model.addAttribute("errors", addError);
        model.addAttribute("books", bookService.getAllBooks());

        return Constant.View.Book.ALL;

    }


    // Delete Book in User
    @GetMapping(value = "/user/book/")
    public String getBooksInUser(Authentication authentication, Model model) {

        String view;
        try {
            model.addAttribute("books", userService.getAllBookFromUserLogin(authentication.getName()));
            view = Constant.View.Book.ALL_IN_USER;
        } catch (UserNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            view = Constant.View.Error.ERROR_BOOK;
        }
        return view;

    }

    @PostMapping(value = "/user/book/delete")
    public String deleteBooksInUser(Authentication authentication, Integer[] books, Model model) {
        String view;

        for (int i = 0; i < books.length; i++) {
            userService.removeBookInUser(books[i], authentication.getName());
        }

        try {
            model.addAttribute("books", userService.getAllBookFromUserLogin(authentication.getName()));
            view = Constant.View.Book.ALL_IN_USER;
        } catch (UserNotFoundException e) {

            model.addAttribute("error", e.getMessage());
            view = Constant.View.Error.ERROR_BOOK;
        }
        return Constant.View.Book.ALL_IN_USER;

    }

}
