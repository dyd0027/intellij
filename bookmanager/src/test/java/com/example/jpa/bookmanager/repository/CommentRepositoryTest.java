package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void commentTest(){
        Comment comment = new Comment();
        comment.setComment("ㅂㄹㅂㄹ");
//        comment.setCommentedAt(LocalDateTime.now());

        commentRepository.saveAndFlush(comment);

//        System.out.println(commentRepository.findById(4L));
        commentRepository.findAll().forEach(System.out::println);
    }
}