package edu.javeriana.aes.model.invoiceservice.repository;

import edu.javeriana.aes.model.invoiceservice.domain.PartnershipsRestStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnershipRestRepository extends JpaRepository<PartnershipsRestStructure, Long> {
    PartnershipsRestStructure findByIdPartnerAndHttpVerb(Long idPartner, String httpVerb);
}
