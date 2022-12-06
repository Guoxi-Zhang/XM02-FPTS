package com.fpts.finance_collection.controller;

import java.util.List;

import com.fpts.finance_warehouse.domain.FinanceWarehouse;
import com.fpts.finance_warehouse.service.IFinanceWarehouseService;
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
import com.fpts.finance_collection.domain.FinanceCollection;
import com.fpts.finance_collection.service.IFinanceCollectionService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

/**
 * 产品收藏Controller
 * 
 * @author laybxc
 * @date 2022-11-30
 */
@Controller
@RequestMapping("/finance_collection/collection")
public class FinanceCollectionController extends BaseController
{
    private String prefix = "finance_collection/collection";

    @Autowired
    private IFinanceCollectionService financeCollectionService;

    @Autowired
    private IFinanceWarehouseService financeWarehouseServiceImpl;
    @RequiresPermissions("finance_collection:collection:view")
    @GetMapping()
    public String collection()
    {
        return prefix + "/collection";
    }

    /**
     * 查询产品收藏列表
     */
    @RequiresPermissions("finance_collection:collection:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinanceWarehouse financeCollection)
    {
        startPage();
        List<FinanceWarehouse> list = financeWarehouseServiceImpl.selectFinanceWarehouseListTocoll(financeCollection);
        return getDataTable(list);
    }

    /**
     * 导出产品收藏列表
     */
    @RequiresPermissions("finance_collection:collection:export")
    @Log(title = "产品收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinanceCollection financeCollection)
    {
        List<FinanceCollection> list = financeCollectionService.selectFinanceCollectionList(financeCollection);
        ExcelUtil<FinanceCollection> util = new ExcelUtil<FinanceCollection>(FinanceCollection.class);
        return util.exportExcel(list, "产品收藏数据");
    }

    /**
     * 新增产品收藏
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品收藏
     */
    @RequiresPermissions("finance_collection:collection:add")
    @Log(title = "产品收藏", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinanceCollection financeCollection)
    {
        return toAjax(financeCollectionService.insertFinanceCollection(financeCollection));
    }

    /**
     * 修改产品收藏
     */
    @RequiresPermissions("finance_collection:collection:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FinanceCollection financeCollection = financeCollectionService.selectFinanceCollectionById(id);
        mmap.put("financeCollection", financeCollection);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品收藏
     */
    @RequiresPermissions("finance_collection:collection:edit")
    @Log(title = "产品收藏", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinanceCollection financeCollection)
    {
        return toAjax(financeCollectionService.updateFinanceCollection(financeCollection));
    }

    /**
     * 删除产品收藏
     */
    @RequiresPermissions("finance_collection:collection:remove")
    @Log(title = "产品收藏", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(financeCollectionService.deleteFinanceCollectionByIds(ids));
    }
}
