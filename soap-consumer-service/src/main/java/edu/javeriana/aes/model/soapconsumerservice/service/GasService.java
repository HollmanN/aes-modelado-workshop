package edu.javeriana.aes.model.soapconsumerservice.service;

import edu.javeriana.aes.model.entities.Invoice;
import edu.javeriana.aes.model.soapconsumerservice.artifacts.gas.Pago;
import edu.javeriana.aes.model.soapconsumerservice.artifacts.gas.PagosInerface;
import edu.javeriana.aes.model.soapconsumerservice.artifacts.gas.ReferenciaFactura;
import edu.javeriana.aes.model.soapconsumerservice.artifacts.gas.Resultado;
import edu.javeriana.aes.model.soapconsumerservice.artifacts.gas.ResultadoConsulta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class GasService {

    private final PagosInerface pagosInerface;
    private final static Logger logger = LoggerFactory.getLogger(GasService.class);

    public GasService(PagosInerface pagosInerface) {
        this.pagosInerface = pagosInerface;
    }

    public boolean compensateGasInvoice(Invoice invoice) {
        boolean result = false;
        try {
            logger.info("Compensating invoice {}", invoice);
            Pago pago = new Pago();
            ReferenciaFactura referenciaFactura = new ReferenciaFactura();
            referenciaFactura.setReferenciaFactura(invoice.getIdInvoice());
            pago.setReferenciaFactura(referenciaFactura);
            pago.setTotalPagar(invoice.getTotalAmount().doubleValue());

            Resultado resultado = pagosInerface.compensar(pago);
            logger.info("Result: {}", resultado.getMensaje());

            result = resultado.getMensaje().equals("Factura Compensada Exitosamente");

        } catch (Exception e) {
            logger.error("Error compensating invoice {}", e, invoice.getIdInvoice());
        }

        return result;
    }

    public Invoice consultarGasInvoice(String invoice) {
        Invoice result = new Invoice();
        try {
            logger.info("Consulting invoice {}", invoice);

            ReferenciaFactura referenciaFactura = new ReferenciaFactura();
            referenciaFactura.setReferenciaFactura(invoice);
            ResultadoConsulta resultadoConsulta = pagosInerface.cosultar(referenciaFactura);

            //TODO - Move this to XSLT
            result.setIdInvoice(resultadoConsulta.getReferenciaFactura().getReferenciaFactura());
            result.setTotalAmount(BigDecimal.valueOf(resultadoConsulta.getTotalPagar()));
        } catch (Exception e) {
            logger.error("Error consulting invoice {}", e, invoice);
        }
        return result;
    }

    public boolean payGasInvoice(Invoice invoice) {
        boolean result = false;
        try {
            logger.info("Compensating invoice {}", invoice);
            Pago pago = new Pago();
            ReferenciaFactura referenciaFactura = new ReferenciaFactura();
            referenciaFactura.setReferenciaFactura(invoice.getIdInvoice());
            pago.setReferenciaFactura(referenciaFactura);
            pago.setTotalPagar(invoice.getTotalAmount().doubleValue());

            Resultado resultado = pagosInerface.pagar(pago);
            logger.info("Result: {}", resultado.getMensaje());

            result = resultado.getMensaje().equals("Factura Pagada Exitosamente");

        } catch (Exception e) {
            logger.error("Error paying invoice {}", e, invoice.getIdInvoice());
        }

        return result;
    }


}
