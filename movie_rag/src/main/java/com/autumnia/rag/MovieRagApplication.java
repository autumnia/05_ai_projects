package com.autumnia.rag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MovieRagApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieRagApplication.class, args);
//		log.info("여기는 메인");
	}

}
