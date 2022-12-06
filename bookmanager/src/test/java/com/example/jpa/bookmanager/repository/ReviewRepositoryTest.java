package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Transactional
    void reviewTest(){
//        List<Review> list = reviewRepository.findAll();
//        List<Review> list = reviewRepository.findAllByFetchJoin();
        List<Review> list = reviewRepository.findAllByEntityGraph();
//        System.out.println(list);
        System.out.println("전체를 불러 옴");

        System.out.println(list.get(0).getComments());
        System.out.println("첫번째 리뷰의 코멘트");

        System.out.println(list.get(1).getComments());
        System.out.println("두번째 리뷰의 코멘트");
    }
}
