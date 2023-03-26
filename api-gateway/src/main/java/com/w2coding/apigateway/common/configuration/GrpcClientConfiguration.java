package com.w2coding.apigateway.common.configuration;

import com.w2coding.proto.member.MemberServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfiguration {

    private final ManagedChannel settlementServerChannel = ManagedChannelBuilder.forAddress("localhost", 8082)
                .usePlaintext()
                .build();

    @Bean
    public MemberServiceGrpc.MemberServiceBlockingStub memberServiceBlockingStub() {
        return MemberServiceGrpc.newBlockingStub(settlementServerChannel);
    }
}
