package com.fpts.todo.service;

import java.util.List;
import com.fpts.todo.domain.TodoList;

/**
 * 待办事项Service接口
 * 
 * @author ruoyi
 * @date 2022-11-15
 */
public interface ITodoListService 
{
    /**
     * 查询待办事项
     * 
     * @param id 待办事项主键
     * @return 待办事项
     */
    public TodoList selectTodoListById(Long id);

    /**
     * 查询待办事项列表
     * 
     * @param todoList 待办事项
     * @return 待办事项集合
     */
    public List<TodoList> selectTodoListList(TodoList todoList);

    /**
     * 新增待办事项
     * 
     * @param todoList 待办事项
     * @return 结果
     */
    public int insertTodoList(TodoList todoList);

    /**
     * 修改待办事项
     * 
     * @param todoList 待办事项
     * @return 结果
     */
    public int updateTodoList(TodoList todoList);

    /**
     * 批量删除待办事项
     * 
     * @param ids 需要删除的待办事项主键集合
     * @return 结果
     */
    public int deleteTodoListByIds(String ids);

    /**
     * 删除待办事项信息
     * 
     * @param id 待办事项主键
     * @return 结果
     */
    public int deleteTodoListById(Long id);
}
