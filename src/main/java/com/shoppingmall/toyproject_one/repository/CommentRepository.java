package com.shoppingmall.toyproject_one.repository;

import com.shoppingmall.toyproject_one.entity.board;
import com.shoppingmall.toyproject_one.entity.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<comment, String> {
//     select * from comment where board order by board_id desc;
    List<comment> findAllByBoard (board board);
}