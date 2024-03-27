package com.shoppingmall.toyproject_one.repository;

import com.shoppingmall.toyproject_one.entity.board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<board, String> {

    // 조회수 증가
    // update board set boardHits=boardHits + 1 where boardID=?
    @Modifying
    @Query("update board b set b.boardHits = b.boardHits + 1 where b.boardID = :boardID")
    void updateHits(@Param("boardID") String boardID);
}