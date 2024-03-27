package com.shoppingmall.toyproject_one.controller;


import com.shoppingmall.toyproject_one.DTO.boardDTO;
import com.shoppingmall.toyproject_one.DTO.userDTO;
import com.shoppingmall.toyproject_one.entity.item;
import com.shoppingmall.toyproject_one.service.BoardService;
import com.shoppingmall.toyproject_one.service.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private HttpSession session;

    @Autowired
    private ItemService itemService;

    @Autowired
    private BoardService boardService;

    // 게시판 글쓰기 Form
    @GetMapping(value = "/toyproject_one/user_board/write/{itemID}")
    public String boardWriteForm(Model model, @PathVariable("itemID") String itemID) {
        String userID = (String) session.getAttribute("userID");
        model.addAttribute("userID", userID);
        model.addAttribute("item", itemService.itemView(itemID));
        return "/user_board/user_write";
    }

    @PostMapping(value = "/toyproject_one/user_board/write/{itemID}")
    public String boardWrite(Model model, @PathVariable("itemID") String itemID, @ModelAttribute boardDTO boardDTO) {
        String userID = (String) session.getAttribute("userID"); // 세션에서 현재 로그인된 사용자의 아이디를 가져옴
        boardDTO.setUserID(userID); // 게시판 작성자로 현재 로그인된 사용자의 아이디 설정

        model.addAttribute("item", itemService.itemView(itemID));
        System.out.println("BoardDTO = " + boardDTO);

        boardService.save(boardDTO);
        return "/user_board/user_write";
    }

    @GetMapping(value = "/toyproject_one/user_board")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 user_list.html에 보냄
        List<boardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);

        return "/user_board/user_list";
    }

    @GetMapping(value = "/toyproject_one/user_board/{boardID}")
    public String findByID(@PathVariable("boardID") String boardID, Model model) {
        /*
        * 해당 게시글의 조회수를 하나 올리고
        * 게시글 데이터를 가져와서 user_list.html에 출력
        */
        boardService.updateHits(boardID);
        boardDTO boardDTO = boardService.findByID(boardID);

        model.addAttribute("board", boardDTO);
        return "/user_board/user_boardDetail";
    }

    // 수정 요청
    @GetMapping(value = "toyproject_one/user_board/update/{boardID}")
    public String updateForm(@PathVariable("boardID") String boardID, Model model){
        boardDTO boardDTO = boardService.findByID(boardID);
        model.addAttribute("boardUpdate", boardDTO);
        return "/user_board/user_update";
    }


}











