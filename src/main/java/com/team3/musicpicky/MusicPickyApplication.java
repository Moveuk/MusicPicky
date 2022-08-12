package com.team3.musicpicky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MusicPickyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicPickyApplication.class, args);
	}

}
