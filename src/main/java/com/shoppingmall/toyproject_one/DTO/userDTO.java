package com.shoppingmall.toyproject_one.DTO;

import com.shoppingmall.toyproject_one.entity.user;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@Data
@ToString

public class userDTO {
    @NotNull(message = "아이디를 입력해주세요")
    @Column(name = "user_id")

    private String userID;

    @NotNull(message = "비밀번호를 입력해주세요")
    private String user_pw;

    @NotNull(message = "이름을 입력해주세요")
    private String user_name;

    @NotNull(message = "성별을 선택해주세요")
    private String user_gender;

    @NotNull(message = "주소를 입력해주세요")
    private String user_address;

    @NotNull(message = "이메일을 입력해주세요")
    private String user_email;

    @NotNull(message = "전화번호를 입력해주세요")
    private String user_phone;

//    @Builder
//    public userDTO(String user_id, String user_pw, String user_name, String user_gender, String user_address, String user_email, String user_phone){
//        this.user_id = user_id;
//        this.user_pw = user_pw;
//        this.user_name = user_name;
//        this.user_gender = user_gender;
//        this.user_address = user_address;
//        this.user_email = user_email;
//        this.user_phone = user_phone;
//    }
//
//    public userDTO() {
//
//    }

    public static userDTO userDTO(user user) {
        userDTO userDTO = new userDTO();
        userDTO.setUserID(user.getUserID());
        userDTO.setUser_pw(user.getUser_pw());
        userDTO.setUser_name(user.getUser_name());
        userDTO.setUser_gender(user.getUser_gender());
        userDTO.setUser_address(user.getUser_address());
        userDTO.setUser_email(user.getUser_email());
        userDTO.setUser_phone(user.getUser_phone());
        return userDTO;
    }
}
