package edu.javeriana.aes.model.invoiceservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partnerships_rest")
public class PartnershipsRestStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idPartner;
    private String templateRequest;
    private String templateResponse;
    private String httpVerb;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(long idPartner) {
        this.idPartner = idPartner;
    }


    public String getTemplateRequest() {
        return templateRequest;
    }

    public void setTemplateRequest(String templateRequest) {
        this.templateRequest = templateRequest;
    }


    public String getTemplateResponse() {
        return templateResponse;
    }

    public void setTemplateResponse(String templateResponse) {
        this.templateResponse = templateResponse;
    }


    public String getHttpVerb() {
        return httpVerb;
    }

    public void setHttpVerb(String httpVerb) {
        this.httpVerb = httpVerb;
    }

}
