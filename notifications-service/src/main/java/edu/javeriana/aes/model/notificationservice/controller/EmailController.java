package edu.javeriana.aes.model.notificationservice.controller;

import edu.javeriana.aes.model.api.NotificationsApi;
import edu.javeriana.aes.model.entities.Notification;
import edu.javeriana.aes.model.notificationservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class EmailController implements NotificationsApi {

    private final EmailService emailService;
    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public ResponseEntity<Void> sendNotification(@Valid Notification notification) {
        URI location = URI.create("");

        try {
            emailService.deliver(notification);
            location = new URI(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/").toUriString());
        } catch (Exception ex) {
            LOG.error("Error in sending email", ex);
        }
        return ResponseEntity.created(location).build();
    }
}
