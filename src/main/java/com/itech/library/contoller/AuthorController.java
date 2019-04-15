package com.itech.library.contoller;

import com.itech.library.Constant;
import com.itech.library.dto.AuthorDto;
import com.itech.library.entity.Author;
import com.itech.library.exeption.AuthorNotFoundException;
import com.itech.library.exeption.DeleteAuthorContainBookException;
import com.itech.library.service.AuthorService;
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
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping("/author/")
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return Constant.View.Author.ALL;
    }

    @GetMapping("/author/{id}")
    public String getAuthor(@PathVariable("id") Integer id, Model model) {
        String view;
        Optional<Author> author = authorService.getAuthorById(id);

        if (author.isPresent()) {
            model.addAttribute("author", author.get());
            model.addAttribute("books", authorService.getBooksByAuthorId(author.get().getId()));
            view = Constant.View.Author.ONE;
        } else {
            model.addAttribute("error", "Author with Id = " + id + " don't find");
            view = Constant.View.Error.ERROR_AUTHOR;
        }
        return view;
    }


    // Delete Author
    @PostMapping("/author/{id}/delete")
    public String deleteAuthor(@PathVariable("id") Integer id, Model model) {
        String view;

        try {
            Author author = authorService.deleteAuthor(id);
            model.addAttribute("message", "Author '" + author.toString() + "' was successful delete");
            view = Constant.View.Success.SUCCESS_AUTHOR;
        } catch (DeleteAuthorContainBookException | AuthorNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            view = Constant.View.Error.ERROR_AUTHOR;
        }
        return view;
    }


    // Update Author
    @GetMapping("/author/{id}/update")
    public String updateAuthor(@PathVariable("id") Integer id, Model model) {
        String view;
        Optional<Author> author = authorService.getAuthorById(id);

        if (author.isPresent()) {
            model.addAttribute("author", author.get());
            view = Constant.View.Author.UPDATE;
        } else {
            model.addAttribute("error", "Author with Id = " + id + " don't find");
            view = Constant.View.Error.ERROR_AUTHOR;
        }

        return view;
    }

    @PostMapping("/author/{id}/update")
    public String updateAuthor(@Valid AuthorDto authorDto, BindingResult result, Model model) {
        String view;

        if (result.hasErrors()) {
            model.addAttribute("author", authorDto);
            model.addAttribute("errors", result.getAllErrors());
            view = Constant.View.Author.UPDATE;
        } else {

            try {
                Author author = authorService.updateAuthor(authorDto);
                model.addAttribute("message", "Author '" + author.toString() + "' was successful Update");
                view = Constant.View.Success.SUCCESS_AUTHOR;
            } catch (AuthorNotFoundException e) {
                model.addAttribute("error", e.getMessage());
                view = Constant.View.Error.ERROR_AUTHOR;
            }
        }
        return view;
    }


    // Create Author
    @GetMapping("/author/create")
    public String createAuthor(Model model) {
        model.addAttribute("author", new Author());
        return Constant.View.Author.CREATE;
    }

    @PostMapping("/author/create")
    public String createAuthor(@Valid AuthorDto authorDto, BindingResult result, Model model) {
        String view;

        if (result.hasErrors()) {
            model.addAttribute("author", authorDto);
            model.addAttribute("errors", result.getAllErrors());
            view = Constant.View.Author.CREATE;
        } else {
            Author author = authorService.addAuthor(authorDto);
            model.addAttribute("message", "Author '" + author.toString() + "' was successful Create");
            view = Constant.View.Success.SUCCESS_AUTHOR;
        }
        return view;
    }
}
