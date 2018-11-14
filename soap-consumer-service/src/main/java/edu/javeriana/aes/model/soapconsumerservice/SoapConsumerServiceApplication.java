package edu.javeriana.aes.model.soapconsumerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SoapConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapConsumerServiceApplication.class, args);
    }
}
