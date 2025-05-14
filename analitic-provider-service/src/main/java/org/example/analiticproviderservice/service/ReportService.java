package org.example.analiticproviderservice.service;

import org.example.analiticproviderservice.model.OperationPercent;
import org.example.analiticproviderservice.model.Report;
import org.example.analiticproviderservice.model.RequestPercent;
import org.example.analiticproviderservice.util.AnalyticalParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    private final AnalyticalParser analyticalParser;
    private final AnalyticCalculatorService analyticCalculatorService;

    @Autowired
    public ReportService(AnalyticalParser analyticalParser, AnalyticCalculatorService analyticCalculatorService) {
        this.analyticalParser = analyticalParser;
        this.analyticCalculatorService = analyticCalculatorService;
    }

    public void save(List<List<String>> data){
        Report report = Report.builder()
                .avgPrice(analyticalParser.parsePrices(data.get(4)).stream()
                        .mapToDouble(x -> x)
                        .average()
                        .getAsDouble())
                .mostPopularProvider(analyticCalculatorService.getMostPopularProvider(
                        analyticalParser.parseProvidersToStrings(data.get(3)))
                )
                .requestPercent(
                        getRequestPercent(analyticalParser.parseRequestsToStrings(data.get(0)))
                )
                .operationPercent(
                        getOperationPercent(analyticalParser.parseOperationsToStrings(data.get(1)))
                )
                .build();
    }



    private RequestPercent getRequestPercent(List<String> requests){
        return new RequestPercent(
                (double) requestCount(requests, "POST")/requests.size()*100,
                (double) requestCount(requests, "GET")/requests.size()*100,
                (double) requestCount(requests, "DELETE")/requests.size()*100
                );
    }

    private OperationPercent getOperationPercent(List<String> operations){
        return new OperationPercent(
                (double) operationCount(operations, "SAVE")/operations.size()*100,
                (double) operationCount(operations, "FIND_ALL")/operations.size()*100,
                (double) operationCount(operations, "DELETE")/operations.size()*100
        );
    }


}
