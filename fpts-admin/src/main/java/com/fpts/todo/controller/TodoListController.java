package com.fpts.todo.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.fpts.common.annotation.Log;
import com.fpts.common.enums.BusinessType;
import com.fpts.todo.domain.TodoList;
import com.fpts.todo.service.ITodoListService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;
import java.io.IOException;
import com.fpts.framework.web.domain.server.Sys;

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

    @RequestMapping(value = "/wxGet/{Navtab}", method = RequestMethod.POST)
    @ResponseBody
    public List<TodoList> get(TodoList todoList, @PathVariable("Navtab") String tab){
        List<TodoList> list = todoListService.selectTodoListList(todoList);
        List<TodoList> ansList = new ArrayList<>();
        for(TodoList t:list){
            String detail = t.getDetail();
            if(detail.contains("<p>")){
                t.setDetail(t.getDetail().replace("<p>", ""));
                System.out.println(detail.replace("<p>", ""));
            }
            if(detail.contains("</p>")){
                t.setDetail(t.getDetail().replace("</p>", ""));
            }
            if(tab.equals("0") && t.getState().equals("0")){//进行中待办事项
                ansList.add(t);
            }else if(tab.equals("1") && t.getState().equals("1")){//已完成待办事项
                ansList.add(t);
            }
        }
        System.out.println(ansList.toString());
        return ansList;
    }



    @RequestMapping(value = "/wxEdit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public TodoList wxEdit( @PathVariable("id") String id){
        TodoList item = todoListService.selectTodoListById(Long.valueOf(id));
        String detail = item.getDetail();
        if(detail.contains("<p>")){
            item.setDetail(item.getDetail().replace("<p>", ""));
            System.out.println(detail.replace("<p>", ""));
        }
        if(detail.contains("</p>")){
            item.setDetail(item.getDetail().replace("</p>", ""));
        }
        return item;
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
    public String showChart(ModelMap mmap){
        List<TodoList> list = todoListService.selectTodoListList( new TodoList());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
        Map<String, Integer> map = new TreeMap<String, Integer>();
        for(TodoList t: list){
            Date tempDate = t.getEndTime();
            String date = dateformat.format(tempDate);

            if(map.containsKey(date)){
                int cnt = map.get(date);
                map.replace(date, cnt, cnt + 1);
            }else{
                map.put(date, 1);
            }
        }
        String[] dateSet = map.keySet().toArray(new String[0]);
        List<String> dateList=Arrays.asList(dateSet);
        Integer[] cntSet = map.values().toArray(new Integer[0]);
        List<Integer> cntList=Arrays.asList(cntSet);

        System.out.println(dateList.toString());
        System.out.println(cntList.toString());
        mmap.put("dateList", dateList);
        mmap.put("cntList", cntList);
        return prefix + "/chart";
    }

    /**
     * 打印跳转
     */
    @RequestMapping("/print")
    public String print(){

        return prefix + "/print";
    }


    @PostMapping("/complete/{id}")
    @ResponseBody
    public AjaxResult toItemComplete(@PathVariable("id") Long id){
        TodoList todoList = todoListService.selectTodoListById(id);
        todoList.setState("1");
        return toAjax(todoListService.updateTodoList(todoList));
    }
}
