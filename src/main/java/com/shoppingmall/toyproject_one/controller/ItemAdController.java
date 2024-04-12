package com.shoppingmall.toyproject_one.controller;

import com.shoppingmall.toyproject_one.entity.item;
import com.shoppingmall.toyproject_one.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ItemAdController {

    @Autowired
    private ItemService itemService;


//    // 페이징 처리 -> pageable
//    @GetMapping(value = "/toyproject_one/item/ad_list")
//    public String itemList(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
//
//        Page<item> list = itemService.itemList(pageable);
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
//        return "/admin_product/logout_category/All";
//    }
//
//    @GetMapping(value = "/toyproject_one/ad_item/view")
//    public String itemView(Model model, @RequestParam("itemID") String itemID) {
//
//        model.addAttribute("item", itemService.itemView(itemID));
//        return "/admin_product/logout_category/Detail";
//    }

    // category값이 Outer인 것
//    @GetMapping(value = "/toyproject_one/item/ad_list/Outer")
//    public String itemOuterForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
//
//        // 여기서 item_Outer 템플릿에 필요한 데이터를 모델에 추가합니다.
//        List<item> outerItems = itemService.getItemsByCategory("Outer");
//        model.addAttribute("outerItems", outerItems);
//        // 페이징 처리
//        int pageSize = pageable.getPageSize();
//        int totalOuterItems = outerItems.size();
//        int totalPages = (int) Math.ceil((double) totalOuterItems / pageSize);
//
//        int nowPage = pageable.getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, totalPages);
//
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        // 페이징 처리된 Outer 아이템 가져오기
//        int startIndex = (nowPage - 1) * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, totalOuterItems);
//        List<item> pagedOuterItems = outerItems.subList(startIndex, endIndex);
//        model.addAttribute("pagedOuterItems", pagedOuterItems);
//
//        return "admin_product/logout_category/Outer";
//    }
//
//    @GetMapping(value = "/toyproject_one/item/ad_list/Top")
//    public String itemTopForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
//        // 여기서 item_Outer 템플릿에 필요한 데이터를 모델에 추가합니다.
//        List<item> topItems = itemService.getItemsByCategory("Top");
//        model.addAttribute("topItems", topItems);
//
//        // 페이징 처리
//        int pageSize = pageable.getPageSize();
//        int totalTopItems = topItems.size();
//        int totalPages = (int) Math.ceil((double) totalTopItems / pageSize);
//
//        int nowPage = pageable.getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, totalPages);
//
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        // 페이징 처리된 Top 아이템 가져오기
//        int startIndex = (nowPage - 1) * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, totalTopItems);
//        List<item> pagedTopItems = topItems.subList(startIndex, endIndex);
//        model.addAttribute("pagedTopItems", pagedTopItems);
//
//        return "admin_product/logout_category/Top";
//    }

//    @GetMapping(value = "/toyproject_one/item/ad_list/Bottom")
//    public String itemBottomForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
//        // 여기서 item_Outer 템플릿에 필요한 데이터를 모델에 추가합니다.
//        List<item> bottomItems = itemService.getItemsByCategory("Bottom");
//        model.addAttribute("bottomItems", bottomItems);
//
//        // 페이징 처리
//        int pageSize = pageable.getPageSize();
//        int totalTopItems = bottomItems.size();
//        int totalPages = (int) Math.ceil((double) totalTopItems / pageSize);
//
//        int nowPage = pageable.getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, totalPages);
//
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        // 페이징 처리된 Top 아이템 가져오기
//        int startIndex = (nowPage - 1) * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, totalTopItems);
//        List<item> pagedTopItems = bottomItems.subList(startIndex, endIndex);
//        model.addAttribute("pagedTopItems", pagedTopItems);
//
//        return "admin_product/logout_category/Bottom";
//    }

//    @GetMapping(value = "/toyproject_one/item/ad_list/Dress")
//    public String itemDressForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
//        // 여기서 item_Outer 템플릿에 필요한 데이터를 모델에 추가합니다.
//        List<item> dressItems = itemService.getItemsByCategory("Dress");
//        model.addAttribute("dressItems", dressItems);
//
//        // 페이징 처리
//        int pageSize = pageable.getPageSize();
//        int totalTopItems = dressItems.size();
//        int totalPages = (int) Math.ceil((double) totalTopItems / pageSize);
//
//        int nowPage = pageable.getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, totalPages);
//
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        // 페이징 처리된 Top 아이템 가져오기
//        int startIndex = (nowPage - 1) * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, totalTopItems);
//        List<item> pagedTopItems = dressItems.subList(startIndex, endIndex);
//        model.addAttribute("pagedTopItems", pagedTopItems);
//
//        return "admin_product/logout_category/Dress";
//    }

//    @GetMapping(value = "/toyproject_one/item/ad_list/Accessories")
//    public String itemAccessoriesForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
//        // 여기서 item_Outer 템플릿에 필요한 데이터를 모델에 추가합니다.
//        List<item> accessoriesItems = itemService.getItemsByCategory("Accessories");
//        model.addAttribute("accessoriesItems", accessoriesItems);
//
//        // 페이징 처리
//        int pageSize = pageable.getPageSize();
//        int totalTopItems = accessoriesItems.size();
//        int totalPages = (int) Math.ceil((double) totalTopItems / pageSize);
//
//        int nowPage = pageable.getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, totalPages);
//
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        // 페이징 처리된 Top 아이템 가져오기
//        int startIndex = (nowPage - 1) * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, totalTopItems);
//        List<item> pagedTopItems = accessoriesItems.subList(startIndex, endIndex);
//        model.addAttribute("pagedTopItems", pagedTopItems);
//
//        return "admin_product/logout_category/Accessories";
//    }


    // itemID 내림차순으로 9개 new product 나타내기 (admin 로그아웃상태)
    @GetMapping(value = "/toyproject_one/ad_main")
    public String getNewItems(Model model) {
        List<item> items = itemService.getItemsDescendingOrder(9);
        model.addAttribute("items", items);
        return "admin/ad_main";
    }
}


