package com.shoppingmall.toyproject_one.controller;

import com.shoppingmall.toyproject_one.DTO.cartDTO;
import com.shoppingmall.toyproject_one.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CartController {

    @Autowired
    private HttpSession session;
    @Autowired
    private CartService cartService;

    @PostMapping("/toyproject_one/user_item/user_cart/{userID}")
    public String cartForm(@PathVariable("userID") String userID, Model model, @ModelAttribute cartDTO cartDTO) {
        // cartDTO에 userID 설정
        cartDTO.setUserID(userID);

        cartService.save(cartDTO);
        System.out.println(cartDTO);

        // 모델에 cartDTO 추가
        model.addAttribute("cart", cartDTO);

        model.addAttribute("message", "장바구니에 추가되었습니다.");
        model.addAttribute("searchUrl", "/toyproject_one/user_item/cartList/{userID}");
        return "message";
    }

    @GetMapping("/toyproject_one/user_item/cartList/{userID}")
    public String findAll(@PathVariable("userID") String userID, Model model){
        model.addAttribute("userID", userID);
        List<cartDTO> cartDTOList = cartService.findAll();
        model.addAttribute("cartList", cartDTOList);
        return "user_product/login_category/etc/user_cart";
    }
    @GetMapping(value = "/toyproject_one/user_item/cartList/delete/{userID}")
    public String user_delete(@PathVariable("userID") String userID, Model model){
        cartService.deleteByUserID(userID); // 메서드명 수정
        model.addAttribute("message", "상품 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/toyproject_one/user_item/cartList/{userID}");

        return "message";
    }

}
