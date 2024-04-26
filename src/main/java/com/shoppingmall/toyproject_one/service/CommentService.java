package com.shoppingmall.toyproject_one.service;

import com.shoppingmall.toyproject_one.DTO.commentDTO;
import com.shoppingmall.toyproject_one.entity.board;
import com.shoppingmall.toyproject_one.entity.comment;
import com.shoppingmall.toyproject_one.repository.BoardRepository;
import com.shoppingmall.toyproject_one.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public String save(commentDTO commentDTO) {
        //부모엔티티(board)우선 조회
        Optional<board> optionalBoard = boardRepository.findById(commentDTO.getBoardID());
        if (optionalBoard.isPresent()) {
            // DTO로 받아온걸 Entity로 변환하기
            board board = optionalBoard.get();
            comment Comment = comment.toSaveEntity(commentDTO, board);
            return commentRepository.save(Comment).getCommentID();
        } else {
            return null;
        }
    }

/*DTO로 받아온걸 Entity로 변환하는 방법 (이거 3가지 비교해보기)
    1. comment comment = comment.toSaveEntity(commentDTO);
    2. comment comment = new comment();
    3. Builder 어노테이션 사용
 */
//
    public List<commentDTO> findAllByBoard (String boardID){
        board board = boardRepository.findById(boardID).get();
        List<comment> commentList = commentRepository.findAllByBoard(board);
        /*Entity -> DTOList로 변환*/
        List<commentDTO> commentDTOList = new ArrayList<>();
        for (comment comment: commentList){
            commentDTO commentDTO = com.shoppingmall.toyproject_one.DTO.commentDTO.commentDTO(comment, boardID);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}