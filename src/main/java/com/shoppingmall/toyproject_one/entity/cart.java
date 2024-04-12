package com.shoppingmall.toyproject_one.entity;

import com.shoppingmall.toyproject_one.DTO.cartDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "cart")
public class cart {

    @Id
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


//    @ Column
//    private int Total;

    public static cart cart(cartDTO cartDTO) {
        cart cart = new cart();
        cart.setCartID(cartDTO.getCartID());
        cart.setUserID(cartDTO.getUserID());
        cart.setItemImage(cartDTO.getItemImage());
        cart.setItemID(cartDTO.getItemID());
        cart.setItemName(cartDTO.getItemName());
        cart.setPrice(cartDTO.getPrice());
        cart.setNumber(cartDTO.getNumber());

        return cart;
    }



}
