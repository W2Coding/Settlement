package com.w2coding.settlementserver.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class GrpcServerConfiguration {
	@Value("${grpc.port}")
	Integer grpcPort;

	@Bean
	public Server grpcServer() {
		return ServerBuilder
			.forPort(grpcPort)
			// .addService()
			.build();
	}
}
