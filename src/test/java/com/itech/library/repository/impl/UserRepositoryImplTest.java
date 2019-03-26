package com.itech.library.repository.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.entity.User;
import com.itech.library.repository.UserRepository;
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
public class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUserByIdPositive() {
        Optional<User> user = userRepository.getUserById(1);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void getUserByIdNegative() {
        Optional<User> user = userRepository.getUserById(0);
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void getUserByLoginPositive() {
        Optional<User> user = userRepository.getUserByLogin("Admin");
        Assert.assertTrue(user.isPresent());
        Assert.assertEquals("Admin", user.get().getLogin());
    }

    @Test
    public void getUserByLoginNegative() {
        Optional<User> user = userRepository.getUserByLogin("");
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void addUserPositive() {
        User addUser = new User("UserForTest", "UserForTest");
        User user = userRepository.addUser(addUser);
        Optional<User> getAddUser = userRepository.getUserByLogin("UserForTest");
        Assert.assertTrue(getAddUser.isPresent());
        Assert.assertEquals(user, getAddUser.get());
    }

    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    public void addUserNegative_AddExistUser() {
        User addUser = new User("Admin", "12345678");
        User user = userRepository.addUser(addUser);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserNegative_AddNull() {
        User user = userRepository.addUser(null);
    }

    @Test
    public void findOnePositive() {
        User oneUser = new User("Admin", "Admin");
        Optional<User> user = userRepository.findOne(oneUser);
        Assert.assertTrue(user.isPresent());
        Assert.assertEquals(oneUser.getLogin(), user.get().getLogin());
        Assert.assertEquals(oneUser.getPassword(), user.get().getPassword());
    }

    @Test
    public void findOneNegative() {
        User oneUser = new User("T", "T");
        Optional<User> user = userRepository.findOne(oneUser);
        Assert.assertFalse(user.isPresent());
    }
}