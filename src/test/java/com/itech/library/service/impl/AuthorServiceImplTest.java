package com.itech.library.service.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.service.AuthorService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;
}