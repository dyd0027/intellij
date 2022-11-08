package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void crud(){
        //이름 역순으로 검색
        //List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        //primaryKey로 지정한 걸로 찾기
        //List<User> users = userRepository.findAllById(Lists.newArrayList(1L,2L,3L));

        User user1 = new User("용휘","hangle@naver.com");
        User user2 = new User("소희","sh@naver.com");
        userRepository.saveAll(Lists.newArrayList(user1,user2));
        List<User> users = userRepository.findAll();
        //@Transactional 필요함
        //User transactionUser = userRepository.getOne(1L);
        //System.out.println(transactionUser);
        users.forEach(System.out::println);
        User findByIdUser = userRepository.findById(1L).orElse(null);
        System.out.println(findByIdUser);
    }
}