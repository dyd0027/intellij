package com.example.objectmapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    private String name;
    private int age;
    @JsonProperty("phone_number")
    private String phoneNumber;

    public User(){
        this.name = null;
        this.age =0 ;
        this.phoneNumber = null;
    }
    public User(String name, int age){
        this.age = age;
        this.name = name;
    }
    public User(String name, int age, String phoneNumber){
        this.name=name;
        this.age=age;
        this.phoneNumber=phoneNumber;
    }
}
