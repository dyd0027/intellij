package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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

    //헤더에 값 담아 보내기
    public UserResponse exchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/exchange/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"yh")
                .toUri();
        System.out.println(uri);

        UserRequest req = new UserRequest();
        req.setAge(10);
        req.setName("sh");

        RequestEntity<UserRequest> requestRequestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header","fffff")
                .body(req);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestRequestEntity,UserResponse.class);
        return response.getBody();
    }

    public Req<UserResponse> genericExchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/generic/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"yh")
                .toUri();
        System.out.println(uri);

        UserRequest userRequest = new UserRequest();
        userRequest.setAge(10);
        userRequest.setName("sh");

        Req<UserRequest> req = new Req<>();
        req.setHeader(
            new Req.Header()
        );
        req.setRBody(
                userRequest
        );

        RequestEntity<Req<UserRequest>> requestRequestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header","fffff")
                .body(req);
        RestTemplate restTemplate = new RestTemplate();
        //generic 타입은 클래스를 생성 할 수 없어서 new Parameter~~저걸 써야 함
        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestRequestEntity, new ParameterizedTypeReference<Req<UserResponse>>() {});
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        return  response.getBody();
    }
}
