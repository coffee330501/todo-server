package io.github.coffee330501.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.coffee330501.module.entity.TodoList;
import io.github.coffee330501.dao.mapper.TodoListMapper;
import io.github.coffee330501.service.TodoListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 待办事项 服务层实现。
 *
 * @author objcfeng
 * @since 2024-02-06
 */
@Service
public class TodoListServiceImpl extends ServiceImpl<TodoListMapper, TodoList> implements TodoListService {
    @Resource
    TodoListMapper todoListMapper;

    @Override
    public boolean save(TodoList todoList) {
        todoListMapper.insertSelective(todoList);
        return true;
    }

    @Override
    public List<TodoList> listPending() {
        return list(new QueryWrapper().and("status = 'Pending'").orderBy("create_time desc"));
    }

    @Override
    public List<TodoList> listClosed() {
        return list(new QueryWrapper().and("status = 'Closed'").orderBy("create_time desc"));
    }
}
