package com.fpts.cockpitInterface;

import com.fpts.common.core.page.TableDataInfo;
import com.fpts.todo.domain.TodoList;
import com.fpts.todo.service.ITodoListService;
import com.fpts.finance_forum.domain.FinanceForum;
import com.fpts.finance_forum.service.IFinanceForumService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 可视化大屏幕Controller
 *
 * @author Guoxi Zhang & Yicheng Li
 * @date 2022-12-09
 */
@Controller
@RequestMapping("/data")
public class CockpitInterfaceController {
    private String prefix = "data";

    @Autowired
    private ITodoListService todoListService;
    @Autowired
    private IFinanceForumService financeForumService;

    @GetMapping()
    public String cockpitInterface(ModelMap mmap, TodoList todoList, FinanceForum financeForum)
    {
        /**
         * 查询待办事项列表和金融论坛
         */
        List<TodoList> list = todoListService.selectTodoListList(todoList);
        mmap.put("todoList", list);
        List<FinanceForum> list1 = financeForumService.selectFinanceForumList(financeForum);
        mmap.put("financeForum", list1);


        //返回视图
        return prefix + "/cockpitInterface";
    }
    //@PostMapping(value="/todo_list", "finance_forum")
    @ResponseBody
    public void list(ModelMap mmap, TodoList todoList, FinanceForum financeForum)
    {

    }
}
