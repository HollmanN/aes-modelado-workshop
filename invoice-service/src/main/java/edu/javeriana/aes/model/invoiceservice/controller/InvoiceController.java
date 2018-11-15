package edu.javeriana.aes.model.invoiceservice.controller;

import edu.javeriana.aes.model.api.InvoiceApi;
import edu.javeriana.aes.model.entities.Invoice;
import edu.javeriana.aes.model.invoiceservice.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class InvoiceController implements InvoiceApi {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    public ResponseEntity<Void> compensateInvoice(@Valid Invoice invoice) {
        URI location = URI.create("");
        boolean succesfull = false;
        try {
            succesfull = invoiceService.compensateInvoice(invoice);
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
    public ResponseEntity<Invoice> getInvoice(String invoice) {
        Invoice invoiceResult = invoiceService.getInvoice(invoice);
        return new ResponseEntity<>(invoiceResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> payInvoice(@Valid Invoice invoice) {
        URI location = URI.create("");
        boolean succesfull = false;
        try {
            succesfull = invoiceService.payInvoice(invoice);
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
