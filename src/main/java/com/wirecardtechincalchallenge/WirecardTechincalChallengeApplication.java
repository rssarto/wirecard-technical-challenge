package com.wirecardtechincalchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WirecardTechincalChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WirecardTechincalChallengeApplication.class, args);
	}

}

