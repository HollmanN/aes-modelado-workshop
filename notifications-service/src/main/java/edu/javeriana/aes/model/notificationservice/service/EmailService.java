package edu.javeriana.aes.model.notificationservice.service;

import edu.javeriana.aes.model.entities.Email;
import edu.javeriana.aes.model.entities.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender sender;
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    public EmailService(final JavaMailSender javaMailSender) {
        this.sender = javaMailSender;
    }

    public void deliver(Notification notification) {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            Email email = buildEmail(notification);
            LOG.info("Sending email notification {} to user {}", notification.getId(), email.getToEmail());
            helper.setFrom(email.getFromEmail());
            helper.setTo(email.getToEmail());
            helper.setText(email.getContent());
            helper.setSubject(email.getSubject());
            sender.send(message);
        } catch (Exception e) {
            LOG.error("Error sending email notification {}", notification, e);
        }
        LOG.info("Send email notification to user success!");
    }

    public void deliver(String notification) {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            LOG.info("Sending email notification {} to user {}", "jmiguelarroyo12@gmail.com", "jmiguelarroyo12@gmail.com");
            helper.setFrom("jmiguelarroyo12@gmail.com");
            helper.setTo("jmiguelarroyo12@gmail.com");
            helper.setText(notification);
            helper.setSubject("KAfka test");
            sender.send(message);
        } catch (Exception e) {
            LOG.error("Error sending email notification {}", notification, e);
        }
        LOG.info("Send email notification to user success!");
    }

    private Email buildEmail(final Notification notification) {
        //TODO: Connect with the client-service
        final String fromEmail = "aes-workshop@javeriana.edu.co";
        final String toEmail = "test-workshop@javeriana.edu.co";

        final String subject = notification.getMessage().getSubject();
        final String content = notification.getMessage().getContent();

        Email email = new Email();
        email.setFromEmail(fromEmail);
        email.setToEmail(toEmail);
        email.setSubject(subject);
        email.setContent(content);

        return email;
    }
}
