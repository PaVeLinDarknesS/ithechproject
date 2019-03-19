package com.itech.library.contoller;

import com.itech.library.entity.Author;
import com.itech.library.entity.User;
import com.itech.library.repository.AuthorRepository;
import com.itech.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class HelloController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping("/hello")
    public String getHelloPage() {
        userRepository.getUserById(1);
        userRepository.getUserByLogin("Admin");
        userRepository.addUser(new User("test", "test"));
        userRepository.findOne(new User("test", "test"));
        return "hello";
    }

    @RequestMapping("/testBook")
    public String testBook() {
        Optional<Author> authorId1 = authorRepository.getAuthorById(1);
        Optional<Author> authorId0 = authorRepository.getAuthorById(0);

        Author addAut = new Author("Test", "Test");
        Author authorAdd1 = authorRepository.addAuthor(addAut);
        Author authorAdd2 = authorRepository.addAuthor(addAut);
        authorAdd1.setFirstName("Temp");
        Author updateAuthor = authorRepository.updateAuthor(authorAdd1);
//        Author deleteAuthor = authorRepository.deleteAuthor(updateAuthor);
        return "hello";
    }

}
