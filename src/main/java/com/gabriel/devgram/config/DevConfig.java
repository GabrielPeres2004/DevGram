package com.gabriel.devgram.config;

import com.gabriel.devgram.services.DBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBServices dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;

	@Bean
	public CommandLineRunner instanceDB() {
		return args -> {
			if (value.equals("create")) {
				this.dbService.instanceDB();
			}
		};
	}
}