package com.itech.library.service.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.dto.UserDto;
import com.itech.library.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserByLogin() {
        Optional<UserDto> userPojo = userService.getUserByLogin("Admin");
        Assert.assertTrue(userPojo.isPresent());
    }
}