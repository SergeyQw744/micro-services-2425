package org.example.analiticproviderservice.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticCalculatorService {

    public String getMostPopularProvider(List<String> providers){

        List<String> originalProviders = providers.stream().distinct().toList();
        Map<String, Long> providerNameAndCount = new HashMap<>();
        for (String provider : originalProviders){
            providerNameAndCount.put(provider, providers.stream().filter(p -> p.equals(provider)).count());
        }
        return providerNameAndCount.entrySet()
                .stream()
                .filter(entry -> providerNameAndCount.values()
                        .stream()
                        .max(Long::compareTo)
                        .get().equals(entry.getValue())
                )
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    }

    public int operationCount(List<String> operations, String operationType){
        return (int) operations.stream()
                .filter(operation -> operation.equals(operationType))
                .count();
    }

    public int requestCount(List<String> requests, String requestType){
        return (int) requests.stream()
                .filter(request -> request.equals(requestType))
                .count();
    }
}
