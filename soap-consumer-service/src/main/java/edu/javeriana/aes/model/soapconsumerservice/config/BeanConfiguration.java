package edu.javeriana.aes.model.soapconsumerservice.config;

import edu.javeriana.aes.model.soapconsumerservice.artifacts.gas.PagosInerface;
import edu.javeriana.aes.model.soapconsumerservice.artifacts.gas.PagosService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

@Configuration
public class BeanConfiguration {
    private static final PagosService pagosService = new PagosService();

    @Bean
    public URL getURLService() {
        return pagosService.getWSDLDocumentLocation();
    }

    @Bean
    public QName getQName() {
        return pagosService.getServiceName();
    }

    @Bean
    public Service getService(URL url, QName qName) {
        return Service.create(url, qName);
    }

    @Bean
    public PagosInerface getPagosInterface(Service service){
        return service.getPort(PagosInerface.class);
    }
}
