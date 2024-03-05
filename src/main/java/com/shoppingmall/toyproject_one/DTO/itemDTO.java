package com.shoppingmall.toyproject_one.DTO;

import com.shoppingmall.toyproject_one.entity.item;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Data
@ToString
public class itemDTO {

    @NotNull
    private String item_id;

    @NotNull(message = "이미지를 업로드해주세요")
    private String item_img_id;

    @NotNull(message = "상품명을 입력해주세요")
    @Pattern(regexp = "^[\s가-힣a-zA-Z]{5,13}$", message = "한글, 영문, 또는 띄어쓰기로 5자 이상 13자 이하여야 합니다.")
    private String item_nm;

    @NotNull(message = "상품 가격을 입력해주세요")
    @Pattern(regexp = "^([1-9]\\d{0,2}(,\\d{3})*|10000000)$", message = "1 ~ 9,999,999 사이의 양수만 입력 가능합니다.")
    private String price;

    @NotNull(message = "수량을 선택해주세요")
    private String stock_number;

    @NotNull(message = "제품 상세 설명을 입력해주세요")
    private String item_detail;

    private String item_sell_status;

    private String filepath;


//    @Builder
//    public itemDTO(String item_id, String item_img_id, String item_nm, String price, String stock_number, String item_detail, String item_sell_status){
//
//        this.item_id = item_id;
//        this.item_img_id = item_img_id;
//        this.item_nm = item_nm;
//        this.price = price;
//        this.stock_number = stock_number;
//        this.item_detail = item_detail;
//        this.item_sell_status = item_sell_status;
//    }
//
//    public itemDTO(){
//
//    }

    public static itemDTO itemDTO(item item){
        itemDTO itemDTO = new itemDTO();
        itemDTO.setItem_id(item.getItem_id());
        itemDTO.setItem_img_id(item.getItem_img_id());
        itemDTO.setItem_nm(item.getItem_nm());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setStock_number(item.getStock_number());
        itemDTO.setItem_detail(item.getItem_detail());
        itemDTO.setItem_sell_status(item.getItem_sell_status());
        itemDTO.setFilepath(item.getFilepath());
        return itemDTO;
    }


}
