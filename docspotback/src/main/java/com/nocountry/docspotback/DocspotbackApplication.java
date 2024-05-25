package com.nocountry.docspotback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@SpringBootApplication
@PropertySource("classpath:.env")
public class DocspotbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocspotbackApplication.class, args);
	}

}
