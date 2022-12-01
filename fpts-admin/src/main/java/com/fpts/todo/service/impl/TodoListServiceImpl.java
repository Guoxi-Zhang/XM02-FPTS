package com.fpts.todo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.todo.mapper.TodoListMapper;
import com.fpts.todo.domain.TodoList;
import com.fpts.todo.service.ITodoListService;
import com.fpts.common.core.text.Convert;

/**
 * 待办事项Service业务层处理
 *
 * @author Guoxi Zhang
 * @date 2022-12-01
 */
@Service
public class TodoListServiceImpl implements ITodoListService
{
    @Autowired
    private TodoListMapper todoListMapper;

    /**
     * 查询待办事项
     *
     * @param id 待办事项主键
     * @return 待办事项
     */
    @Override
    public TodoList selectTodoListById(Long id)
    {
        return todoListMapper.selectTodoListById(id);
    }

    /**
     * 查询待办事项列表
     *
     * @param todoList 待办事项
     * @return 待办事项
     */
    @Override
    public List<TodoList> selectTodoListList(TodoList todoList)
    {
        return todoListMapper.selectTodoListList(todoList);
    }

    /**
     * 新增待办事项
     *
     * @param todoList 待办事项
     * @return 结果
     */
    @Override
    public int insertTodoList(TodoList todoList)
    {
        return todoListMapper.insertTodoList(todoList);
    }

    /**
     * 修改待办事项
     *
     * @param todoList 待办事项
     * @return 结果
     */
    @Override
    public int updateTodoList(TodoList todoList)
    {
        return todoListMapper.updateTodoList(todoList);
    }

    /**
     * 批量删除待办事项
     *
     * @param ids 需要删除的待办事项主键
     * @return 结果
     */
    @Override
    public int deleteTodoListByIds(String ids)
    {
        return todoListMapper.deleteTodoListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除待办事项信息
     *
     * @param id 待办事项主键
     * @return 结果
     */
    @Override
    public int deleteTodoListById(Long id)
    {
        return todoListMapper.deleteTodoListById(id);
    }
}
