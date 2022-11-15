package com.example.jpa.bookmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 자주쓰는 listener들의 정보가 담겨 있음.
public class BookmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmanagerApplication.class, args);
	}

}
