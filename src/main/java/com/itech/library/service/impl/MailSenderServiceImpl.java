package com.itech.library.service.impl;

import com.itech.library.Constant;
import com.itech.library.entity.Author;
import com.itech.library.entity.Book;
import com.itech.library.entity.User;
import com.itech.library.service.MailSenderService;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Override
    public boolean notifyUserTakeBook(List<Book> addBook, User user) {
        boolean result;
        //return composeMessage(addBook, user.getLogin());
        Properties props = new Properties();
        props.put("mail.transport.protocol", Constant.Message.PROTOCOL);
        props.put("mail.smtps.host", Constant.Message.HOST);
        props.put("mail.smtps.auth", Constant.Message.AUTH);
        props.put("mail.smtp.sendpartial", Constant.Message.SENDPARTIAL);

        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);

        try {
            message.setSubject("Notify about add Book!");
            message.setText(composeMessage(addBook, user.getLogin()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSentDate(new Date());


            Transport transport = session.getTransport();
            transport.connect(
                    Constant.Message.HOST,
                    465,
                    Constant.Message.LOGIN,
                    Constant.Message.PASSWORD);

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            result = true;
        } catch (MessagingException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    private String composeMessage(List<Book> books, String login) {
        StringBuilder message = new StringBuilder("Dear '" + login + "'! \n\tYou have added the following books:\n");

        for (int i = 0; i < books.size(); i++) {
            message.append(i + 1)
                    .append(". ")
                    .append(books.get(i).toString())
                    .append(", ")
                    .append(Optional.ofNullable(books.get(i).getAuthor()).orElse(new Author("unknown", "")))
                    .append('\n');
        }
        return message.toString();
    }
}
