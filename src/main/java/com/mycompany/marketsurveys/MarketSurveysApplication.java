package com.mycompany.marketsurveys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MarketSurveysApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketSurveysApplication.class, args);
	}

}