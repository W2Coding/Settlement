package com.w2coding.settlementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SettlementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettlementServerApplication.class, args);
	}

}
