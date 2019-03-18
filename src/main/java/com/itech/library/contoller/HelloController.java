package com.itech.library.contoller;

import com.itech.library.entity.User;
import com.itech.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/hello")
    public String getHelloPage() {
        userRepository.getUserById(1);
        userRepository.getUserByLogin("Admin");
        userRepository.addUser(new User("test", "test"));
        userRepository.findOne(new User("test", "test"));
        return "hello";
    }

}
