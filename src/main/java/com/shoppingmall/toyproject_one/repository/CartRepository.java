package com.shoppingmall.toyproject_one.repository;

import com.shoppingmall.toyproject_one.entity.cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<cart, String> {
    List<cart> findByUserID(String userID); // 메서드명 수정
}