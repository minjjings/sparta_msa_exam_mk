package com.sparta.msa_exam.Eureca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurecaApplication.class, args);
	}

}
