package edu.javeriana.aes.model.clientservice.service;

import edu.javeriana.aes.model.entities.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import javax.validation.Valid;

@Service
public class ClientService {

    private final static Logger logger = LoggerFactory.getLogger(ClientService.class);

    private KafkaTemplate<String, String> clientTemplate;

    @Autowired
    public ClientService(KafkaTemplate<String, String> clientTemplate) {
        this.clientTemplate = clientTemplate;
    }

    public Client getClient(int id) {
        Client client = new Client();
        client.setId(id);
        client.setIdType(Client.IdTypeEnum.CC);
        client.setIdValue("1234567890");
        client.setName("Hollman");
        client.setLastName("Castro");
        client.setEmail("hollman_castro@outlook.com");
        client.setPhone("+571234567890");

        fireClientCreatedEvent(client);
        return client;
    }

    private void fireClientCreatedEvent(Client client) {
        clientTemplate.send("clients", client.getEmail(), client.toString());
    }

    public void postClient(@Valid Client client) {
        clientTemplate.send("clients", client.getEmail(), client.toString());
        logger.info("Created client {}", client);
    }
}
