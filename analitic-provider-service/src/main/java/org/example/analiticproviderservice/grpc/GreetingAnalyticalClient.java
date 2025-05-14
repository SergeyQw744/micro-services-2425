package org.example.analiticproviderservice.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.grpc.GreetingAnalyticalServiceGrpc;
import org.example.grpc.GreetingAnalyticalServiceOuterClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GreetingAnalyticalClient {

    @GrpcClient("GLOBAL")
    private GreetingAnalyticalServiceGrpc.GreetingAnalyticalServiceBlockingStub stub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        stub = GreetingAnalyticalServiceGrpc.newBlockingStub(managedChannel);
    }

    public List<List<String>> requestAnalyticsFromBucketService(){
        GreetingAnalyticalServiceOuterClass.HelloRequestAnalytic request =
                GreetingAnalyticalServiceOuterClass.HelloRequestAnalytic.newBuilder()
                        .setMsg("get_analytic")
                        .build();
        GreetingAnalyticalServiceOuterClass.HelloResponseAnalytic response = stub.greeting(request);
        List<List<String>> data = new ArrayList<>();
        data.add(response.getRequestsList());
        data.add(response.getOperationsList());
        data.add(response.getDatesList());
        data.add(response.getProvidersList());
        data.add(response.getPricesList());
        return data;
    }
}
