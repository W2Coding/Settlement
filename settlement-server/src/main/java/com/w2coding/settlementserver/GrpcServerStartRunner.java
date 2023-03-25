package com.w2coding.settlementserver;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import io.grpc.Server;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GrpcServerStartRunner implements ApplicationRunner, DisposableBean {

	private final Server grpcServer;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		grpcServer.start();
		grpcServer.awaitTermination();
	}

	@Override
	public void destroy() {
		if (!ObjectUtils.isEmpty(grpcServer)) {
			grpcServer.shutdown();
		}
	}
}
