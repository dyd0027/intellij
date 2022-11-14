package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Gender;
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

    @Test
    void select(){
          //Is와 Equal은 가독성을 위해 붙히지 아무 상관 없음
//        System.out.println(userRepository.findByName("yh"));
//        System.out.println(userRepository.findByNameIs("yh"));
//        System.out.println(userRepository.findByNameEqual("yh"));
//        System.out.println("findByEmail: "+userRepository.findByEmail("self0027@naver.com"));
//        System.out.println("getByEmail: "+userRepository.getByEmail("self0027@naver.com"));
//        System.out.println("readByEmail: "+userRepository.readByEmail("self0027@naver.com"));
//        System.out.println("queryByEmail: "+userRepository.queryByEmail("self0027@naver.com"));
//        System.out.println("searchByEmail: "+userRepository.searchByEmail("self0027@naver.com"));
//        System.out.println("streamByEmail: "+userRepository.streamByEmail("self0027@naver.com"));
//        System.out.println("findUserByEmail: "+userRepository.findUserByEmail("self0027@naver.com"));
//
//        System.out.println("findSomethingByEmail: "+userRepository.findSomethingByEmail("self0027@naver.com"));
//
//        System.out.println("findFirst1ByEmail: "+userRepository.findFirst2ByEmail("self0027@naver.com"));
//        System.out.println("findTop1ByEmail(: "+userRepository.findTop2ByEmail("self0027@naver.com"));

        System.out.println("findByEmailAndName: "+userRepository.findByEmailAndName("self0027@naver.com","yh"));
        System.out.println("findByOrAndName: "+userRepository.findByEmailOrName("self0027@naver.com","yong"));
        // After: 초과값 >
        System.out.println("findByCreatedAtAfter: "+userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter: "+userRepository.findByIdAfter(3L));
        // GreaterThan: 초과값 >
        // GreaterThanEqual: 이상 >=
        System.out.println("findByCreatedAtGreaterThan: "+userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual: "+userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L) ));
        //between: < > 초과값
        //findByIdGreaterThanEqualAndLessThan: 이상, 이하값
        System.out.println("findByCreatedAtBetween: "+userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L),LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween: "+userRepository.findByIdBetween( 1L,4L));
        System.out.println("findByIdGreaterThanEqualAndLessThan: "+userRepository.findByIdGreaterThanEqualAndIdLessThan( 1L,4L));
        //값이 null이 아닌 값 가져오기
        System.out.println("findByIdIsNotNull: "+userRepository.findByIdIsNotNull());
//        System.out.println("findByAddressIsNotEmpty: "+userRepository.findByAddressIsNotEmpty());
        //In절
        System.out.println("findByNameIn: "+userRepository.findByNameIn(Lists.newArrayList("yh","yong")));
        //like 검색 -> 마지막 함수는 %를 넣어서 검색
        System.out.println("findByNameStartingWith: "+userRepository.findByNameStartingWith("on"));
        System.out.println("findByNameEndingWith: "+userRepository.findByNameEndingWith("on"));
        System.out.println("findByNameContains: "+userRepository.findByNameContains("on"));
        System.out.println("findByNameLike: "+userRepository.findByNameLike("%on%"));

    }
    @Test
    void PagingAndSortingTest(){
        //Sort
        System.out.println("findTop1ByName: "+userRepository.findTop1ByName("yh"));
        System.out.println("findTop1ByNameOrderByIdDesc: "+userRepository.findTop1ByNameOrderByIdDesc("yh"));
        // OrderBy 같은 경우 내가 원하는 조건을 and나or없이 그냥 적어줌.
        System.out.println("findFirstByNameOrderByIdDescEmailAsc: "+userRepository.findFirstByNameOrderByIdDescEmailAsc("yh"));
        //Sort의 조건이 길어지면 메소드 명이 길어지니 밑에 메소드와같이 매개변수로 Sort를 넣어준다.
        System.out.println("findFirstByNameWithSortParam: "+userRepository.findFirstByName("yh",Sort.by(Sort.Order.desc("id"),Sort.Order.asc("email"))));
        System.out.println("findByNameWithPaging: "+userRepository.findByName("yh",PageRequest.of(0,1,Sort.by(Sort.Order.asc("id")))).getContent());

    }
    @Test
    void insertAndUpdateTest(){
        User user = new User();
        user.setName("yh");
        user.setEmail("sss@namver.com");
        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("yyyyyyyg");
        userRepository.save(user2);
    }
    @Test
    void enumTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);
        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);
        System.out.println(userRepository.findRawRecord().get("gender"));
    }
}