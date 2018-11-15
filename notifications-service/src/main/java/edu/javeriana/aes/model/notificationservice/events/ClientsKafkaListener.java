package edu.javeriana.aes.model.notificationservice.events;

import edu.javeriana.aes.model.entities.Email;
import edu.javeriana.aes.model.entities.Notification;
import edu.javeriana.aes.model.notificationservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class ClientsKafkaListener {

    private final static Logger logger = LoggerFactory.getLogger(ClientsKafkaListener.class);

    private EmailService emailService;

    public ClientsKafkaListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "clients")
    public void order(String clientInfo, Acknowledgment acknowledgment) {
        logger.info("Received client info: " + clientInfo);

        emailService.deliver(clientInfo);

        acknowledgment.acknowledge();
    }

}
