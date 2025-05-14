package org.example.analiticproviderservice.model;

public class RequestPercent {
    private double requestPostPercent;
    private double requestGetPercent;
    private double requestDeletePercent;

    public RequestPercent(double requestPostPercent, double requestGetPercent, double requestDeletePercent) {
        this.requestPostPercent = requestPostPercent;
        this.requestGetPercent = requestGetPercent;
        this.requestDeletePercent = requestDeletePercent;
    }
    public RequestPercent() {}

    public double getRequestPostPercent() {
        return requestPostPercent;
    }

    public void setRequestPostPercent(double requestPostPercent) {
        this.requestPostPercent = requestPostPercent;
    }

    public double getRequestGetPercent() {
        return requestGetPercent;
    }

    public void setRequestGetPercent(double requestGetPercent) {
        this.requestGetPercent = requestGetPercent;
    }

    public double getRequestDeletePercent() {
        return requestDeletePercent;
    }

    public void setRequestDeletePercent(double requestDeletePercent) {
        this.requestDeletePercent = requestDeletePercent;
    }
}
