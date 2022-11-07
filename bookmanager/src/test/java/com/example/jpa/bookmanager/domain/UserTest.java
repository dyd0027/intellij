package com.example.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void test(){
        User user = new User();
        user.setName("김소희");
        user.setEmail("sh@naver.com");

        User allArg = new User("권용휘","self@naver.com", LocalDateTime.now(),LocalDateTime.now());

        User requierdArg = new User("권용","ef@nav.ercom");

    }
}