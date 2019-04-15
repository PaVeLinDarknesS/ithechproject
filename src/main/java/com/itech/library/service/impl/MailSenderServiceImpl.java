package com.itech.library.service.impl;

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
import java.util.Properties;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final static String protocol = "smtps";
    private final static String host = "smtp.gmail.com";
    private final static String user = "JavaSpringPavel@gmail.com";
    private final static String password = "PaVeLtestAPP1";

    // TODO: TLS vs SSL

    @Override
    public boolean notifyUserTakeBook(List<Book> addBook, User user) {
        boolean result;
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.sendpartial", "true");

        Session session = Session.getDefaultInstance(props);
        //создаем сообщение
        MimeMessage message = new MimeMessage(session);

        try {
            message.setSubject("Notify about add Book!");

            // TODO Message
            message.setText("Asta la vista, baby!");

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSentDate(new Date());


            Transport transport = null;
            transport = session.getTransport();
            transport.connect("smtp.gmail.com", 465, this.user, password);

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            result = true;
        } catch (MessagingException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}
