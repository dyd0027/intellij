package com.example.client.service;

import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    //http://localhost/api/server/hello
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

        //이것저것 담아옴
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri,UserResponse.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }
}
