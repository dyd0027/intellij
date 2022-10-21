package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		System.out.println("------start-------");
		var objectMapper = new ObjectMapper();

		//object -> text
		//object mapper는 et method를 활용한다.
		var user = new User("용휘",27,"010-3292-4012");
		var text = objectMapper.writeValueAsString(user);
		System.out.println(text);

		//text -> object
		//object mapper 는 default 생성자를 필요로 한다.
		var objectUser = objectMapper.readValue(text,User.class);
		System.out.println(objectUser);


	}

}
