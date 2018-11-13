package edu.javeriana.aes.model.soapconsumerservice;

import edu.javeriana.aes.model.soapconsumerservice.artifacts.gas.PagosInerface;
import edu.javeriana.aes.model.soapconsumerservice.artifacts.gas.PagosService;
import edu.javeriana.aes.model.soapconsumerservice.artifacts.gas.ReferenciaFactura;
import edu.javeriana.aes.model.soapconsumerservice.artifacts.gas.ResultadoConsulta;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

@SpringBootApplication
public class SoapConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapConsumerServiceApplication.class, args);

        PagosService pagosService = new PagosService();
        URL url = pagosService.getWSDLDocumentLocation();

        QName qname = pagosService.getServiceName();

        Service service = Service.create(url, qname);

        PagosInerface stockEndPoint = service.getPort(PagosInerface.class);

        ReferenciaFactura referenciaFactura = new ReferenciaFactura();
        referenciaFactura.setReferenciaFactura("1234444343");

        ResultadoConsulta resultadoConsulta = stockEndPoint.cosultar(referenciaFactura);

        System.out.println(referenciaFactura.getReferenciaFactura());
        System.out.println(resultadoConsulta.getTotalPagar());
    }
}
