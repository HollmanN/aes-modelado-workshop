package edu.javeriana.aes.model.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientServiceApplication {

    public static void main(String[] args) {
        run(ClientServiceApplication.class, args);
    }
}
