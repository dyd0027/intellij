package com.example.springioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringIocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIocApplication.class, args);

		String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";
		//bean에 등록된 것들 사용
		ApplicationContext context = ApplicationContextProvider.getContext();

		//bean에 등록된 Base64Encoding 가져오기
		Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
		Encoder encoder = new Encoder(base64Encoder);
		System.out.println(encoder.encode(url));
		//bean에 등록된 UrlEncoding 가져오기
		UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
		encoder.setIEncoder(urlEncoder);
		System.out.println(encoder.encode(url));

		//@Qualifier 사용하여 지정한경우
//		Encoder encoder1 = context.getBean(Encoder.class);
//		System.out.println(encoder1.encode(url));

		Encoder encoder2 = context.getBean("base64encode",Encoder.class);
		System.out.println(encoder2.encode(url));

	}

}

// spring 컨테이너 안에 여러개의 bean을 생성
@Configuration
class Appconfig{
	@Bean("base64encode")
	public Encoder encoder(Base64Encoder base64Encoder){
		return new Encoder(base64Encoder);
	}
	@Bean("urlEncode")
	public Encoder encoder(UrlEncoder urlEncoder){
		return new Encoder(urlEncoder);
	}
}
