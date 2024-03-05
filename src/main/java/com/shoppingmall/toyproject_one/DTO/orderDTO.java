package com.shoppingmall.toyproject_one.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자를 자동으로 만들어줌
@AllArgsConstructor // 필드를 모두 매개변수로하는 생성자를 만들어줌
public class orderDTO {
    @NotNull
    private String order_id;

    @NotNull
    private com.shoppingmall.toyproject_one.entity.user user ;

    @NotNull
    private LocalDateTime order_date;

    @NotNull
    private String order_status;

}
