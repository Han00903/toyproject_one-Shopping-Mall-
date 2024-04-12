package com.shoppingmall.toyproject_one.service;

import com.shoppingmall.toyproject_one.DTO.cartDTO;
import com.shoppingmall.toyproject_one.entity.cart;
import com.shoppingmall.toyproject_one.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public void save(cartDTO cartDTO){
        cart cart = com.shoppingmall.toyproject_one.entity.cart.cart(cartDTO);

        cartRepository.save(cart);
    }
    public void delete(String userID ){
        cartRepository.deleteById(userID);
    }

    public void deleteByUserID(String userID) { // 메서드명 수정
        List<cart> cartList = cartRepository.findByUserID(userID); // 메서드명 수정
        cartRepository.deleteAll(cartList);
    }
    public void saveCartItem(String cartID, String userID, String itemImgFilepath, String itemID, String itemNM, int price, int quantity) {
        cart cartItem = new cart();
        cartItem.setCartID(cartID);
        cartItem.setUserID(userID);
        cartItem.setItemImage(itemImgFilepath);
        cartItem.setItemID(itemID);
        cartItem.setItemName(itemNM);
        cartItem.setPrice(price);
        cartItem.setNumber(quantity);

        cartRepository.save(cartItem);
    }

    public List<cartDTO> findAll(){
        List<cart> cartList = cartRepository.findAll();
        List<cartDTO> cartDTOList = new ArrayList<>();
        for(cart cart: cartList){
            cartDTO cartDto = cartDTO.cartDTO(cart);
            cartDto.setUserID(cart.getUserID()); // cartDTO에 userID 설정
            cartDTOList.add(cartDto);
        }
        return cartDTOList;
    }


}
