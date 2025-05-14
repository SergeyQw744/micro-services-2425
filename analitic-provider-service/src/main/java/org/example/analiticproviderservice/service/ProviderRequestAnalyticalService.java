package org.example.analiticproviderservice.service;

import org.example.analiticproviderservice.grpc.GreetingAnalyticalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderRequestAnalyticalService {

    private final GreetingAnalyticalClient greetingAnalyticalClient;

    @Autowired
    public ProviderRequestAnalyticalService(GreetingAnalyticalClient greetingAnalyticalClient) {
        this.greetingAnalyticalClient = greetingAnalyticalClient;
    }

    @Scheduled(fixedRate = 60*1000, initialDelay = 60*1000)
    public void getAnalytical(){
        List<List<String>> data = greetingAnalyticalClient.requestAnalyticsFromBucketService();
        System.out.println(data.toString());
    }
}
