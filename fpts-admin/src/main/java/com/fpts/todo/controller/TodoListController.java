package com.fpts.todo.controller;

import java.io.IOException;
import java.util.*;

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
 * @date 2022-12-01
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
    public String showChart(ModelMap mmap, TodoList todoList){
        List<TodoList> list = todoListService.selectTodoListList(todoList);
        Map<Date, Integer> map = new HashMap<Date, Integer>();
        for(TodoList t: list){
            Date tempDate = t.getEndTime();
            if(map.containsKey(t.getEndTime())){
                int cnt = map.get(tempDate);
                map.replace(tempDate, cnt, cnt++);
            }else{
                map.put(t.getEndTime(), 1);
            }
        }
        Object[] dateSetObj = map.keySet().toArray();
        String[] dateSet = Arrays.copyOf(dateSetObj, dateSetObj.length, String[].class);
        Object[] cntSetObj = map.values().toArray();
        Integer[] cntSet = Arrays.copyOf(cntSetObj, cntSetObj.length, Integer[].class);

        mmap.put("dateSet", dateSet);
        mmap.put("cntSet", cntSet);
        return prefix + "/chart";
    }

    @PostMapping("/complete/{id}")
    @ResponseBody
    public AjaxResult toItemComplete(@PathVariable("id") Long id){
        TodoList todoList = todoListService.selectTodoListById(id);
        todoList.setState("1");
        return toAjax(todoListService.updateTodoList(todoList));
    }
}
