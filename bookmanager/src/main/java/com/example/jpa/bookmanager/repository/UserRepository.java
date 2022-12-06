package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String name);
//    List<User> findByNameIs(String name);
//    List<User> findByNameEqual(String name);
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
    List<User> findByIdIsNotNull();
//    List<User> findByAddressIsNotEmpty(); //Empty같은 경우 null이 아닌 "" 값 임.
    List<User> findByNameIn(List<String> names);  // In절의 같은 경우 많은 조건이 들어가면 성능의 문제가 생김
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);
    List<User> findByNameLike(String name);
    List<User> findTop1ByName(String name);
    List<User> findTop1ByNameOrderByIdDesc(String name);
    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);
    List<User> findFirstByName(String name, Sort sort);
    Page<User> findByName(String name, Pageable pageable);
    @Query(value = "select * from users limit 1",nativeQuery = true)
    Map<String,Object> findRawRecord();
    @Query(value = "select * from users",nativeQuery = true)
    List<Map<String,Object>> findAllRawRecord();
}
