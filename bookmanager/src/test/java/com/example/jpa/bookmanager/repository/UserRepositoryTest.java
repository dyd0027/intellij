package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    //@Transactional
    void crud(){
        //이름 역순으로 검색
        //List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        //primaryKey로 지정한 걸로 찾기
        //List<User> users = userRepository.findAllById(Lists.newArrayList(1L,2L,3L));

        User user1 = new User("용휘","hangle@naver.com");
        User user2 = new User("소희","sh@naver.com");
        userRepository.saveAll(Lists.newArrayList(user1,user2));

        //@Transactional 필요함
        //User transactionUser = userRepository.getOne(1L);
        //System.out.println(transactionUser);

        User findByIdUser = userRepository.findById(1L).orElse(null);
        System.out.println(findByIdUser);

        long count = userRepository.count();
        System.out.println(count);

        userRepository.deleteById(1L);
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);

        //전부 삭제
        //userRepository.deleteAll();
//        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(2L,3L)));
        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(2L,3L)));
        users = userRepository.findAll();
        users.forEach(System.out::println);
    }

    @Test
    void page(){
        Page<User> users = userRepository.findAll(PageRequest.of(1,3));
        System.out.println("page: "+users);
        System.out.println("totalElements: "+users.getTotalElements());
        System.out.println("totalPages: "+users.getTotalPages());
        System.out.println("numberOfElements: "+users.getNumberOfElements());
        System.out.println("sort: "+users.getSort());
        System.out.println("size: "+users.getSize());
        users.getContent().forEach(System.out::println);
    }

    @Test
    void example(){
        // 해당 matcher로 like 검색 가능
        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("name")
                .withMatcher("email",contains()); // 양방향 검색
//                .withMatcher("email",endsWith()); // 끝이 같은거 검색
        Example<User> example = Example.of(new User("yh","naver"),matcher);
        userRepository.findAll(example).forEach(System.out::println);
        //matcher가 없으면 그냥 검색
        example = Example.of(new User("yh","naver.com"));
        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void update(){
        User user = new User("향뚱","sohee@naver.com");
        userRepository.save(user);
        user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("updateEmail@naver.com");
        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);
    }
}