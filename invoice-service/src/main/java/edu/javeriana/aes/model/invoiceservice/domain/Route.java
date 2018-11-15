package edu.javeriana.aes.model.invoiceservice.domain;

public class Route {
    private Long partnerId;
    private String url;
    private String protocol;

    public Route(Long partnerId, String url, String protocol) {
        this.partnerId = partnerId;
        this.url = url;
        this.protocol = protocol;
    }

    public Route() {
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
