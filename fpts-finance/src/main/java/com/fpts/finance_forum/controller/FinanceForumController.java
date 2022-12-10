package com.fpts.finance_forum.controller;

import java.util.List;
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
import com.fpts.finance_forum.domain.FinanceForum;
import com.fpts.finance_forum.service.IFinanceForumService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

/**
 * 金融论坛Controller
 * 
 * @author liyicheng
 * @date 2022-12-06
 */
@Controller
@RequestMapping("/finance_forum/homepage")
public class FinanceForumController extends BaseController
{
    private String prefix = "finance_forum/homepage";

    @Autowired
    private IFinanceForumService financeForumService;

    @RequiresPermissions("finance_forum:homepage:view")
    @GetMapping()
    public String homepage()
    {
        return prefix + "/homepage";
    }

    /**
     * 查询金融论坛列表
     */
    @RequiresPermissions("finance_forum:homepage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinanceForum financeForum)
    {
        startPage();
        List<FinanceForum> list = financeForumService.selectFinanceForumList(financeForum);
        return getDataTable(list);
    }

    /**
     * 导出金融论坛列表
     */
    @RequiresPermissions("finance_forum:homepage:export")
    @Log(title = "金融论坛", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinanceForum financeForum)
    {
        List<FinanceForum> list = financeForumService.selectFinanceForumList(financeForum);
        ExcelUtil<FinanceForum> util = new ExcelUtil<FinanceForum>(FinanceForum.class);
        return util.exportExcel(list, "金融论坛数据");
    }

    /**
     * 新增金融论坛
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存金融论坛
     */
    @RequiresPermissions("finance_forum:homepage:add")
    @Log(title = "金融论坛", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinanceForum financeForum)
    {
        return toAjax(financeForumService.insertFinanceForum(financeForum));
    }

    /**
     * 修改金融论坛
     */
    @RequiresPermissions("finance_forum:homepage:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FinanceForum financeForum = financeForumService.selectFinanceForumById(id);
        mmap.put("financeForum", financeForum);
        return prefix + "/edit";
    }

    /**
     * 修改保存金融论坛
     */
    @RequiresPermissions("finance_forum:homepage:edit")
    @Log(title = "金融论坛", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinanceForum financeForum)
    {
        return toAjax(financeForumService.updateFinanceForum(financeForum));
    }

    /**
     * 删除金融论坛
     */
    @RequiresPermissions("finance_forum:homepage:remove")
    @Log(title = "金融论坛", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(financeForumService.deleteFinanceForumByIds(ids));
    }
}
