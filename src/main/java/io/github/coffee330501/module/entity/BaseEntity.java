package io.github.coffee330501.module.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    @Id
    private String id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Column(isLogicDelete = true)
    private LocalDateTime deleteTime;

    @Column(version = true)
    private Long version;
}
