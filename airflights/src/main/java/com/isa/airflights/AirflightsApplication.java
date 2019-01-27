package com.isa.airflights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Glavna klasa koja pokrece aplikaciju
 * 
 * */

@SpringBootApplication
public class AirflightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirflightsApplication.class, args);
		System.out.println("RADI!");
	}
}
