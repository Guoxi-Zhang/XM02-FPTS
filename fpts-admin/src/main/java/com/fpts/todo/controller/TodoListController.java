package com.fpts.todo.controller;

import java.util.List;

import com.fpts.framework.web.domain.server.Sys;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fpts.common.annotation.Log;
import com.fpts.common.enums.BusinessType;
import com.fpts.todo.domain.TodoList;
import com.fpts.todo.service.ITodoListService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

/**
 * 待办事项Controller
 * 
 * @author Guoxi Zhang
 * @date 2022-11-15
 */
@Controller
@RequestMapping("/todo/list")
public class TodoListController extends BaseController
{
    private String prefix = "todo/list";

    @Autowired
    private ITodoListService todoListService;

    @RequiresPermissions("todo:list:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/list";
    }

    /**
     * 查询待办事项列表
     */
    @RequiresPermissions("todo:list:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TodoList todoList)
    {
        startPage();
        List<TodoList> list = todoListService.selectTodoListList(todoList);
        return getDataTable(list);
    }

    /**
     * 导出待办事项列表
     */
    @RequiresPermissions("todo:list:export")
    @Log(title = "待办事项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TodoList todoList)
    {
        List<TodoList> list = todoListService.selectTodoListList(todoList);
        ExcelUtil<TodoList> util = new ExcelUtil<TodoList>(TodoList.class);
        return util.exportExcel(list, "待办事项数据");
    }

    /**
     * 新增待办事项
     */
    @GetMapping("/add")
    public String add()
    {
        System.out.println(prefix + "/add");
        return prefix + "/add";
    }

    /**
     * 新增保存待办事项
     */
    @RequiresPermissions("todo:list:add")
    @Log(title = "待办事项", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TodoList todoList)
    {
        return toAjax(todoListService.insertTodoList(todoList));
    }

    /**
     * 修改待办事项
     */
    @RequiresPermissions("todo:list:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TodoList todoList = todoListService.selectTodoListById(id);
        mmap.put("todoList", todoList);
        return prefix + "/edit";
    }

    /**
     * 修改保存待办事项
     */
    @RequiresPermissions("todo:list:edit")
    @Log(title = "待办事项", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TodoList todoList)
    {
        return toAjax(todoListService.updateTodoList(todoList));
    }

    /**
     * 删除待办事项
     */
    @RequiresPermissions("todo:list:remove")
    @Log(title = "待办事项", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(todoListService.deleteTodoListByIds(ids));
    }

    /**
     * 统计报表
     */
//    @PostMapping( "/chart")
    @RequestMapping("/chart")
    public String showChart(){
        return prefix + "/chart";
    }
}
