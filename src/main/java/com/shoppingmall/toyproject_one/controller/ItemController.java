package com.shoppingmall.toyproject_one.controller;

import com.shoppingmall.toyproject_one.entity.item;
import com.shoppingmall.toyproject_one.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/toyproject_one/item/write")
    public String itemWriteForm() {

        return "/admin_product/login_category/etc/item_registration";
    }

    // ** item_registration.html 파일 form주소와 동일하게
    @PostMapping(value = "/toyproject_one/item/writepro")
    public String itemWritePro(@ModelAttribute("item") item item, Model model, @RequestParam("file") MultipartFile file) throws Exception {
        itemService.write(item, file);

        model.addAttribute("message", "상품 등록이 완료되었습니다.");
        model.addAttribute("searchUrl", "/toyproject_one/item/list");

        return "message";
    }

    // 페이징 처리 -> pageable
    @GetMapping(value = "/toyproject_one/item/list")
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

        return "/admin_product/login_category/item_All";
    }

    @GetMapping(value = "/toyproject_one/item/view")
    public String itemView(Model model, @RequestParam("itemID") String itemID) {

        model.addAttribute("item", itemService.itemView(itemID));
        return "/admin_product/login_category/item_Detail";
    }

    @GetMapping(value = "/toyproject_one/item/delete")
    public String itemDelete(@RequestParam("itemID") String itemID, Model model) {

        itemService.itemDelete((itemID));

        model.addAttribute("message", "글 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/toyproject_one/item/list");

        return "message";
    }

    // 수정페이지
    @GetMapping(value = "/toyproject_one/item/modify/{itemID}")
    public String itemModify(Model model, @PathVariable("itemID") String itemID) {

        model.addAttribute("item", itemService.itemView(itemID));
        return "/admin_product/login_category/etc/item_modify";
    }

    // 수정 후 업데이트 처리
    @PostMapping(value = "/toyproject_one/item/update/{itemID}")
    public String itemUpdate(@PathVariable("itemID") String itemID, Model model, @ModelAttribute item item, @RequestParam("file") MultipartFile file) throws Exception {

        // 기존에 작성한 글 검색
        item itemTemp = itemService.itemView(item.getItemID());
        itemTemp.setItemID(item.getItemID());
        itemTemp.setImgID(item.getImgID());
        itemTemp.setCategory(item.getCategory());
        itemTemp.setItemNM(item.getItemNM());
        itemTemp.setPrice(item.getPrice());
        itemTemp.setStock_number(item.getStock_number());
//        itemTemp.setItem_detail(item.getItem_detail());
        itemTemp.setItem_sell_status(item.getItem_sell_status());
        itemTemp.setItem_img_filepath(item.getItem_img_filepath());
        itemService.write(itemTemp, file);

        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/toyproject_one/item/list");

        return "message";
    }

    // category값이 Outer인 것
    @GetMapping(value = "/toyproject_one/item/list/Outer")
    public String itemOuterForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {

        // 여기서 item_Outer 템플릿에 필요한 데이터를 모델에 추가합니다.
        List<item> outerItems = itemService.getItemsByCategory("Outer");
        model.addAttribute("outerItems", outerItems);
        // 페이징 처리
        int pageSize = pageable.getPageSize();
        int totalOuterItems = outerItems.size();
        int totalPages = (int) Math.ceil((double) totalOuterItems / pageSize);

        int nowPage = pageable.getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, totalPages);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        // 페이징 처리된 Outer 아이템 가져오기
        int startIndex = (nowPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalOuterItems);
        List<item> pagedOuterItems = outerItems.subList(startIndex, endIndex);
        model.addAttribute("pagedOuterItems", pagedOuterItems);

        return "admin_product/login_category/item_Outer";
    }

    @GetMapping(value = "/toyproject_one/item/list/Top")
    public String itemTopForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
        // 여기서 item_Outer 템플릿에 필요한 데이터를 모델에 추가합니다.
        List<item> topItems = itemService.getItemsByCategory("Top");
        model.addAttribute("topItems", topItems);

        // 페이징 처리
        int pageSize = pageable.getPageSize();
        int totalTopItems = topItems.size();
        int totalPages = (int) Math.ceil((double) totalTopItems / pageSize);

        int nowPage = pageable.getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, totalPages);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        // 페이징 처리된 Top 아이템 가져오기
        int startIndex = (nowPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalTopItems);
        List<item> pagedTopItems = topItems.subList(startIndex, endIndex);
        model.addAttribute("pagedTopItems", pagedTopItems);

        return "admin_product/login_category/item_Top";
    }

    @GetMapping(value = "/toyproject_one/item/list/Bottom")
    public String itemBottomForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
        // 여기서 item_Outer 템플릿에 필요한 데이터를 모델에 추가합니다.
        List<item> bottomItems = itemService.getItemsByCategory("Bottom");
        model.addAttribute("bottomItems", bottomItems);

        // 페이징 처리
        int pageSize = pageable.getPageSize();
        int totalTopItems = bottomItems.size();
        int totalPages = (int) Math.ceil((double) totalTopItems / pageSize);

        int nowPage = pageable.getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, totalPages);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        // 페이징 처리된 Top 아이템 가져오기
        int startIndex = (nowPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalTopItems);
        List<item> pagedTopItems = bottomItems.subList(startIndex, endIndex);
        model.addAttribute("pagedTopItems", pagedTopItems);

        return "admin_product/login_category/item_Bottom";
    }

    @GetMapping(value = "/toyproject_one/item/list/Dress")
    public String itemDressForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
        // 여기서 item_Outer 템플릿에 필요한 데이터를 모델에 추가합니다.
        List<item> dressItems = itemService.getItemsByCategory("Dress");
        model.addAttribute("dressItems", dressItems);

        // 페이징 처리
        int pageSize = pageable.getPageSize();
        int totalTopItems = dressItems.size();
        int totalPages = (int) Math.ceil((double) totalTopItems / pageSize);

        int nowPage = pageable.getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, totalPages);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        // 페이징 처리된 Top 아이템 가져오기
        int startIndex = (nowPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalTopItems);
        List<item> pagedTopItems = dressItems.subList(startIndex, endIndex);
        model.addAttribute("pagedTopItems", pagedTopItems);

        return "admin_product/login_category/item_Dress";
    }

    @GetMapping(value = "/toyproject_one/item/list/Accessories")
    public String itemAccessoriesForm(Model model, @PageableDefault(page = 0, size = 9, sort = "itemID", direction = Sort.Direction.DESC) Pageable pageable) {
        // 여기서 item_Outer 템플릿에 필요한 데이터를 모델에 추가합니다.
        List<item> accessoriesItems = itemService.getItemsByCategory("Accessories");
        model.addAttribute("accessoriesItems", accessoriesItems);

        // 페이징 처리
        int pageSize = pageable.getPageSize();
        int totalTopItems = accessoriesItems.size();
        int totalPages = (int) Math.ceil((double) totalTopItems / pageSize);

        int nowPage = pageable.getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, totalPages);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        // 페이징 처리된 Top 아이템 가져오기
        int startIndex = (nowPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalTopItems);
        List<item> pagedTopItems = accessoriesItems.subList(startIndex, endIndex);
        model.addAttribute("pagedTopItems", pagedTopItems);

        return "admin_product/login_category/item_Accessories";
    }

    // itemID 내림차순으로 9개 new admin_product 나타내기
    @GetMapping(value = "/toyproject_one/admin_main")
    public String getItems(Model model) {
        List<item> items = itemService.getItemsDescendingOrder(9);
        model.addAttribute("items", items);
        return "admin/admin_main";
    }
}