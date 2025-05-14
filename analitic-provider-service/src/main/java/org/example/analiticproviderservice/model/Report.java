package org.example.analiticproviderservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Report {
    private int id;
    private RequestPercent requestPercent;
    private OperationPercent operationPercent;
    private String mostPopularProvider;
    private double avgPrice;
}
