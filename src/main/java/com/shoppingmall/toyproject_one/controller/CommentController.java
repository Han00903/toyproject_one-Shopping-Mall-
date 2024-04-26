package com.shoppingmall.toyproject_one.controller;

import com.shoppingmall.toyproject_one.DTO.commentDTO;
import com.shoppingmall.toyproject_one.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping(value = "/toyproject_one/admin_board/{itemID}/{boardID}/save")
    public ResponseEntity<?> save(@ModelAttribute commentDTO commentDTO, @PathVariable("boardID") String boardID, @PathVariable("itemID") String itemID, Model model) {
        System.out.println("commentDTO = " + commentDTO);
        String saveResult = commentService.save(commentDTO);
        if (saveResult != null) {
            // 작성 성공하면 댓글목록 가져와 리턴
            // 댓글목록: 해당 게시글의 댓글 전체
            List<commentDTO> commentDTOList = commentService.findAllByBoard(commentDTO.getBoardID());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }

}