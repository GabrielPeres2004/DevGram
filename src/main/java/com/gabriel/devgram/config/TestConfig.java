package com.gabriel.devgram.config;

import com.gabriel.devgram.services.DBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBServices dbService;

	@Bean
	public CommandLineRunner instanceDB() {
		return args -> this.dbService.instanceDB();
	}
}