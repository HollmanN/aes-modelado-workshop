package edu.javeriana.aes.model.invoiceservice.domain;

public enum Action {
    PAY("PAY"), CONSULT("CONSULT"), COMPENSATE("COMPENSATE");

    String value;
    Action(String action) {
        this.value = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
