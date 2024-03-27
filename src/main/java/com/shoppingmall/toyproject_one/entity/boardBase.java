package com.shoppingmall.toyproject_one.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AutoCloseable.class)
@Getter
public class boardBase {

    @CreationTimestamp
    @Column(name = "board_created_time", updatable = false)
    private LocalDateTime boardCreatedTime;

    @CreationTimestamp
    @Column(name = "board_update_time", insertable = false)
    private LocalDateTime boardUpdatedTime;
}
