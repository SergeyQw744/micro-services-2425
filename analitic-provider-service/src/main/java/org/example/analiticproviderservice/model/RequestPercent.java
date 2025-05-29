package org.example.analiticproviderservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestPercent {
    private int id;
    private double requestPostPercent;
    private double requestGetPercent;
    private double requestDeletePercent;

    public RequestPercent(double requestPostPercent, double requestGetPercent, double requestDeletePercent) {
        this.requestPostPercent = requestPostPercent;
        this.requestGetPercent = requestGetPercent;
        this.requestDeletePercent = requestDeletePercent;
    }

}
