package org.example.bucketservice.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.bucketservice.exception.NotRequestAnalyticalGrpcException;
import org.example.bucketservice.service.AnalyticService;
import org.example.bucketservice.util.parser.AnalyticalFrameListToStringsParser;
import org.example.grpc.GreetingAnalyticalServiceGrpc;
import org.example.grpc.GreetingAnalyticalServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@GrpcService
public class GreetingAnalyticalServiceImpl extends GreetingAnalyticalServiceGrpc.GreetingAnalyticalServiceImplBase{

    @Value("${message.exception.grpc.not.analytical}")
    private String notAnalyticalMessage;

    private final AnalyticService analyticService;
    private final AnalyticalFrameListToStringsParser frameListToStringsParser;

    @Autowired
    public GreetingAnalyticalServiceImpl(AnalyticService analyticService,
                                         AnalyticalFrameListToStringsParser frameListToStringsParser) {
        this.analyticService = analyticService;
        this.frameListToStringsParser = frameListToStringsParser;
    }

    @Override
    public void greeting(GreetingAnalyticalServiceOuterClass.HelloRequestAnalytic request,
                         StreamObserver<GreetingAnalyticalServiceOuterClass.HelloResponseAnalytic> responseObserver){
        if (request.getMsg().equals("get_analytic")){
            List<List<String>> stringsFrame =  frameListToStringsParser.parseFramesToStrings(analyticService.getFrames());
            GreetingAnalyticalServiceOuterClass.HelloResponseAnalytic response =
                    GreetingAnalyticalServiceOuterClass.HelloResponseAnalytic.newBuilder()
                            .addAllRequests(stringsFrame.get(0))
                            .addAllOperations(stringsFrame.get(1))
                            .addAllDates(stringsFrame.get(2))
                            .addAllProviders(stringsFrame.get(3))
                            .addAllPrices(stringsFrame.get(4))
                            .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            throw new NotRequestAnalyticalGrpcException(notAnalyticalMessage);
        }
    }
}
