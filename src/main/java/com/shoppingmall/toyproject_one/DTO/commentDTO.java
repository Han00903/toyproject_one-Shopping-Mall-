package com.shoppingmall.toyproject_one.DTO;

import com.shoppingmall.toyproject_one.entity.comment;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class commentDTO {
    @Id
    @Column(name = "comment_id")
    private String commentID;

    @Column(name = "board_id")
    private String boardID;

    @Column(name = "comment_writer")
    private String commentWriter;

    @Column(name = "comment_contents")
    private String commentContents;

    @Column(name = "comment_created_time")
    private LocalDateTime commentCreatedTime;

    @Column(name = "board_created_time")
    private LocalDateTime boardCreatedTime;

    public static commentDTO commentDTO(comment comment, String boardID){
        commentDTO commentDTO = new commentDTO();
        commentDTO.setCommentID(comment.getCommentID());
        commentDTO.setBoardID(boardID);
        commentDTO.setCommentWriter(comment.getCommentWriter());
        commentDTO.setCommentContents(comment.getCommentContents());
        commentDTO.setCommentCreatedTime(comment.getCommentCreatedTime());
        return commentDTO;
    }
}
