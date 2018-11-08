package edu.javeriana.aes.model.clientservice.service;

import edu.javeriana.aes.model.entities.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ClientService {

    private final static Logger logger = LoggerFactory.getLogger(ClientService.class);

    public Client getClient(String id) {
        Client client = new Client();
        client.setId(id);
        client.setName("Test");
        client.setLastName("Test");
        client.setEmail("test@test.com");
        client.setPhone("+573023844696");

        return client;
    }

    public void postClient(@Valid Client client) {
        logger.info("Created client {}", client);
    }
}
