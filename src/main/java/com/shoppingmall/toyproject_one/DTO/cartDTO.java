package com.shoppingmall.toyproject_one.DTO;

import com.shoppingmall.toyproject_one.entity.board;
import com.shoppingmall.toyproject_one.entity.cart;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class cartDTO {

    @Id
    @NotNull
    private String cartID;

    @NotNull
    @Column(name = "user_id")
    private String userID;

    @NotNull
    @Column(name = "item_image")
    private String itemImage;

    @NotNull
    private String itemID;

    @NotNull
    @Column(name = "item_name")

    private String itemName;

    @NotNull
    private int price;

    @NotNull
    private int Number;
//
//    @NotNull
//    private int Total;


    public static cartDTO cartDTO(cart cart) {
        cartDTO cartDTO = new cartDTO();
        cartDTO.setCartID(cart.getCartID());
        cartDTO.setUserID(cart.getUserID());
        cartDTO.setItemImage(cart.getItemImage());
        cartDTO.setItemID(cart.getItemID());
        cartDTO.setItemName(cart.getItemName());
        cartDTO.setPrice(cart.getPrice());
        cartDTO.setNumber(cart.getNumber());

        return cartDTO;
    }



}
