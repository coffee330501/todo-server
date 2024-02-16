package io.github.coffee330501.service.impl;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.coffee330501.module.entity.TodoList;
import io.github.coffee330501.dao.mapper.TodoListMapper;
import io.github.coffee330501.module.vo.GetClientSyncDataInput;
import io.github.coffee330501.service.TodoListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static io.github.coffee330501.module.entity.table.TodoListTableDef.TODO_LIST;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sync(List<TodoList> todoList) {
        // 更新
        syncUpdate(todoList);
        // 新增
        syncInsert(todoList);
    }

    @Override
    public List<TodoList> getClientSyncData(GetClientSyncDataInput input) {
        return todoListMapper.selectListByQuery(new QueryWrapper().and(TODO_LIST.UPDATE_TIME.ge(input.getLastSyncTime())).orderBy(TODO_LIST.CREATE_TIME, Boolean.FALSE));
    }

    private void syncUpdate(List<TodoList> todoList) {
        List<String> ids = CollStreamUtil.toList(todoList, TodoList::getId);
        if (CollUtil.isEmpty(ids)) return;

        Map<String, TodoList> map = CollStreamUtil.toIdentityMap(todoList, TodoList::getId);
        List<TodoList> todoListDb = todoListMapper.selectListByIds(ids);
        for (TodoList todoDb : todoListDb) {
            TodoList todo = map.get(todoDb.getId());
            todoListMapper.update(todo);
        }
    }

    private void syncInsert(List<TodoList> todoList) {
        List<String> ids = CollStreamUtil.toList(todoList, TodoList::getId);
        if (CollUtil.isEmpty(ids)) return;

        Map<String, TodoList> map = CollStreamUtil.toIdentityMap(todoList, TodoList::getId);
        List<TodoList> todoListDb = todoListMapper.selectListByIds(ids);
        Map<String, TodoList> dbMap = CollStreamUtil.toIdentityMap(todoListDb, TodoList::getId);

        for (String id : ids) {
            if (dbMap.get(id) != null) continue;
            todoListMapper.insertSelective(map.get(id));
        }
    }
}
