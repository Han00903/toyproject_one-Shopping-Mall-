package com.shoppingmall.toyproject_one.entity;

import com.shoppingmall.toyproject_one.DTO.adminDTO;
import com.shoppingmall.toyproject_one.DTO.userDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "admin")
public class admin {

    @Id
    private String admin_id;

    @Column(nullable = false)
    private String admin_pw;

    @Column(nullable = false)
    private String admin_name;

    @Column(nullable = false)
    private String admin_phone;

//    @Builder
//    public admin (String admin_id, String admin_pw, String admin_name, String admin_phone){
//        this.admin_id = admin_id;
//        this.admin_pw = admin_pw;
//        this.admin_name = admin_name;
//        this.admin_phone = admin_phone;
//    }
//
//    public admin() {
//
//    }

    public static admin admin(adminDTO adminDTO){

        admin admin = new admin(); // DTO에 있는 값을 entity로 넘기기 때문에 (DTO, entity)로 작성
        admin.setAdmin_id(adminDTO.getAdmin_id());
        admin.setAdmin_pw(adminDTO.getAdmin_pw());
        admin.setAdmin_name(adminDTO.getAdmin_name());
        admin.setAdmin_phone(adminDTO.getAdmin_phone());
        return admin;
    }

}
