package com.example.client.service;

import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    //http://localhost:9090/api/server
    //response
    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server")
                .queryParam("name","yh")
                .queryParam("age",20)
                .build()
                .toUri();
        System.out.println(uri.toString());
        RestTemplate restTemplate = new RestTemplate();

        //단순히 String으로만 받을때 사용
        //String result = restTemplate.getForObject(uri,String.class);

        //이것저것 담아옴 여기서 get은 get방식의 get
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri,UserResponse.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public UserResponse post(){
        // http://localhost:9090/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"yh")
                .toUri();
        System.out.println(uri);

        UserRequest req = new UserRequest();
        req.setAge(10);
        req.setName("sh");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response =restTemplate.postForEntity(uri,req,UserResponse.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }
}
