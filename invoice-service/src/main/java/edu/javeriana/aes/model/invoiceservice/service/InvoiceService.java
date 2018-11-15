package edu.javeriana.aes.model.invoiceservice.service;

import edu.javeriana.aes.model.entities.Invoice;
import edu.javeriana.aes.model.invoiceservice.domain.PartnershipsRestStructure;
import edu.javeriana.aes.model.invoiceservice.domain.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceService {

    private final RoutingService routingService;
    private final TransformRestService transformRestService;
    private final DispatchingRestService dispatchingRestService;
    private final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    public InvoiceService(RoutingService routingService, TransformRestService transformRestService, DispatchingRestService dispatchingRestService) {
        this.routingService = routingService;
        this.transformRestService = transformRestService;
        this.dispatchingRestService = dispatchingRestService;
    }

    public boolean compensateInvoice(Invoice invoice) {
        log.info("Starting pay Invoice process");
        log.info("Consulting routing");
        boolean result = false;

        Optional<Route> routeResult = routingService.getRoute(invoice);
        if (routeResult.isPresent()) {
            Route route = routeResult.get();
            log.info("Routing result URL: {}", route.getUrl());

            switch (route.getProtocol()) {
                case "REST": {
                    Optional<PartnershipsRestStructure> restStructure = transformRestService.transform(route.getPartnerId(), invoice, "DELETE");
                    if (restStructure.isPresent()) {
                        PartnershipsRestStructure structure = restStructure.get();
                        log.info("Transformation result: {}", structure.getTemplateRequest());
                        dispatchingRestService.dispatch(route, structure);

                        result = true;
                    } else {
                        log.error("Can't find transformation for invoice {}", invoice.getIdInvoice());
                    }
                    break;
                }
            }
        } else {
            log.error("Can't find route for invoice {}", invoice.getIdInvoice());
        }
        return result;
    }

    public Invoice getInvoice(String invoice) {
        return null;
    }

    public boolean payInvoice(Invoice invoice) {
        log.info("Starting pay Invoice process");
        log.info("Consulting routing");
        boolean result = false;

        Optional<Route> routeResult = routingService.getRoute(invoice);
        if (routeResult.isPresent()) {
            Route route = routeResult.get();
            log.info("Routing result URL: {}", route.getUrl());

            switch (route.getProtocol()) {
                case "REST": {
                    Optional<PartnershipsRestStructure> restStructure = transformRestService.transform(route.getPartnerId(), invoice, "POST");
                    if (restStructure.isPresent()) {
                        PartnershipsRestStructure structure = restStructure.get();
                        log.info("Transformation result: {}", structure.getTemplateRequest());
                        dispatchingRestService.dispatch(route, structure);

                        result = true;
                    } else {
                        log.error("Can't find transformation for invoice {}", invoice.getIdInvoice());
                    }
                    break;
                }
            }
        } else {
            log.error("Can't find route for invoice {}", invoice.getIdInvoice());
        }
        return result;
    }

    private int consume(Route route, PartnershipsRestStructure structure) {
        log.info("Transformation result: {}", structure.getTemplateRequest());
        return dispatchingRestService.dispatch(route, structure);
    }
}
