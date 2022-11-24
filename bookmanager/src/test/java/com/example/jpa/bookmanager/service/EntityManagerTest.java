package com.example.jpa.bookmanager.service;

import com.example.jpa.bookmanager.domain.User;
import com.example.jpa.bookmanager.repository.UserRepository;
import org.assertj.core.util.Lists;
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
        insertUser();
        System.out.println("여기로 와");
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
    }
    @Test
    void cacheFindTest(){
        insertUser();
        /*@Tranactional이 없다면 같은 메소드를 시키면 3번 연속 쿼리문을 발생시킴*/
//        System.out.println("여기"+userRepository.findById(1L).get());
//        System.out.println("여기"+userRepository.findById(1L).get());
//        System.out.println("여기"+userRepository.findById(1L).get());
        userRepository.deleteById(1L);

    }
    @Test
    void cacheFindTest2(){
        insertUser();
        User user = userRepository.findById(1L).get();
        user.setName("YYYYYYh");
        userRepository.save(user);
//        userRepository.flush(); /*DB에 바로 반영해 줌*/

        System.out.println("ggggggggggggggggggggggggggggggggggggg");

        user.setEmail("TYYYH@nanerv.om");
        userRepository.save(user);

        //DB에는 적용이 되지 않았지만 영속성컨텍스트에 저장되어 있음으로 저장된 값으로 보임.
        System.out.println("1>>>>>>"+userRepository.findById(1L).get());
        //DB에 저장하는 flush를 사용 > DB에 저장됨.
        userRepository.flush();
        System.out.println("2>>>>>>"+userRepository.findById(1L).get());
    }
    @Test
    void cacheFindTest3(){
        insertUser();
        User user = userRepository.findById(1L).get();
        user.setName("YYYYYYh");
        userRepository.save(user);
//        userRepository.flush(); /*DB에 바로 반영해 줌*/

        System.out.println("ggggggggggggggggggggggggggggggggggggg");

        user.setEmail("TYYYH@nanerv.om");
        userRepository.save(user);
        System.out.println("여기서는 자동 flush가 됨"+userRepository.findAll());
    }
    public void insertUser(){
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        User user5 = new User();
        user1.setId(1L);
        user1.setName("yh");
        user1.setEmail("self0027@naver.com");

        user2.setId(2L);
        user2.setName("y2h");
        user2.setEmail("2self0027@naver.com");

        user3.setId(3L);
        user3.setName("y3h");
        user3.setEmail("3self0027@naver.com");

        user4.setId(4L);
        user4.setName("y4h");
        user4.setEmail("4self0027@naver.com");

        user5.setId(5L);
        user5.setName("y5h");
        user5.setEmail("5self0027@naver.com");

        userRepository.saveAll(Lists.newArrayList(user1,user2,user3,user4,user5));
    }
}
