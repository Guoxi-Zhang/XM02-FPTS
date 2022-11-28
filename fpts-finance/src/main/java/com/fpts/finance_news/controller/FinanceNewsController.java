package com.fpts.finance_news.controller;

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
import com.fpts.finance_news.domain.FinanceNews;
import com.fpts.finance_news.service.IFinanceNewsService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

/**
 * 新闻管理Controller
 * 
 * @author Guoxi Zhang
 * @date 2022-11-28
 */
@Controller
@RequestMapping("/finance_news/news_manage")
public class FinanceNewsController extends BaseController
{
    private String prefix = "finance_news/news_manage";

    @Autowired
    private IFinanceNewsService financeNewsService;

    @RequiresPermissions("finance_news:news_manage:view")
    @GetMapping()
    public String news_manage()
    {
        return prefix + "/news_manage";
    }

    /**
     * 查询新闻管理列表
     */
    @RequiresPermissions("finance_news:news_manage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinanceNews financeNews)
    {
        startPage();
        List<FinanceNews> list = financeNewsService.selectFinanceNewsList(financeNews);
        return getDataTable(list);
    }

    /**
     * 导出新闻管理列表
     */
    @RequiresPermissions("finance_news:news_manage:export")
    @Log(title = "新闻管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinanceNews financeNews)
    {
        List<FinanceNews> list = financeNewsService.selectFinanceNewsList(financeNews);
        ExcelUtil<FinanceNews> util = new ExcelUtil<FinanceNews>(FinanceNews.class);
        return util.exportExcel(list, "新闻管理数据");
    }

    /**
     * 新增新闻管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存新闻管理
     */
    @RequiresPermissions("finance_news:news_manage:add")
    @Log(title = "新闻管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinanceNews financeNews)
    {
        return toAjax(financeNewsService.insertFinanceNews(financeNews));
    }

    /**
     * 修改新闻管理
     */
    @RequiresPermissions("finance_news:news_manage:edit")
    @GetMapping("/edit/{newsId}")
    public String edit(@PathVariable("newsId") Long newsId, ModelMap mmap)
    {
        FinanceNews financeNews = financeNewsService.selectFinanceNewsByNewsId(newsId);
        mmap.put("financeNews", financeNews);
        return prefix + "/edit";
    }

    /**
     * 修改保存新闻管理
     */
    @RequiresPermissions("finance_news:news_manage:edit")
    @Log(title = "新闻管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinanceNews financeNews)
    {
        return toAjax(financeNewsService.updateFinanceNews(financeNews));
    }

    /**
     * 删除新闻管理
     */
    @RequiresPermissions("finance_news:news_manage:remove")
    @Log(title = "新闻管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(financeNewsService.deleteFinanceNewsByNewsIds(ids));
    }
}
