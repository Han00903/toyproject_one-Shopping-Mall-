package com.shoppingmall.toyproject_one.repository;

import com.shoppingmall.toyproject_one.entity.order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<order, String> {
    



}
