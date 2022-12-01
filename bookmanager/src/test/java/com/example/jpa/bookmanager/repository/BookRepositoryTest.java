package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Book;
import com.example.jpa.bookmanager.domain.Publisher;
import com.example.jpa.bookmanager.domain.Review;
import com.example.jpa.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

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

    @Test
    @Transactional
    void bookCascadeTest(){
        Book book = new Book();
        book.setName("JPA!!!");

        Publisher publisher = new Publisher();
        publisher.setName("패컴");

        book.setPublisher(publisher);
        bookRepository.save(book);

        System.out.println("books: "+bookRepository.findAll());
        System.out.println("publisher: "+publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("바뀐다리?");
        bookRepository.save(book1);

        System.out.println("publisher2: "+publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();
        book2.setPublisher(null);
        bookRepository.save(book2);
        System.out.println("books: "+bookRepository.findAll());
        System.out.println("publisher: "+publisherRepository.findAll());

    }
    @Test
    void bookRemoveCascadeTest(){
        bookRepository.deleteById(1L);

        System.out.println("books:: "+bookRepository.findAll());
        System.out.println("publisher:: "+publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }
    @Test
    void softDelete(){

        bookRepository.findAllByDeletedFalse().forEach(System.out::println);
        bookRepository.findByCategoryIsNullAndDeletedFalse().forEach(System.out::println);
    }
    @Test
    void queryTest(){
        System.out.println("긴 쿼리이름: "+
                bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
                "bookName",
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().minusDays(1)));
        System.out.println("findByNameRecently: "+ bookRepository.findByNameRecently(
                "bookName",
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().minusDays(1)));
        bookRepository.findByBookNameAndCategory().forEach(b -> System.out.println(b.getName()+" : "+b.getCategory()));

        bookRepository.findByBookNameAndCategory(PageRequest.of(1,1))
                .forEach(bookNameAndCategory -> System.out.println(bookNameAndCategory.getName()+":"+bookNameAndCategory.getCategory()));
        bookRepository.findByBookNameAndCategory(PageRequest.of(0,1))
                .forEach(bookNameAndCategory -> System.out.println(bookNameAndCategory.getName()+":"+bookNameAndCategory.getCategory()));
    }
    @Test
    void nativeQueryTest(){
//        bookRepository.findAll().forEach(System.out::println);
//        bookRepository.findByAllCustom().forEach(System.out::println);
        List<Book> books = bookRepository.findAll();
        for(Book book : books){
            book.setCategory("IT");
        }
        bookRepository.saveAll(books);
        System.out.println(bookRepository.findAll());

        System.out.println("affectedRow : "+bookRepository.updateCategories());
        System.out.println(bookRepository.findAll());
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
