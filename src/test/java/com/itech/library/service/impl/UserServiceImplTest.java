package com.itech.library.service.impl;

import com.itech.library.config.WebConfig;
import com.itech.library.dto.BookDto;
import com.itech.library.dto.UserDto;
import com.itech.library.entity.User;
import com.itech.library.exeption.UserExistException;
import com.itech.library.exeption.UserNotFoundException;
import com.itech.library.repository.BookRepository;
import com.itech.library.repository.UserRepository;
import com.itech.library.service.UserService;
import org.junit.Assert;
import org.junit.Ignore;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void getUserByLogin() {
        Optional<User> user = userService.getUserByLogin("Admin");
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void removeAllBookInUser() {
        UserDto userDto = new UserDto.Builder().setLogin("Admin").build();

        userService.removeAllBookInUser(userDto.getLogin());

        Assert.assertEquals(0, userService.getUserByLogin("Admin").get().getBooks().size());
    }

    @Test
    public void checkExistUser() {
        UserDto userDto = new UserDto.Builder().setLogin("User").setPassword("User").build();
        boolean receive = userService.checkExistUser(userDto);
        //Assert.assertTrue(receive);
    }

    @Test
    public void addUser() throws UserExistException {
        UserDto userDto = new UserDto.Builder()
                .setLogin("Admin")
                .setPassword("Admin")
                .build();

        //Assert.assertNull(userService.addUser(userDto));
    }

    @Test
    public void updateUser() {
        UserDto userDto = new UserDto.Builder()
                .setId(2)
                .setLogin("newUser")
                .setPassword("Admin")
                .build();
        //Assert.assertTrue(userService.getUserByLogin("newUser").isPresent());
    }

    @Test
    @Ignore
    public void deleteUser() throws UserNotFoundException {
        UserDto userDto = new UserDto.Builder()
                .setId(2)
                .setLogin("User")
                .setPassword("User")
                .build();
        Assert.assertEquals(userDto.getId(), userService.deleteUser(userDto).getId());
    }

    @Test
    public void addBookInUser() {
     /*   BookDto bookDto = new BookDto.Builder()
                .setTitle("Title3").build();

        UserDto userDto = new UserDto.Builder()
                .setLogin("User")
                .setPassword("User")
                .build();

        int expected = userService.getUserByLogin("User").get().getBooks().size();
        userService.addBookInUser(bookDto, userDto);
        int received = userService.getUserByLogin("User").get().getBooks().size();

        Assert.assertEquals(expected + 1, received);*/
    }

    @Test
    public void removeBookInUser() {
       /* BookDto bookDto = new BookDto.Builder()
                .setTitle("Title2").build();

        UserDto userDto = new UserDto.Builder()
                .setLogin("Admin")
                .setPassword("Admin")
                .build();

        int expected = userService.getUserByLogin("Admin").get().getBooks().size();
        userService.removeBookInUser(bookDto, userDto);
        int received = userService.getUserByLogin("Admin").get().getBooks().size();

        Assert.assertEquals(expected - 1, received);*/
    }
}