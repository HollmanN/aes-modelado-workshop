package edu.javeriana.aes.model.invoiceservice.service;

import edu.javeriana.aes.model.entities.Invoice;
import edu.javeriana.aes.model.invoiceservice.domain.PartnershipsRestStructure;
import edu.javeriana.aes.model.invoiceservice.repository.PartnershipRestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransformRestService {
    @Autowired
    private PartnershipRestRepository partnershipRestRepository;
    private final Logger logger = LoggerFactory.getLogger(TransformRestService.class);

    public Optional<PartnershipsRestStructure> transform(Long idPartner, Invoice invoice, String httpVerb) {
        Optional<PartnershipsRestStructure> result = Optional.empty();

        try {
            PartnershipsRestStructure partnershipsRestStructure =
                    partnershipRestRepository.findByIdPartnerAndHttpVerb(idPartner, httpVerb);
            if (partnershipsRestStructure != null) {
                String templateRequest;
                if (!(partnershipsRestStructure.getTemplateRequest() == null)) {
                    templateRequest = partnershipsRestStructure.getTemplateRequest();
                    templateRequest = templateRequest.replace("#idFactura#", invoice.getIdInvoice());
                    templateRequest = templateRequest.replace("#valorFactura#", String.valueOf(invoice.getAmount()));
                    partnershipsRestStructure.setTemplateRequest(templateRequest);
                    result = Optional.of(partnershipsRestStructure);
                }
            }
        } catch (Exception e) {
            logger.error("Error on transforming", e);
        }

        return result;
    }
}
