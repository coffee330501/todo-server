package io.github.coffee330501.service;

import com.mybatisflex.core.service.IService;
import io.github.coffee330501.module.entity.TodoList;
import io.github.coffee330501.module.vo.GetClientSyncDataInput;

import java.util.List;

/**
 * 待办事项 服务层。
 *
 * @author objcfeng
 * @since 2024-02-06
 */
public interface TodoListService extends IService<TodoList> {

    List<TodoList> listPending();

    List<TodoList> listClosed();

    void sync(List<TodoList> todoList);

    List<TodoList> getClientSyncData(GetClientSyncDataInput input);

}
