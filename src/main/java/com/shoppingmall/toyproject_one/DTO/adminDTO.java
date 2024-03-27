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
    @Pattern(regexp = "^[a-zA-Z0-9_]{5,15}$", message = "한글, 영문, 또는 띄어쓰기로 5자 이상 15자 이하여야 합니다.")
    @NotNull(message = "아이디를 입력해주세요")
    private String admin_id;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}$", message = "비밀번호는 최소 8자 이상이어야 하며, 영문, 숫자, 특수문자가 모두 포함되어야 합니다.")
    @NotNull(message = "비밀번호를 입력해주세요")
    private String admin_pw;

    @Pattern(regexp = "^[가-힣a-zA-Z]+$", message = "한글과 영문 알파벳으로만 입력해주세요.")
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
