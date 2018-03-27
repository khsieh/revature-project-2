package com.revature.warlockzone;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarlockZoneApplication {
	private static Logger log = Logger.getLogger(WarlockZoneApplication.class.getName());
	public static void main(String[] args) {
		log.info("Starting spring application");
		SpringApplication.run(WarlockZoneApplication.class, args);
	}
}
