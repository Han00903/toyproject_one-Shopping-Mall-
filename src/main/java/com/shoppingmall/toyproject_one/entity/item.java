package com.shoppingmall.toyproject_one.entity;

import com.shoppingmall.toyproject_one.DTO.itemDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "item")
public class item {

    @Id
    @Column(name = "item_id")
    private String itemID;

    @Column(name = "item_img_id")
    private String imgID = "";

    @ Column(nullable = false)
    private String category;

    @ Column(name = "item_nm")
    private String itemNM;

    @ Column(nullable = false)
    private String price;

    @ Column(nullable = false)
    private String stock_number;

    @ Column(nullable = false)
    private String item_detail;

    @ Column(nullable = false)
    private String item_sell_status;

    @ Column(nullable = false)
    private String item_img_filepath = ""; // 또는 적절한 기본값 설정




    public static item item(itemDTO itemDTO){

        item item = new item();
        item.setItemID(itemDTO.getItemID());
        item.setImgID(itemDTO.getImgID());
        item.setCategory(itemDTO.getCategory());
        item.setItemNM(itemDTO.getItemNM());
        item.setPrice(itemDTO.getPrice());
        item.setStock_number(itemDTO.getStock_number());
        item.setItem_detail(itemDTO.getItem_detail());
        item.setItem_sell_status(itemDTO.getItem_sell_status());
        item.setItem_img_filepath(itemDTO.getItem_img_filepath());
        return item;
    }
}