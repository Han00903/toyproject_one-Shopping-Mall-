package com.shoppingmall.toyproject_one.DTO;

import com.shoppingmall.toyproject_one.entity.admin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Data
@ToString

public class adminDTO {

    @NotNull (message = "아이디를 입력해주세요")
    private String admin_id;

    @NotNull(message = "비밀번호를 입력해주세요")
    private String admin_pw;

    @NotNull(message = "이름을 입력해주세요")
    private String admin_name;

    @NotNull(message = "전화번호를 입력해주세요")
    private String admin_phone;
//
//
//    @Builder
//    public adminDTO(String admin_id, String admin_pw, String admin_name, String admin_phone){
//        this.admin_id = admin_id;
//        this.admin_pw = admin_pw;
//        this.admin_name = admin_name;
//        this.admin_phone = admin_phone;
//    }
//
//    public adminDTO() {
//
//    }
    public static adminDTO adminDTO(admin admin) {
        adminDTO adminDTO = new adminDTO();
        adminDTO.setAdmin_id(admin.getAdmin_id());
        adminDTO.setAdmin_pw(admin.getAdmin_pw());
        adminDTO.setAdmin_name(admin.getAdmin_name());
        adminDTO.setAdmin_phone(admin.getAdmin_phone());
        return adminDTO;
    }
}
