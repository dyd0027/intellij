package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookReviewInfoRepositoryTest {
    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Test
    void crudTest(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setReviewCount(2);
        bookReviewInfo.setAverageReviewScore(4.5f);

        bookReviewInfoRepository.save(bookReviewInfo);
        System.out.println(bookReviewInfoRepository.findAll());
    }
}