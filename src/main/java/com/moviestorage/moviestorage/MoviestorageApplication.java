package com.moviestorage.moviestorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication()
public class MoviestorageApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml, "
			+ "classpath:dbInfo.yml";

	public static void main(String[] args) {
//		SpringApplication.run(MoviestorageApplication.class, args);
		new SpringApplicationBuilder(MoviestorageApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
