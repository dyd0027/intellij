package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String name);
    User findByEmail(String email);
    User getByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String email);
    User findSomethingByEmail(String email);
    List<User> findFirst2ByEmail(String email);
    List<User> findTop2ByEmail(String email);
    List<User> findByEmailAndName(String email,String name);
    List<User> findByEmailOrName(String email,String name);
    List<User> findByCreatedAtAfter(LocalDateTime localDateTime);
    List<User> findByIdAfter(Long id);
    List<User> findByCreatedAtGreaterThan(LocalDateTime localDateTime);
    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime localDateTime);
    List<User> findByCreatedAtBetween(LocalDateTime yesterday,LocalDateTime tomorrow);
    List<User> findByIdBetween(Long id1,Long id2);
    List<User> findByIdGreaterThanEqualAndIdLessThan(Long id1,Long id2);
}
