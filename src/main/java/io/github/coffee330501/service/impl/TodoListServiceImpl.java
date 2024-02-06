package io.github.coffee330501.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.coffee330501.module.entity.TodoList;
import io.github.coffee330501.dao.mapper.TodoListMapper;
import io.github.coffee330501.service.TodoListService;
import org.springframework.stereotype.Service;

/**
 * 待办事项 服务层实现。
 *
 * @author objcfeng
 * @since 2024-02-06
 */
@Service
public class TodoListServiceImpl extends ServiceImpl<TodoListMapper, TodoList> implements TodoListService {

}
