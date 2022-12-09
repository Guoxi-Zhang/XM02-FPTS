package com.fpts.cockpitInterface;

import com.fpts.common.core.page.TableDataInfo;
import com.fpts.todo.domain.TodoList;
import com.fpts.todo.service.ITodoListService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 可视化大屏幕Controller
 *
 * @author Guoxi Zhang
 * @date 2022-12-09
 */
@Controller
@RequestMapping("/data")
public class CockpitInterfaceController {
    private String prefix = "data";

    @Autowired
    private ITodoListService todoListService;

    @GetMapping()
    public String cockpitInterface(ModelMap mmap, TodoList todoList)
    {
        /**
         * 查询待办事项列表
         */
        List<TodoList> list = todoListService.selectTodoListList(todoList);
        mmap.put("todoList", list);


        //返回视图
        return prefix + "/cockpitInterface";
    }


    /**
     * 查询待办事项列表
     */
    @PostMapping("/todo_list")
    @ResponseBody
    public void list(ModelMap mmap, TodoList todoList)
    {

    }
}
