package io.github.coffee330501.module.entity;

import com.mybatisflex.annotation.Table;
import io.github.coffee330501.module.entity.BaseEntity;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 待办事项 实体类。
 *
 * @author objcfeng
 * @since 2024-02-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "todo_list")

public class TodoList extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String content;

    private String status;

    private String tag;

}
