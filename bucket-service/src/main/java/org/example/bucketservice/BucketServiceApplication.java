package org.example.bucketservice;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BucketServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(BucketServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
