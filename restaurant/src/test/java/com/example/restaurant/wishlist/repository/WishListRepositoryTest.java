package com.example.restaurant.wishlist.repository;

import com.example.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Target;

@SpringBootTest
public class WishListRepositoryTest {
    @Autowired
    private WishListRepository wishListRepository;
    private WishListEntity create(){
        var wishList = new WishListEntity();
        wishList.setTitle("title");
        wishList.setAddress("adrress");
        wishList.setCategory("category");
        wishList.setHomePageLink("homepage");
        wishList.setImageLink("imagelink");
        wishList.setVisitCount(0);
        wishList.setReadAddress("readAd");
        wishList.setVisit(false);
        wishList.setLastVisitDate(null);
        return wishList;
    }

    @Test
    public void saveTest(){
        var wishListEntity = create();
        var excepted = wishListRepository.save(wishListEntity);
        Assertions.assertNotNull(excepted);
        Assertions.assertEquals(1,excepted.getIndex());
    }@Test
    public void updateTest(){
        var wishListEntity = create();
        var excepted = wishListRepository.save(wishListEntity);

        excepted.setTitle("update Title");
        var saveEntity = wishListRepository.save(excepted);

        Assertions.assertEquals("update Title",saveEntity.getTitle());
        Assertions.assertEquals(1,wishListRepository.listAll().size());
    }
    @Test
    public void findByIdTest(){
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);
        var excepted = wishListRepository.findById(1);
        Assertions.assertEquals(true,excepted.isPresent());
        Assertions.assertEquals(1,excepted.get().getIndex());

    }
    @Test
    public void deleteTest(){
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);
        wishListRepository.deleteById(1);
        int cnt = wishListRepository.listAll().size();
        Assertions.assertEquals(0,cnt);
    }
    @Test
    public void listAllTest(){
        var wishListEntity1 = create();
        wishListRepository.save(wishListEntity1);
        var wishListEntity2 = create();
        wishListRepository.save(wishListEntity2);

        int cnt = wishListRepository.listAll().size();
        Assertions.assertEquals(2,cnt);

    }
}
