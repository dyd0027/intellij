package com.sp.fc.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicAuthenticationTest {
    @LocalServerPort
    int port;
    RestTemplate client = new RestTemplate();
    private String greetingURL(){
        return "http://localhost:"+port+"/greeting";
    }
    @DisplayName("1. 인증 실패")
    @Test
    void test_1(){
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,()->{
            client.getForObject(greetingURL(),String.class);
        });
        assertEquals(401,exception.getRawStatusCode());
    }
    @DisplayName("2. 인증 성공")
    @Test
    void test_2(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic "+ Base64.getEncoder().encodeToString(
                "user1:1111".getBytes()
        ));
        HttpEntity entity = new HttpEntity(null,headers);
        ResponseEntity<String> resp = client.exchange(greetingURL(), HttpMethod.GET,entity,String.class);
        assertEquals("hello",resp.getBody());
    }
    @DisplayName("3. 인증 성공2")
    @Test
    void test_3(){
        //2번이랑 같은 좀 더 편한 방법
        TestRestTemplate testClient = new TestRestTemplate("user1","1111");
        String resp = testClient.getForObject(greetingURL(),String.class);
        assertEquals("hello",resp);
    }
    @DisplayName("4. Post인증")
    @Test
    void test_4(){
        //2번이랑 같은 좀 더 편한 방법
        TestRestTemplate testClient = new TestRestTemplate("user1","1111");
        ResponseEntity<String> resp = testClient.postForEntity(greetingURL(),"yong",String.class);
        assertEquals("hello yong",resp.getBody());
    }
}
