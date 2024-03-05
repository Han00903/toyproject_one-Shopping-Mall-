package com.shoppingmall.toyproject_one.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "order")
public class order {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY) // DB에 의해 자동으로 증가하는 PK값을 사용하도록 지정
    private String order_id;

//    @JoinColumn(name = "user_id") // 외래키(FK)를 매핑하는데 사용하는 어노테이션
//    @ManyToOne(fetch = FetchType.LAZY) // 다대일(N:1)관계를 나타내는 어노테이션, FetchType.LAZY 옵션은 연관된 엔터티를 필요할때까지 로딩하지 않음을 나타냄
//    private user user ;

    @ Column(nullable = false)
    private LocalDateTime order_date;

    @ Column(nullable = false)
    private String order_status;

//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<orderItem> orderItems = new ArrayList<>();

}
