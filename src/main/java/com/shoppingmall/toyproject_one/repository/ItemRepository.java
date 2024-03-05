package com.shoppingmall.toyproject_one.repository;

import com.shoppingmall.toyproject_one.entity.item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ItemRepository extends JpaRepository<item, String> {

//이메일로 회원 정보 조회 (select * from user where user_id = ?)
//    @Override
//    Optional<item> findById(String user_id);
}
