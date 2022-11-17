package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Book;
import com.example.jpa.bookmanager.domain.Publisher;
import com.example.jpa.bookmanager.domain.Review;
import com.example.jpa.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("용휘짱");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());

    }
    @Test
    @Transactional
    void bookRelationTest(){
        givenBookAndReview();
        User user = userRepository.findByEmail("self0027@naver.com");
        System.out.println("Review: "+user.getReviews());
        System.out.println("Book: "+user.getReviews().get(0).getBook());
        System.out.println("Publisher:"+ user.getReviews().get(0).getBook().getPublisher());

    }
    private void givenBookAndReview(){

        givenReview(givenUser(),givenBook(givenPublisher()));
    }
    private void givenReview(User user, Book book){
        Review review = new Review();
        review.setTitle("나루토");
        review.setContent("어그로끌었따");
        review.setScore(5.0f);
        review.setBook(book);
        review.setUser(user);

        reviewRepository.save(review);
    }
    private User givenUser(){
        return userRepository.findByEmail("self0027@naver.com");
    }
    private Book givenBook(Publisher publisher){
        Book book = new Book();
        book.setName("jpa초격자");
        book.setPublisher(publisher);
        return bookRepository.save(book);
    }
    private Publisher givenPublisher(){
        Publisher publisher=new Publisher();
        publisher.setName("천재교육");
        return publisherRepository.save(publisher);
    }
}
