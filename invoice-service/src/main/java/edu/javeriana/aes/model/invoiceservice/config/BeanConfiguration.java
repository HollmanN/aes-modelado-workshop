package edu.javeriana.aes.model.invoiceservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
