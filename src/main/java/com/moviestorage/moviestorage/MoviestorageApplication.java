package com.moviestorage.moviestorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class MoviestorageApplication {

//	public static final String APPLICATION_LOCATIONS = "spring.config.location="
//			+ "classpath:application.yml, "
//			+ "classpath:application-db.yml";

	public static void main(String[] args) {
		SpringApplication.run(MoviestorageApplication.class, args);
//		new SpringApplicationBuilder(MoviestorageApplication.class)
//				.properties(APPLICATION_LOCATIONS)
//				.run(args);
	}

}
