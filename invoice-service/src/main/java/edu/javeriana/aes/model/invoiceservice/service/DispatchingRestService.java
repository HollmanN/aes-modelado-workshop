package edu.javeriana.aes.model.invoiceservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.javeriana.aes.model.invoiceservice.domain.PartnershipsRestStructure;
import edu.javeriana.aes.model.invoiceservice.domain.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DispatchingRestService {

    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(DispatchingRestService.class);

    @Autowired
    public DispatchingRestService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public int dispatch(Route route, PartnershipsRestStructure dispatchingRestService) {
        int status = 400;
        try {
            RestTemplate restTemplate = new RestTemplate();
            switch (dispatchingRestService.getHttpVerb()) {
                case "GET": {
                    logger.info("Generating GET request");
                    ResponseEntity<String> response = restTemplate.getForEntity(route.getUrl(), String.class);
                    logger.info("GET response: {}", response.getStatusCode());
                    status = response.getStatusCodeValue();
                    break;
                }
                case "POST": {
                    logger.info("Generating POST request");
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> request = new HttpEntity<>(dispatchingRestService.getTemplateRequest(), headers);
                    ResponseEntity<String> response = restTemplate.postForEntity(route.getUrl(), request, String.class);
                    logger.info("POST response: {}", response.getStatusCode());
                    status = response.getStatusCodeValue();
                    break;
                }
                case "DELETE": {
                    logger.info("Generating DELETE request");
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> request = new HttpEntity<>(dispatchingRestService.getTemplateRequest(), headers);
                    ResponseEntity<String> response = restTemplate.exchange(route.getUrl(), HttpMethod.DELETE, request, String.class);
                    logger.info("DELETE response: {}", response.getStatusCode());
                    status = response.getStatusCodeValue();
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("Error executing dispatching!", e);
        }
        return status;
    }
}
