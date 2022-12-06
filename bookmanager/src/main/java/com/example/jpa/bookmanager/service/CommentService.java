package com.example.jpa.bookmanager.service;

import com.example.jpa.bookmanager.domain.Comment;
import com.example.jpa.bookmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void init(){
        for (int i=0;i<10;i++){
            Comment comment = new Comment();
            comment.setComment("짱짱");
            commentRepository.save(comment);
        }
    }
    @Transactional(readOnly = true)
    public void updateSomething(){
        List<Comment> comments = commentRepository.findAll();
        for(Comment comment : comments){
            comment.setComment("변합니다.");
            commentRepository.save(comment);
        }
    }
}
