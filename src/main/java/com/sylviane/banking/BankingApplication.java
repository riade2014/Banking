package com.sylviane.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaAuditing//demande à jpa d'activer le JpaAuditing
public class BankingApplication {
	//SDP(Spring Data Pattern)
	//DTO(Data Transfert Objects)

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

}
