
package edu.javeriana.aes.model.soapconsumerservice.artifacts.gas;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PagosService", targetNamespace = "http://boundary.pagos.modval.aes.javeriana.edu.co/", wsdlLocation = "http://localhost:8080/gas-natural/PagosService?wsdl")
public class PagosService
    extends Service
{

    private final static URL PAGOSSERVICE_WSDL_LOCATION;
    private final static WebServiceException PAGOSSERVICE_EXCEPTION;
    private final static QName PAGOSSERVICE_QNAME = new QName("http://boundary.pagos.modval.aes.javeriana.edu.co/", "PagosService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/gas-natural/PagosService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PAGOSSERVICE_WSDL_LOCATION = url;
        PAGOSSERVICE_EXCEPTION = e;
    }

    public PagosService() {
        super(__getWsdlLocation(), PAGOSSERVICE_QNAME);
    }

    public PagosService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PAGOSSERVICE_QNAME, features);
    }

    public PagosService(URL wsdlLocation) {
        super(wsdlLocation, PAGOSSERVICE_QNAME);
    }

    public PagosService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PAGOSSERVICE_QNAME, features);
    }

    public PagosService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PagosService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PagosInerface
     */
    @WebEndpoint(name = "PagosPort")
    public PagosInerface getPagosPort() {
        return super.getPort(new QName("http://boundary.pagos.modval.aes.javeriana.edu.co/", "PagosPort"), PagosInerface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PagosInerface
     */
    @WebEndpoint(name = "PagosPort")
    public PagosInerface getPagosPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://boundary.pagos.modval.aes.javeriana.edu.co/", "PagosPort"), PagosInerface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PAGOSSERVICE_EXCEPTION!= null) {
            throw PAGOSSERVICE_EXCEPTION;
        }
        return PAGOSSERVICE_WSDL_LOCATION;
    }

}
