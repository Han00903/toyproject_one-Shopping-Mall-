package com.shoppingmall.toyproject_one.entity;

import com.shoppingmall.toyproject_one.DTO.commentDTO;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comment")
public class comment extends boardBase {

        @Id
        @Column(name = "comment_id")
        private String commentID;

        @ManyToOne(fetch = FetchType.LAZY) //board:comment = 1:N
        @JoinColumn(name = "board_id")
        private board board;

        @Column(name = "comment_writer")
        private String commentWriter;

        @Column(name = "comment_contents")
        private String commentContents;

        @Column(name = "comment_created_time")
        private LocalDateTime commentCreatedTime;

        @Column(name = "board_created_time")
        private LocalDateTime boardCreatedTime;

    public static comment toSaveEntity(commentDTO commentDTO, board board) {
            comment Comment = new comment();
            Comment.setCommentID(commentDTO.getCommentID());
            Comment.setBoard(board);
            Comment.setCommentWriter(commentDTO.getCommentWriter());
            Comment.setCommentContents(commentDTO.getCommentContents());
            return Comment;
    }
}