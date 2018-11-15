package edu.javeriana.aes.model.invoiceservice.service;

import edu.javeriana.aes.model.entities.Invoice;
import edu.javeriana.aes.model.invoiceservice.domain.Partnership;
import edu.javeriana.aes.model.invoiceservice.domain.Route;
import edu.javeriana.aes.model.invoiceservice.repository.PartnershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoutingService {

    @Autowired
    private PartnershipRepository partnershipRepository;

    public Optional<Route> getRoute(Invoice invoice) {
        Optional<Route> routeResult = Optional.empty();
        try {
            String pattern = invoice.getIdInvoice().substring(0, 4);
            Partnership partnership = partnershipRepository.findByInvoicePattern(pattern);
            String partnerURL = partnership.getUrl();
            partnerURL = partnerURL.replace("#idFactura#", invoice.getIdInvoice());
            Route route = new Route();
            route.setPartnerId(partnership.getId());
            route.setUrl(partnerURL);
            route.setProtocol(partnership.getProtocol());

            routeResult = Optional.of(route);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routeResult;
    }
}
