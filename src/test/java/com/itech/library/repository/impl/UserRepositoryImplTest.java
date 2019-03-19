package com.itech.library.repository.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.entity.User;
import com.itech.library.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration(classes = WebConfig.class)
@ContextConfiguration(classes = {WebConfig.class})
//@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {WebConfig.class, HibernateConfig.class, UserRepositoryImpl.class})
public class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
//        userRepository.getUserById(1);
//        userRepository.getUserByLogin("Admin");
//        userRepository.addUser(new User("test", "test"));
//        userRepository.findOne(new User("test", "test"));
    }

    @After
    public void tearDown() throws Exception {
    }

    /****/
    @Test
    public void getUserByIdPositive() {
        Optional<User> user = userRepository.getUserById(1);
        Assert.assertTrue(user.isPresent());

    }

    @Test
    public void getUserByIdNegative() {
    }

    @Test
    public void getUserByLogin() {
    }

    @Test
    public void addUser() {
    }

    @Test
    public void findOne() {
    }
}