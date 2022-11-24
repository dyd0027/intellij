package com.example.jpa.bookmanager.service;

import com.example.jpa.bookmanager.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;

    //비영속성 (new)
    @Transactional
    public void put(){
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");

        entityManager.persist(user);
        /*아래 set한 부분이 업데이트가 안됨->준영속성*/
        entityManager.detach(user);
        user.setName("newUserAfterPersist");
        /*merge시켜줌으로서 영속성이 됨*/
        entityManager.merge(user);
    }
}
