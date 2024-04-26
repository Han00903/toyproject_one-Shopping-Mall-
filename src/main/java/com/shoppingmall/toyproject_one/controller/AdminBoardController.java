package com.shoppingmall.toyproject_one.controller;

import com.shoppingmall.toyproject_one.DTO.boardDTO;
import com.shoppingmall.toyproject_one.DTO.commentDTO;
import com.shoppingmall.toyproject_one.DTO.userDTO;
import com.shoppingmall.toyproject_one.entity.board;
import com.shoppingmall.toyproject_one.entity.user;
import com.shoppingmall.toyproject_one.service.BoardService;
import com.shoppingmall.toyproject_one.service.CommentService;
import com.shoppingmall.toyproject_one.service.ItemService;
import com.shoppingmall.toyproject_one.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AdminBoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/toyproject_one/admin_board/review_list") // 회원가입 페이지 출력 요청
    public String reviewListForm(Model model,
                                 @PageableDefault(page = 0, size = 9, sort = "boardCreatedTime", direction = Sort.Direction.DESC) Pageable pageable,
                                 @ModelAttribute("searchKeyword") String searchKeyword,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {
        Page<board> list;

        if (searchKeyword == null || searchKeyword.isEmpty()) {
            list = boardService.boardList(pageable);
        } else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        if (list.isEmpty()) {
            // 검색 결과가 없는 경우 메시지 출력 & 이전페이지로 이동
            model.addAttribute("message", "해당 회원이 존재하지 않습니다.");
            String referer = request.getHeader("Referer");
            model.addAttribute("searchUrl", referer);

            return "message";
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        // userDTOList 가져오기
        List<boardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);

        // 내림차순 저장
        model.addAttribute("boardList", list.getContent());


        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/admin_board/review_list";
    }

    @GetMapping(value = "/toyproject_one/admin_board/{itemID}/{boardID}")
    public String adBoardDetail (@PathVariable("boardID") String boardID,
                                 @PathVariable("itemID") String itemID, Model model,
                                 HttpSession session) {
        /*
         * 해당 게시글의 조회수를 하나 올리고
         * 게시글 데이터를 가져와서 user_list.html에 출력
         */
        boardDTO boardDTO = boardService.findByID(boardID);

        /*댓글 목록 가져오기*/
        List<commentDTO> commentDTOList = commentService.findAllByBoard(boardID);
        model.addAttribute("commentList", commentDTOList);

        System.out.println("댓글 목록을 가져와보자" + commentDTOList);

        /*세션 로그인 정보 가져오기*/
        String userID = (String) session.getAttribute("adminID");
        System.out.println("세션에 저장된 adminID: " + userID);
        model.addAttribute("adminID", userID);
        System.out.println("로그인된 아이디는 : " + userID);


        model.addAttribute("board", boardDTO);
        model.addAttribute("item", itemService.itemView(itemID));

        return "/admin_board/review_list_detail";
    }

    @GetMapping(value = "/toyproject_one/admin_board/delete/{itemID}/{boardID}")
    public String delete(@PathVariable("boardID") String boardID, @PathVariable("itemID") String itemID, Model model){
        boardService.delete(boardID);
        model.addAttribute("message", "글 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/toyproject_one/admin_board/review_list");

        return "message";
    }


    @GetMapping("/toyproject_one/admin_board/user_list") // 회원가입 페이지 출력 요청
    public String userListForm(Model model,
                               @PageableDefault(page = 0, size = 9, sort = "userID", direction = Sort.Direction.DESC) Pageable pageable, /* page 번호 */
                               @ModelAttribute("searchKeyword") String searchKeyword,
                               HttpServletRequest request,
                               RedirectAttributes redirectAttributes) {

        Page<user> list;

        if (searchKeyword == null || searchKeyword.isEmpty()) {
            list = userService.userList(pageable);
        } else {
            list = userService.userSearchList(searchKeyword, pageable);
        }

        if (list.isEmpty()) {
            // 검색 결과가 없는 경우 메시지 출력 & 이전페이지로 이동
            model.addAttribute("message", "해당 회원이 존재하지 않습니다.");
            String referer = request.getHeader("Referer");
            model.addAttribute("searchUrl", referer);

            return "message";
        }

        System.out.println("리스트 내역을 보자" + list);



        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        // userDTOList 가져오기
        List<userDTO> userDTOList = userService.findAll();
        model.addAttribute("userList", userDTOList);

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/admin_board/user_list";
    }

    @GetMapping(value = "/toyproject_one/user_list/{userID}")
    public String userDetail (@PathVariable("userID") String userID, Model model) {
        userDTO userDTO = userService.findByID(userID);
        model.addAttribute("user", userDTO); // 모델에 userDTO 추가

        System.out.println("userID는 "+ userDTO);
        return "/admin_board/user_list_detail";
    }
    @GetMapping(value = "/toyproject_one/user_list/delete/{userID}")
    public String user_delete(@PathVariable("userID") String userID, Model model){
        userService.delete(userID);
        model.addAttribute("message", "회원 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/toyproject_one/admin_board/user_list");

        return "message";
    }


}
