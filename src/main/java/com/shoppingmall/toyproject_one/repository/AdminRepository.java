package com.shoppingmall.toyproject_one.repository;

import com.shoppingmall.toyproject_one.entity.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<admin, String> {


    @Override
    Optional<admin> findById (String admin_id);

}
