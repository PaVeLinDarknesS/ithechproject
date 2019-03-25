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
        return "hello";
    }

}
