package com.w2coding.settlementserver.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.w2coding.settlementserver.member.grpc.MemberGrpcService;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class GrpcServerConfiguration {

	private final MemberGrpcService memberGrpcService;

	@Value("${grpc.port}")
	Integer grpcPort;

	@Bean
	public Server grpcServer() {
		return ServerBuilder
			.forPort(grpcPort)
			.addService(memberGrpcService)
			.build();
	}

}
