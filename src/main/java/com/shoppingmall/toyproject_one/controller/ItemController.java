package com.shoppingmall.toyproject_one.controller;

import com.shoppingmall.toyproject_one.DTO.itemDTO;
import com.shoppingmall.toyproject_one.entity.item;
import com.shoppingmall.toyproject_one.repository.ItemRepository;
import com.shoppingmall.toyproject_one.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private ItemService itemService;


    @GetMapping(value = "/toyproject_one/item")
    public String item_data(){
        return "/product/item";
    }

    @GetMapping(value = "/toyproject_one/item/list")
    public String item_list(Model model){

        model.addAttribute("list", itemService.itemList());

        return "/product/item_list";
    }

    @GetMapping(value = "/toyproject_one/item/view")
    public String itemView(Model model, @RequestParam("item_id") String itemId){

        model.addAttribute("item", itemService.itemView(itemId));
        return "/product/item_view";
    }

    @PostMapping("/toyproject_one/item")
    public String item_save(@Valid @ModelAttribute itemDTO itemDTO, Errors errors, Model model) {

        if (errors.hasErrors()) {
            // 에러가 발생한 경우
            Map<String, String> validatorResult = itemService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            // 입력한 값도 콘솔에 출력
            System.out.println("입력한 값: " + itemDTO);
            return "/product/item";
        } else {
            // 에러가 발생하지 않은 경우
            itemService.save(itemDTO);
            return "/product/item_list"; // 아이템 목록 화면
        }

    }

}
