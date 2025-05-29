package org.example.analiticproviderservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OperationPercent {
    private int id;
    private double operationSavePercent;
    private double operationFindAllPercent;
    private double operationDeletePercent;

    public OperationPercent (double operationSavePercent, double operationFindAllPercent, double operationDeletePercent) {
        this.operationSavePercent = operationSavePercent;
        this.operationFindAllPercent = operationFindAllPercent;
        this.operationDeletePercent = operationDeletePercent;
    }
}
