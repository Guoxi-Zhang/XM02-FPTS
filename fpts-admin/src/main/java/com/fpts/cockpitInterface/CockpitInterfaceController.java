package com.fpts.cockpitInterface;

import com.fpts.common.core.page.TableDataInfo;
import com.fpts.system.domain.SysNotice;
import com.fpts.system.service.ISysNoticeService;
import com.fpts.todo.domain.TodoList;
import com.fpts.todo.service.ITodoListService;
import com.fpts.finance_forum.domain.FinanceForum;
import com.fpts.finance_forum.service.IFinanceForumService;
import com.fpts.system.domain.SysUserOnline;
import com.fpts.system.service.ISysUserOnlineService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * 可视化大屏幕Controller
 *
 * @author Guoxi Zhang & Yicheng Li & Chenglong Yu
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
    @Autowired
    private  ISysUserOnlineService sysUserOnlineService;
    @Autowired
    private ISysNoticeService noticeService;
    @GetMapping()
    public String cockpitInterface(ModelMap mmap, TodoList todoList, FinanceForum financeForum, SysUserOnline sysUserOnline, SysNotice notice)
    {
        /**
         * 查询待办事项列表和金融论坛
         */
        List<TodoList> list = todoListService.selectTodoListList(todoList);
        mmap.put("todoList", list);
        List<FinanceForum> list1 = financeForumService.selectFinanceForumList(financeForum);
        mmap.put("financeForum", list1);
        List<SysUserOnline> list2 = sysUserOnlineService.selectUserOnlineList(sysUserOnline);
        mmap.put("sysUserOnline", list2);
        List<SysNotice> list3 = noticeService.selectNoticeList(notice);
        mmap.put("notice", list3);

        //返回视图
        return prefix + "/cockpitInterface";
    }

}
