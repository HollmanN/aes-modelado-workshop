package edu.javeriana.aes.model.invoiceservice.repository;

import edu.javeriana.aes.model.invoiceservice.domain.Partnership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnershipRepository extends JpaRepository<Partnership, Long> {
    Partnership findByInvoicePattern(String pattern);
}
