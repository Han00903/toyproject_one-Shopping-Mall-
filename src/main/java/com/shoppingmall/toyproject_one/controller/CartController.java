package com.shoppingmall.toyproject_one.controller;

import com.shoppingmall.toyproject_one.DTO.cartDTO;
import com.shoppingmall.toyproject_one.entity.cart;
import com.shoppingmall.toyproject_one.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

//    @GetMapping("/toyproject_one/user_item/cartList/{userID}/payment")
//    public ModelAndView payPage(@RequestParam(required = false, name = "selectedItemsJson") String selectedItemsJson) {
//        ModelAndView modelAndView = new ModelAndView("user_product/login_category/etc/user_payment");
//
//        if (selectedItemsJson != null && !selectedItemsJson.isEmpty()) {
//            // sessionStorage에서 저장된 데이터를 불러와서 출력
//            modelAndView.addObject("selectedItemsJson", selectedItemsJson);
//        } else {
//            // 데이터가 없는 경우 처리
//            modelAndView.addObject("selectedItemsJson", "No items selected.");
//        }
//
//        return modelAndView;
//    }















//    @PostMapping("/saveSelectedItems")
//    public @ResponseBody
//    ResponseEntity<String> saveSelectedItems(@RequestBody List<cart> selectedItems, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.setAttribute("selectedItems", selectedItems);
//
//        System.out.println(selectedItems);
//        // 리다이렉트할 URL을 JSON 형식으로 반환
//        String redirectUrl = "user_payment.html"; // 변경 가능
//        return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
//    }


//    @GetMapping(value = "/toyproject_one/user_item/cartList/delete/{cartID}")
//    public String user_delete(@PathVariable("cartID") String cartID, Model model){
//        cartService.deleteCartById(cartID); // 해당 상품 ID를 사용하여 상품 삭제
//        model.addAttribute("message", "상품 삭제가 완료되었습니다.");
//        model.addAttribute("searchUrl", "/toyproject_one/user_item/cartList"); // 삭제 후 장바구니 목록으로 이동
//
//        return "message"; // 결과를 보여줄 뷰의 이름
//    }

//    @GetMapping("/carts")
//    public String getCarts(Model model) {
//        List<Cart> carts = cartService.getAllCarts();
//        model.addAttribute("carts", carts);
//        return "carts";
//    }

//    @PostMapping("/deleteCarts")
//    public String deleteCarts(String[] cartIds) {
//        if (cartIds != null && cartIds.length > 0) {
//            List<Long> cartIdList = Stream.of(cartIds)
//                    .map(Long::valueOf)
//                    .collect(Collectors.toList());
//            cartService.deleteCarts(cartIdList);
//        }
//        return "redirect:/carts";
//    }
//}

}
