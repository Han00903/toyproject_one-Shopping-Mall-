package com.shoppingmall.toyproject_one.entity;

import com.shoppingmall.toyproject_one.DTO.userDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Table(name = "user")
public class user {

    @Id
    @Column(name = "user_id")
    @NotNull(message = "아이디를 입력해주세요")

    // @GeneratedValue( strategy = GenerationType.IDENTITY) // DB에 의해 자동으로 증가하는 PK값을 사용하도록 지정
    // @GeneratedValue : JPA 엔터티의 PK의 값을 자동으로 생성하기 위해 사용 ( DB에 새로운 레코드를 추가할 때마다 자동으로 증가하는 값을 부여 )
    // strategy 속성 : 주 키 값을 생성하는 전략을 선택하는데 사용
    // GenerationType.IDENTITY : DB가 자동으로 PK값을 관리하도록 하는 옵션
    private String userID;

    @NotNull(message = "비밀번호를 입력해주세요")
    private String user_pw;

    @ Column(nullable = false)
    private String user_name;

    @ Column
    private String user_gender;

    @ Column(nullable = false)
    private String user_address;

    @ Column
    private String user_email;

    @ Column(nullable = false)
    private String user_phone;

//    @Builder
//    public user(String user_id, String user_pw, String user_name, String user_gender, String user_address, String user_email, String user_phone){
//        this.user_id = user_id;
//        this.user_pw = user_pw;
//        this.user_name = user_name;
//        this.user_gender = user_gender;
//        this.user_address = user_address;
//        this.user_email = user_email;
//        this.user_phone = user_phone;
//    }
//
//    public user() {
//
//    }

    public static user user(userDTO userDTO){

        user user = new user(); // DTO에 있는 값을 entity로 넘기기 때문에 (DTO, entity)로 작성
        user.setUserID(userDTO.getUser_id());
        user.setUser_pw(userDTO.getUser_pw());
        user.setUser_name(userDTO.getUser_name());
        user.setUser_gender(userDTO.getUser_gender());
        user.setUser_address(userDTO.getUser_address());
        user.setUser_email(userDTO.getUser_email());
        user.setUser_phone(userDTO.getUser_phone());
        return user;
    }



}
