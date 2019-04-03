package com.itech.library.contoller;

import com.itech.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/allBooks")
    public ModelAndView getAllBook() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books", bookService.getAllBooks());
        modelAndView.setViewName("allBook");
        return modelAndView;
    }

    @GetMapping("/getBook/{id}")
    public ModelAndView getBookById(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("book", bookService.getBookById(id));
        modelAndView.setViewName("oneBook");
        return modelAndView;
    }


}
