package com.shoppingmall.toyproject_one.entity;

import com.shoppingmall.toyproject_one.DTO.boardDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "board")
public class board extends boardBase{

    @Id
    @Column(name = "board_id")
    private String boardID;

    @Column(name = "board_pw")
    private String boardPW;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "item_id")
    private String itemID;

    @Column(name = "item_img_filepath")
    private String boardImgFilepath = ""; // 또는 적절한 기본값 설정

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "board_hits")
    private int boardHits;

    public static board board(boardDTO boardDTO) {
        board board = new board();
        board.setBoardID(boardDTO.getBoardID());
        board.setBoardPW(boardDTO.getBoardPW());
        board.setUserID(boardDTO.getUserID());
        board.setItemID(boardDTO.getItemID());
        board.setBoardImgFilepath(boardDTO.getBoardImgFilepath());
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContent(boardDTO.getBoardContent());
        board.setBoardHits(0);

        return board;
    }
}