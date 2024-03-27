package com.shoppingmall.toyproject_one.DTO;

import com.shoppingmall.toyproject_one.entity.board;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@ToString
public class boardDTO {

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

    @Column(name = "board_created_time")
    private LocalDateTime boardCreatedTime;

    @Column(name = "board_update_time")
    private LocalDateTime boardUpdatedTime;

    public static boardDTO boardDTO(board board) {
        boardDTO boardDTO = new boardDTO();
        boardDTO.setBoardID(board.getBoardID());
        boardDTO.setBoardPW(board.getBoardPW());
        boardDTO.setUserID(board.getUserID());
        boardDTO.setItemID(board.getItemID());
        boardDTO.setBoardImgFilepath(board.getBoardImgFilepath());
        boardDTO.setBoardTitle(board.getBoardTitle());
        boardDTO.setBoardContent(board.getBoardContent());
        boardDTO.setBoardHits(board.getBoardHits());
        boardDTO.setBoardCreatedTime(board.getBoardCreatedTime());
        boardDTO.setBoardUpdatedTime(board.getBoardUpdatedTime());

        return boardDTO;
    }


}
