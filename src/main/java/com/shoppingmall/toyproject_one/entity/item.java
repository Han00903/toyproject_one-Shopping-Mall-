package com.shoppingmall.toyproject_one.entity;

import com.shoppingmall.toyproject_one.DTO.itemDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "item")
public class item {

    @Id
    private String item_id;

    @ Column(nullable = false)
    private String item_img_id;

    @ Column(nullable = false)
    private String item_nm;

    @ Column(nullable = false)
    private String price;

    @ Column(nullable = false)
    private String stock_number;

    @ Column(nullable = false)
    private String item_detail;

    @ Column(nullable = false)
    private String item_sell_status;

    @ Column(nullable = false)
    private String filepath;


    public static item item(itemDTO itemDTO){

        item item = new item();
        item.setItem_id(itemDTO.getItem_id());
        item.setItem_img_id(itemDTO.getItem_img_id());
        item.setItem_nm(itemDTO.getItem_nm());
        item.setPrice(itemDTO.getPrice());
        item.setStock_number(itemDTO.getStock_number());
        item.setItem_detail(itemDTO.getItem_detail());
        item.setItem_sell_status(itemDTO.getItem_sell_status());
        item.setFilepath(itemDTO.getFilepath());
        return item;
    }







}
