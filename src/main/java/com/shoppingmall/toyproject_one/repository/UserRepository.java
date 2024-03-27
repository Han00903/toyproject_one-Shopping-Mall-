package com.shoppingmall.toyproject_one.repository;

import com.shoppingmall.toyproject_one.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<user, String> {
    // UserRepository가 JpaRepository로 확장하여 "user"엔터티를 조작하기 위한 메서드를 제공

    //이메일로 회원 정보 조회 (select * from user where user_id = ?)
    @Override
    Optional<user> findById(String userID);

}


