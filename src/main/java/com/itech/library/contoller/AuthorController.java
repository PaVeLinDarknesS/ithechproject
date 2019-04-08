package com.itech.library.contoller;

import com.itech.library.Constant;
import com.itech.library.dto.AuthorDto;
import com.itech.library.entity.Author;
import com.itech.library.exeption.AuthorNotFoundException;
import com.itech.library.exeption.DeleteAuthorContainBookException;
import com.itech.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping("/author/")
    public ModelAndView getAllAuthor() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("authors", authorService.getAllAuthors());
        modelAndView.setViewName(Constant.View.Author.ALL);

        return modelAndView;
    }

    @GetMapping("/author/{id}")
    public ModelAndView getAuthorById(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<Author> author = authorService.getAuthorById(id);
        if (author.isPresent()) {
            modelAndView.addObject("author", author.get());
            modelAndView.addObject("books", authorService.getBooksByAuthorId(author.get().getId()));
            modelAndView.setViewName(Constant.View.Author.ONE);
        } else {
            modelAndView.addObject("error", "Author with Id = " + id + " don't find");
            modelAndView.setViewName(Constant.View.Error.ERROR_AUTHOR);
        }

        return modelAndView;
    }


    // Delete Author
    @PostMapping("/author/{id}/delete")
    public ModelAndView deleteAuthor(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            Author author = authorService.deleteAuthor(id);
            modelAndView.setViewName(Constant.View.Success.SUCCESS_AUTHOR);
            modelAndView.addObject("message", "Author '" + author.toString() + "' was successful delete");
        } catch (DeleteAuthorContainBookException | AuthorNotFoundException e) {
            modelAndView.setViewName(Constant.View.Error.ERROR_AUTHOR);
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }


    // Update Author
    @GetMapping("/author/{id}/update")
    public ModelAndView updateAuthorById(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<Author> author = authorService.getAuthorById(id);
        if (author.isPresent()) {
            modelAndView.addObject("author", author.get());
            modelAndView.setViewName(Constant.View.Author.UPDATE);
        } else {
            modelAndView.addObject("error", "Author with Id = " + id + " don't find");
            modelAndView.setViewName(Constant.View.Error.ERROR_AUTHOR);
        }

        return modelAndView;
    }

    @PostMapping("/author/{id}/update")
    public ModelAndView updateAuthor(@Valid AuthorDto authorDto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.addObject("author", authorDto);
            modelAndView.addObject("errors", result.getAllErrors());
            modelAndView.setViewName(Constant.View.Author.UPDATE);
        } else {

            try {
                Author author = authorService.updateAuthor(authorDto);
                modelAndView.setViewName(Constant.View.Success.SUCCESS_AUTHOR);
                modelAndView.addObject("message", "Author '" + author.toString() + "' was successful Update");
            } catch (AuthorNotFoundException e) {
                modelAndView.addObject("error", e.getMessage());
                modelAndView.setViewName(Constant.View.Error.ERROR_AUTHOR);
            }
        }
        return modelAndView;
    }


    // Create Author
    @GetMapping("/author/create")
    public ModelAndView showAddAuthorPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("author", new Author());
        modelAndView.setViewName(Constant.View.Author.CREATE);
        return modelAndView;
    }

    @PostMapping("/author/create")
    public ModelAndView addBook(@Valid AuthorDto authorDto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.addObject("author", authorDto);
            modelAndView.addObject("errors", result.getAllErrors());
            modelAndView.setViewName(Constant.View.Author.CREATE);
        } else {
            Author author = authorService.addAuthor(authorDto);
            modelAndView.setViewName(Constant.View.Success.SUCCESS_AUTHOR);
            modelAndView.addObject("message", "Author '" + author.toString() + "' was successful Create");
        }
        return modelAndView;
    }
}
