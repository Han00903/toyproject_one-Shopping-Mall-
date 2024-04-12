package com.shoppingmall.toyproject_one.controller;

import com.shoppingmall.toyproject_one.DTO.boardDTO;
import com.shoppingmall.toyproject_one.entity.item;
import com.shoppingmall.toyproject_one.service.BoardService;
import com.shoppingmall.toyproject_one.service.CartService;
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
public class ItemUserController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private BoardService boardService;


    // 페이징 처리 -> pageable
    @GetMapping(value = "/toyproject_one/user_item/list")
    public String itemList(Model model,
                           @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable, /* page 번호 */
                           @ModelAttribute("searchKeyword") String searchKeyword,
                           HttpServletRequest request,
                           RedirectAttributes redirectAttributes) { /* 검색 기능 *******왜 맨위 한개만 검색이 가능한거지???????????*/

        Page<item> list;

        if (searchKeyword == null || searchKeyword.isEmpty()) {
            list = itemService.itemList(pageable);
        } else {
            list = itemService.itemSearchList(searchKeyword, pageable);
        }

        if (list.isEmpty()) {
            // 검색 결과가 없는 경우 메시지 출력 & 이전페이지로 이동
            model.addAttribute("message", "해당 상품이 존재하지 않습니다.");
            String referer = request.getHeader("Referer");
            model.addAttribute("searchUrl", referer);

            return "message";
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/user_product/login_category/user_All";
    }

    @GetMapping(value = "/toyproject_one/user_view")
    public String itemView(Model model, @RequestParam("itemID") String itemID, HttpSession session) {
        //세션에서 userID 값 가져오기
        String userID = (String) session.getAttribute("userID");

        List<boardDTO> boardDTOList = boardService.findAll();

        model.addAttribute("userID", userID);
        model.addAttribute("boardList", boardDTOList);
        model.addAttribute("item", itemService.itemView(itemID));

        return "user_product/login_category/user_Detail";
    }

    // category값이 Outer인 것
    @GetMapping(value = "/toyproject_one/user_item/list/Outer")
    public String itemOuterForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {

        // 페이징 처리된 Outer 아이템 목록 가져오기
        Page<item> outerItemsPage = itemService.getItemsByCategory("Outer", pageable);
        // 페이징 처리된 Top 아이템 목록을 모델에 추가
        model.addAttribute("outerItems", outerItemsPage);

        // 페이징 처리된 정보를 모델에 추가
        model.addAttribute("totalPages", outerItemsPage.getTotalPages());
        model.addAttribute("nowPage", outerItemsPage.getNumber() + 1);
        model.addAttribute("startPage", Math.max(1, outerItemsPage.getNumber() - 4));
        model.addAttribute("endPage", Math.min(outerItemsPage.getTotalPages(), outerItemsPage.getNumber() + 5));

        return "user_product/login_category/user_Outer";
    }

    @GetMapping(value = "/toyproject_one/user_item/list/Top")
    public String itemTopForm (Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {

        // 페이징 처리된 Top 아이템 목록 가져오기
        Page<item> topItemsPage = itemService.getItemsByCategory("Top", pageable);
        // 페이징 처리된 Top 아이템 목록을 모델에 추가
        model.addAttribute("topItems", topItemsPage);

        // 페이징 처리된 정보를 모델에 추가
        model.addAttribute("totalPages", topItemsPage.getTotalPages());
        model.addAttribute("nowPage", topItemsPage.getNumber() + 1);
        model.addAttribute("startPage", Math.max(1, topItemsPage.getNumber() - 4));
        model.addAttribute("endPage", Math.min(topItemsPage.getTotalPages(), topItemsPage.getNumber() + 5));

        return "user_product/login_category/user_Top";
    }

    @GetMapping(value = "/toyproject_one/user_item/list/Bottom")
    public String itemBottomForm (Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {

        // 페이징 처리된 Bottom 아이템 목록 가져오기
        Page<item> bottomItemsPage = itemService.getItemsByCategory("Bottom", pageable);
        // 페이징 처리된 Bottom 아이템 목록을 모델에 추가
        model.addAttribute("bottomItems", bottomItemsPage);

        // 페이징 처리된 정보를 모델에 추가
        model.addAttribute("totalPages", bottomItemsPage.getTotalPages());
        model.addAttribute("nowPage", bottomItemsPage.getNumber() + 1);
        model.addAttribute("startPage", Math.max(1, bottomItemsPage.getNumber() - 4));
        model.addAttribute("endPage", Math.min(bottomItemsPage.getTotalPages(), bottomItemsPage.getNumber() + 5));

        return "user_product/login_category/user_Bottom";
    }

    @GetMapping(value = "/toyproject_one/user_item/list/Dress")
    public String itemDressForm (Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {

        // 페이징 처리된 Dress 아이템 목록 가져오기
        Page<item> dressItemsPage = itemService.getItemsByCategory("Dress", pageable);
        // 페이징 처리된 Dress 아이템 목록을 모델에 추가
        model.addAttribute("dressItems", dressItemsPage);

        // 페이징 처리된 정보를 모델에 추가
        model.addAttribute("totalPages", dressItemsPage.getTotalPages());
        model.addAttribute("nowPage", dressItemsPage.getNumber() + 1);
        model.addAttribute("startPage", Math.max(1, dressItemsPage.getNumber() - 4));
        model.addAttribute("endPage", Math.min(dressItemsPage.getTotalPages(), dressItemsPage.getNumber() + 5));



        return "user_product/login_category/user_Dress";
    }

    @GetMapping(value = "/toyproject_one/user_item/list/Accessories")
    public String itemAccessoriesForm (Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {

        // 페이징 처리된 Accessories 아이템 목록 가져오기
        Page<item> accessoriesItemsPage = itemService.getItemsByCategory("Accessories", pageable);
        // 페이징 처리된 Accessories 아이템 목록을 모델에 추가
        model.addAttribute("accessoriesItems", accessoriesItemsPage);

        // 페이징 처리된 정보를 모델에 추가
        model.addAttribute("totalPages", accessoriesItemsPage.getTotalPages());
        model.addAttribute("nowPage", accessoriesItemsPage.getNumber() + 1);
        model.addAttribute("startPage", Math.max(1, accessoriesItemsPage.getNumber() - 4));
        model.addAttribute("endPage", Math.min(accessoriesItemsPage.getTotalPages(), accessoriesItemsPage.getNumber() + 5));

        return "user_product/login_category/user_Accessories";
    }

    @GetMapping(value = "toyproject_one/user_main")
    public String main(Model model) {
        List<item> items = itemService.getItemsDescendingOrder(9);
        model.addAttribute("items", items);
        return "user/user_main";
    }


}