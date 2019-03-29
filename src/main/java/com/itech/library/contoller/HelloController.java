package com.itech.library.contoller;

import com.itech.library.pojo.BookPojo;
import com.itech.library.repository.AuthorRepository;
import com.itech.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class HelloController {

    private static final Header header = new Header("Content-Type", "application/json");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<?> getHelloPage() {
        return new ResponseEntity<>(new BookPojo.Builder().setTitle("as").build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.POST)
    public ResponseEntity<?> getHelloPage1(@Valid @RequestBody BookPojo bookPojo) {
        return new ResponseEntity<>(bookPojo, HttpStatus.OK);
    }

}
