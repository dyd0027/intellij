package com.example.restaurant.wishlist.repository;

import com.example.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.restaurant.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;
//db로 작동한당
@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {

}
