package io.github.madrigal.email;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHelper {
    public static MimeMessage newMessage(String subject, String email) throws MessagingException {
        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject, StandardCharsets.UTF_8.name());

        InternetAddress address = new InternetAddress(email);

        message.setFrom(address);
        message.setReplyTo(new Address[]{address});
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        return message;
    }
}
