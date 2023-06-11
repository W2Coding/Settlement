package com.w2coding.apigateway.common.configuration;

import com.w2coding.proto.member.MemberServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfiguration {
    @Value("${grpc.settlementServer.host}")
    private String settlementServer;

    @Value("${grpc.settlementServer.port}")
    private int settlementPort;

    @Bean
    public MemberServiceGrpc.MemberServiceBlockingStub memberServiceBlockingStub() {
        ManagedChannel settlementServerChannel = ManagedChannelBuilder.forAddress(settlementServer, settlementPort)
                .usePlaintext()
                .build();
        return MemberServiceGrpc.newBlockingStub(settlementServerChannel);
    }
}
