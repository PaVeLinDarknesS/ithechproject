package com.itech.library.contoller;

import com.itech.library.Constant;
import com.itech.library.entity.Book;
import com.itech.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

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
            modelAndView.setViewName(Constant.View.Error.FINDBOOK);
        }
        return modelAndView;
    }

    @GetMapping("/book/{id}/update")
    public ModelAndView updateBookById(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("error", "Book with Id = " + id + " don't find");
        modelAndView.setViewName(Constant.View.Error.FINDBOOK);

        return modelAndView;
    }

    @GetMapping("/bookTest/")
    public ModelAndView test(ServletRequest request) {
        //return new ModelAndView("redirect:"+"/book/");
        return new ModelAndView(Constant.View.HELLO);
    }

}
