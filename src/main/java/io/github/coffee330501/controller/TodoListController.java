package io.github.coffee330501.controller;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.coffee330501.enums.TodoStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.coffee330501.module.entity.TodoList;
import io.github.coffee330501.service.TodoListService;
import org.springframework.web.bind.annotation.RestController;
import java.io.Serializable;
import java.util.List;

/**
 * 待办事项 控制层。
 *
 * @author objcfeng
 * @since 2024-02-06
 */
@RestController
@RequestMapping("/todoList")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    /**
     * 添加待办事项。
     *
     * @param todoList 待办事项
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TodoList todoList) {
        todoList.setStatus(TodoStatus.Pending.name());
        return todoListService.save(todoList);
    }

    /**
     * 根据主键删除待办事项。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return todoListService.removeById(id);
    }

    /**
     * 根据主键更新待办事项。
     *
     * @param todoList 待办事项
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TodoList todoList) {
            return todoListService.updateById(todoList);
    }

    /**
     * 查询所有待办事项。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TodoList> list() {
        return todoListService.list(new QueryWrapper().orderBy("create_time desc"));
    }

    @GetMapping("list/pending")
    public List<TodoList> listPending() {
        return todoListService.listPending();
    }

    @GetMapping("list/closed")
    public List<TodoList> listClosed() {
        return todoListService.listClosed();
    }

    /**
     * 根据待办事项主键获取详细信息。
     *
     * @param id 待办事项主键
     * @return 待办事项详情
     */
    @GetMapping("getInfo/{id}")
    public TodoList getInfo(@PathVariable Serializable id) {
        return todoListService.getById(id);
    }

    /**
     * 分页查询待办事项。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TodoList> page(Page<TodoList> page) {
        return todoListService.page(page);
    }

}
