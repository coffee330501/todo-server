package io.github.coffee330501.module.entity;

import com.mybatisflex.annotation.Table;
import io.github.coffee330501.module.entity.BaseEntity;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户 实体类。
 *
 * @author objcfeng
 * @since 2024-02-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user")

public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String pwd;

}
