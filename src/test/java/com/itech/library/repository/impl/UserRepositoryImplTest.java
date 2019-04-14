package com.itech.library.repository.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.entity.Book;
import com.itech.library.entity.User;
import com.itech.library.repository.BookRepository;
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

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void getUserById_ifUserFind_returnUserOptional() {
        Optional<User> user = userRepository.getUserById(1);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void getUserById_ifUserNotFind_returnEmptyOptional() {
        Optional<User> user = userRepository.getUserById(0);
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void getUserByLogin_ifUserFind_returnUserOptional() {
        Optional<User> user = userRepository.getUserByLogin("Admin");
        Assert.assertTrue(user.isPresent());
        Assert.assertEquals("Admin", user.get().getLogin());
    }

    @Test
    public void getUserByLogin_ifUserNotFind_returnEmptyOptional() {
        Optional<User> user = userRepository.getUserByLogin("");
        Assert.assertFalse(user.isPresent());
    }


    @Test
    public void addUser_returnNewUser() {
        User user = userRepository.addUser(new User("UserForTest", "UserForTest"));
        Optional<User> getAddUser = userRepository.getUserByLogin("UserForTest");
        Assert.assertTrue(getAddUser.isPresent());
        Assert.assertEquals(user, getAddUser.get());
    }

    @Test
    public void updateUser_ifUserExist_returnUpdateUser() {
        User updateUser = userRepository.getUserById(2).get();
        updateUser.setLogin("NewUser");
        userRepository.updateUser(updateUser);
        Assert.assertEquals("NewUser", userRepository.getUserById(2).get().getLogin());
    }

    @Test
    public void deleteUser_returnDeleteUser() {
        User deleteUser = userRepository.getUserById(2).get();
        userRepository.deleteUser(deleteUser);
        Assert.assertFalse(userRepository.getUserById(2).isPresent());
    }

    @Test
    public void addBookInUser() {
        Book book = bookRepository.getBookById(2).get();
        int count = book.getCount();
        User user = userRepository.getUserById(2).get();
        int countBookInUser = user.getBooks().size();
        Assert.assertTrue(userRepository.addBookInUser(book, user));

        Assert.assertEquals(count - 1, book.getCount().intValue());
        Assert.assertEquals(countBookInUser + 1, user.getBooks().size());
    }

    @Test
    public void removeBookInUser() {
        Book book = bookRepository.getBookById(2).get();
        int count = book.getCount();
        User user = userRepository.getUserById(1).get();
        int countBookInUser = user.getBooks().size();
        userRepository.removeBookInUser(book, user);

        Assert.assertEquals(count + 1, book.getCount().intValue());
        Assert.assertEquals(countBookInUser - 1, user.getBooks().size());
    }
}
