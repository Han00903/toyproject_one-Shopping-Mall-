package com.shoppingmall.toyproject_one.controller;

import com.shoppingmall.toyproject_one.DTO.boardDTO;
import com.shoppingmall.toyproject_one.service.BoardService;
import com.shoppingmall.toyproject_one.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String boardWrite(Model model, @PathVariable("itemID") String itemID, @ModelAttribute boardDTO boardDTO,
                             HttpServletRequest request) {
        String userID = (String) session.getAttribute("userID"); // 세션에서 현재 로그인된 사용자의 아이디를 가져옴
        boardDTO.setUserID(userID); // 게시판 작성자로 현재 로그인된 사용자의 아이디 설정

        model.addAttribute("item", itemService.itemView(itemID));
        System.out.println("BoardDTO = " + boardDTO);

        boardService.save(boardDTO);

        model.addAttribute("message", "리뷰 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/toyproject_one/user_view?itemID=" + itemID);
        return "message";
    }

    @GetMapping(value = "/toyproject_one/user_board")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 user_list.html에 보냄
        List<boardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);

        return "/user_board/user_list";
    }

    @GetMapping(value = "/toyproject_one/user_board/{itemID}/{boardID}")
    public String userBoardDetail (@PathVariable("boardID") String boardID,@PathVariable("itemID") String itemID, Model model) {
        /*
        * 해당 게시글의 조회수를 하나 올리고
        * 게시글 데이터를 가져와서 user_list.html에 출력
        */
        boardService.updateHits(boardID);
        boardDTO boardDTO = boardService.findByID(boardID);
        model.addAttribute("board", boardDTO);
        model.addAttribute("item", itemService.itemView(itemID));

        return "/user_board/user_boardDetail";
    }

    @GetMapping(value = "/toyproject_one/us_board/{itemID}/{boardID}")
    public String usBoardDetail (@PathVariable("boardID") String boardID,@PathVariable("itemID") String itemID, Model model) {
        /*
         * 해당 게시글의 조회수를 하나 올리고
         * 게시글 데이터를 가져와서 user_list.html에 출력
         */
        boardService.updateHits(boardID);
        boardDTO boardDTO = boardService.findByID(boardID);
        model.addAttribute("board", boardDTO);
        model.addAttribute("item", itemService.itemView(itemID));

        return "/user_board/us_boardDetail";
    }

    // 수정 요청
    @GetMapping(value = "/toyproject_one/user_board/update/{itemID}/{boardID}")
    public String updateForm(@PathVariable("boardID") String boardID,@PathVariable("itemID") String itemID, Model model){
        boardDTO boardDTO = boardService.findByID(boardID);

        model.addAttribute("item", itemService.itemView(itemID));
        System.out.println("BoardDTO = " + boardDTO);

        model.addAttribute("boardUpdate", boardDTO);
        return "/user_board/user_update";
    }

    @PostMapping(value = "/toyproject_one/user_board/update/{itemID}/{boardID}")
    public String update(@ModelAttribute boardDTO boardDTO, @PathVariable("itemID") String itemID, Model model) {
        boardDTO board = boardService .update(boardDTO);
        model.addAttribute("item", itemService.itemView(itemID));
        model.addAttribute("board", board);
        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/toyproject_one/user_view?itemID=" + itemID);

        return "message";
    }

    // 삭제
    @GetMapping(value = "/toyproject_one/user_board/delete/{itemID}/{boardID}")
    public String delete(@PathVariable("boardID") String boardID,@PathVariable("itemID") String itemID, Model model){
        boardService.delete(boardID);
        model.addAttribute("message", "글 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/toyproject_one/user_view?itemID=" + itemID);

        return "message";
    }

//    //페이징 처리 (/toyproject_one/user_board/paging?page=1)
//    @GetMapping(value = "/toyproject_one/user_board/paging")
//    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
//        pageable.getPageNumber();
//        Page<boardDTO> list = boardService.paging(pageable);
////        int blockLimit = 5;
////        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit))) - 1) * blockLimit + 1 ;
////        int endPage = ((startPage + blockLimit -1) < list.getTotalPages()) ? startPage + blockLimit -1 : list.getTotalPages();
//
//        int nowPage = list.getPageable().getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, list.getTotalPages());
//
//        model.addAttribute("list", list);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        return "/user_board/user_list";
//    }
}