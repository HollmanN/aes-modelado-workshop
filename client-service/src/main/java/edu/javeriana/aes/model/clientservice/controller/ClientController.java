package edu.javeriana.aes.model.clientservice.controller;

import edu.javeriana.aes.model.api.ClientsApi;
import edu.javeriana.aes.model.clientservice.service.ClientService;
import edu.javeriana.aes.model.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController implements ClientsApi {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<Void> createClient(@Valid Client client) {
        clientService.postClient(client);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<Client>> getAllClients() {
        String id = "12345";
        List<Client> clientList = new ArrayList<Client>() {{
            add(clientService.getClient(id));
        }};

        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Client> getClient(String id) {
        return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);
    }
}
