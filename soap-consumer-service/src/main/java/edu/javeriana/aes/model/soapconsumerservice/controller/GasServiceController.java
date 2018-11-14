package edu.javeriana.aes.model.soapconsumerservice.controller;

import edu.javeriana.aes.model.api.GasApi;
import edu.javeriana.aes.model.entities.Invoice;
import edu.javeriana.aes.model.soapconsumerservice.service.GasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class GasServiceController implements GasApi {

    private final GasService gasService;

    public GasServiceController(GasService gasService) {
        this.gasService = gasService;
    }

    @Override
    public ResponseEntity<Void> compensateGasInvoice(Invoice invoice) {
        URI location = URI.create("");
        boolean succesfull = false;
        try {
            succesfull = gasService.compensateGasInvoice(invoice);
            location = new URI(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/").toUriString());
        } catch (Exception ex) {

        }

        if (succesfull) {
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Invoice> getGasInvoice(String invoice) {
        Invoice invoiceResult = gasService.consultarGasInvoice(String.valueOf(invoice));
        return new ResponseEntity<>(invoiceResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> payGasInvoice(@RequestBody @Valid Invoice invoice) {
        URI location = URI.create("");
        boolean succesfull = false;
        try {
            succesfull = gasService.payGasInvoice(invoice);
            location = new URI(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/").toUriString());
        } catch (Exception ex) {

        }

        if (succesfull) {
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
