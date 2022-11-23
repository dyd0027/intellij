package com.example.jpa.bookmanager.service;

import com.example.jpa.bookmanager.domain.User;
import com.example.jpa.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Test
    void entityManagerTest(){
        System.out.println("여기로 와");
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
    }
    @Test
    void cacheFindTest(){
//        User user = new User() ;
//        user.setId(1L);
//        userRepository.save(user);
        System.out.println("여기"+userRepository.findById(1L));
//        System.out.println("여기"+userRepository.findById(1L).get());
//        System.out.println("여기"+userRepository.findById(1L).get());
//        System.out.println("여기"+userRepository.findById(1L).get());
//        System.out.println(userRepository.findByEmail("self0027@naver.com"));
    }
}
