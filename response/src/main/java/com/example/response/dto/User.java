package com.example.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null일 경우 뱉어내지 않는다. 이건 api명세서 만들 때 상의해서 넣기
public class User {
    private String name;

    //int로 할 경우 default: 0, integer로 할 경우 default: null
    private Integer age;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

}
