package org.example.bucketservice.model;

import java.sql.Timestamp;

public class AnalyticalFrame {
    private Long id;
    private RequestEnum request;
    private OperationEnum operation;
    private Timestamp dateOfAction;
    private String provider;
    private double price;

    public AnalyticalFrame() {}

    public AnalyticalFrame(RequestEnum request,
                           OperationEnum operation,
                           String provider, double price) {
        this.request = request;
        this.operation = operation;
        this.provider = provider;
        this.price = price;
    }

    public RequestEnum getRequest() {
        return request;
    }

    public void setRequest(RequestEnum request) {
        this.request = request;
    }

    public OperationEnum getOperation() {
        return operation;
    }

    public void setOperation(OperationEnum operation) {
        this.operation = operation;
    }

    public Timestamp getDateOfAction() {
        return dateOfAction;
    }

    public void setDateOfAction(Timestamp dateOfAction) {
        this.dateOfAction = dateOfAction;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
