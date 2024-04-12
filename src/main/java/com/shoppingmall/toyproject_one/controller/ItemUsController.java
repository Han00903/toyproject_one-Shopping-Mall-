package com.shoppingmall.toyproject_one.controller;


import com.shoppingmall.toyproject_one.entity.item;
import com.shoppingmall.toyproject_one.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ItemUsController {

    @Autowired
    private ItemService itemService;


    // 페이징 처리 -> pageable
    @GetMapping(value = "/toyproject_one/us_item/list")
    public String itemList(Model model,
                           @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable,
                           @ModelAttribute("searchKeyword") String searchKeyword,
                           HttpServletRequest request,
                           RedirectAttributes redirectAttributes) {

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

        return "/user_product/logout_category/us_All";
    }


    @GetMapping(value = "/toyproject_one/us_view")
    public String itemView(Model model, @RequestParam("itemID") String itemID) {

        model.addAttribute("item", itemService.itemView(itemID));
        return "/user_product/logout_category/us_Detail";
    }

    // category값이 Outer인 것
    @GetMapping(value = "/toyproject_one/us_item/list/Outer")
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


        return "user_product/logout_category/us_Outer";
    }

    @GetMapping(value = "/toyproject_one/us_item/list/Top")
    public String itemTopForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {

        // 페이징 처리된 Top 아이템 목록 가져오기
        Page<item> topItemsPage = itemService.getItemsByCategory("Top", pageable);
        // 페이징 처리된 Top 아이템 목록을 모델에 추가
        model.addAttribute("topItems", topItemsPage);

        // 페이징 처리된 정보를 모델에 추가
        model.addAttribute("totalPages", topItemsPage.getTotalPages());
        model.addAttribute("nowPage", topItemsPage.getNumber() + 1);
        model.addAttribute("startPage", Math.max(1, topItemsPage.getNumber() - 4));
        model.addAttribute("endPage", Math.min(topItemsPage.getTotalPages(), topItemsPage.getNumber() + 5));

        return "user_product/logout_category/us_Top";
    }

    @GetMapping(value = "/toyproject_one/us_item/list/Bottom")
    public String itemBottomForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
        // 페이징 처리된 Bottom 아이템 목록 가져오기
        Page<item> bottomItemsPage = itemService.getItemsByCategory("Bottom", pageable);
        // 페이징 처리된 Bottom 아이템 목록을 모델에 추가
        model.addAttribute("bottomItems", bottomItemsPage);

        // 페이징 처리된 정보를 모델에 추가
        model.addAttribute("totalPages", bottomItemsPage.getTotalPages());
        model.addAttribute("nowPage", bottomItemsPage.getNumber() + 1);
        model.addAttribute("startPage", Math.max(1, bottomItemsPage.getNumber() - 4));
        model.addAttribute("endPage", Math.min(bottomItemsPage.getTotalPages(), bottomItemsPage.getNumber() + 5));

        return "user_product/logout_category/us_Bottom";
    }

    @GetMapping(value = "/toyproject_one/us_item/list/Dress")
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

        return "user_product/logout_category/us_Dress";
    }

    @GetMapping(value = "/toyproject_one/us_item/list/Accessories")
    public String itemAccessoriesForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
        // 페이징 처리된 Accessories 아이템 목록 가져오기
        Page<item> accessoriesItemsPage = itemService.getItemsByCategory("Accessories", pageable);
        // 페이징 처리된 Accessories 아이템 목록을 모델에 추가
        model.addAttribute("accessoriesItems", accessoriesItemsPage);

        // 페이징 처리된 정보를 모델에 추가
        model.addAttribute("totalPages", accessoriesItemsPage.getTotalPages());
        model.addAttribute("nowPage", accessoriesItemsPage.getNumber() + 1);
        model.addAttribute("startPage", Math.max(1, accessoriesItemsPage.getNumber() - 4));
        model.addAttribute("endPage", Math.min(accessoriesItemsPage.getTotalPages(), accessoriesItemsPage.getNumber() + 5));

        return "user_product/logout_category/us_Accessories";
    }

    @GetMapping(value = "toyproject_one/main")
    public String main(Model model) {
        List<item> items = itemService.getItemsDescendingOrder(9);
        model.addAttribute("items", items);

        return "/user/main";
    }
}
