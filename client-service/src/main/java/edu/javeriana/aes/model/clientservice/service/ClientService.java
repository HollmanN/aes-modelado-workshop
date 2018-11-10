package edu.javeriana.aes.model.clientservice.service;

import edu.javeriana.aes.model.entities.Client;
import edu.javeriana.aes.model.entities.Identification;
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
        client.setName("Hollman");
        client.setLastName("Castro");
        client.setEmail("hollman_castro@outlook.com");
        client.setPhone("+571234567890");

        Identification identification = new Identification();
        identification.setIdType(Identification.IdTypeEnum.CC);
        identification.setIdNumber("1234567890");
        client.setIdentification(identification);

        return client;
    }

    public void postClient(@Valid Client client) {
        logger.info("Created client {}", client);
    }
}
