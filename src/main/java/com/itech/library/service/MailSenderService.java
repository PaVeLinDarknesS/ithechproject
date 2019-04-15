package com.itech.library.service;

import com.itech.library.entity.Book;
import com.itech.library.entity.User;

import java.util.List;

public interface MailSenderService {

    boolean notifyUserTakeBook(List<Book> addBook, User user);
}
