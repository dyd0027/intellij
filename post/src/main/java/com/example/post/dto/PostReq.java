package com.example.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostReq {
    private String account;
    private String email;
    private String password;
    private String address;

    @JsonProperty("phone_number") // uri에 표기법(스네이크 케이스)과 java상의 표기법(하멜 케이스)이 다른경우 이런식으로
    private String phoneNumber;
}
