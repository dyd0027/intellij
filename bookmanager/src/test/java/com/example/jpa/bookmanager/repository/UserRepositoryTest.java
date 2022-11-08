package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud(){
        userRepository.save(new User(null,"권용휘","self@naver.com", LocalDateTime.now(),LocalDateTime.now()));
        System.out.println(">>>>>"+userRepository.findAll());
    }
}