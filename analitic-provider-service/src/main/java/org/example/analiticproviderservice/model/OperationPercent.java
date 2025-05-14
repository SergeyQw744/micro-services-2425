package org.example.analiticproviderservice.model;

public class OperationPercent {
    private double operationSavePercent;
    private double operationFindAllPercent;
    private double operationDeletePercent;

    public OperationPercent(){}

    public OperationPercent (double operationSavePercent, double operationFindAllPercent, double operationDeletePercent) {
        this.operationSavePercent = operationSavePercent;
        this.operationFindAllPercent = operationFindAllPercent;
        this.operationDeletePercent = operationDeletePercent;
    }

    public double getOperationSavePercent() {
        return operationSavePercent;
    }

    public void setOperationSavePercent(double operationSavePercent) {
        this.operationSavePercent = operationSavePercent;
    }

    public double getOperationFindAllPercent() {
        return operationFindAllPercent;
    }

    public void setOperationFindAllPercent(double operationFindAllPercent) {
        this.operationFindAllPercent = operationFindAllPercent;
    }

    public double getOperationDeletePercent() {
        return operationDeletePercent;
    }

    public void setOperationDeletePercent(double operationDeletePercent) {
        this.operationDeletePercent = operationDeletePercent;
    }
}
