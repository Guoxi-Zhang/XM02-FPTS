package com.fpts.bank_account_management.controller;

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
import com.fpts.bank_account_management.domain.AccountInfo;
import com.fpts.bank_account_management.service.IAccountInfoService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

/**
 * 银行账户管理Controller
 * 
 * @author ruoyi
 * @date 2022-12-08
 */
@Controller
@RequestMapping("/bank_account_management/manage")
public class AccountInfoController extends BaseController
{
    private String prefix = "bank_account_management/manage";

    @Autowired
    private IAccountInfoService accountInfoService;

    @RequiresPermissions("bank_account_management:manage:view")
    @GetMapping()
    public String manage()
    {
        return prefix + "/manage";
    }

    /**
     * 查询银行账户管理列表
     */
    @RequiresPermissions("bank_account_management:manage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccountInfo accountInfo)
    {
        startPage();
        List<AccountInfo> list = accountInfoService.selectAccountInfoList(accountInfo);
        return getDataTable(list);
    }

    /**
     * 导出银行账户管理列表
     */
    @RequiresPermissions("bank_account_management:manage:export")
    @Log(title = "银行账户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AccountInfo accountInfo)
    {
        List<AccountInfo> list = accountInfoService.selectAccountInfoList(accountInfo);
        ExcelUtil<AccountInfo> util = new ExcelUtil<AccountInfo>(AccountInfo.class);
        return util.exportExcel(list, "银行账户管理数据");
    }

    /**
     * 新增银行账户管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存银行账户管理
     */
    @RequiresPermissions("bank_account_management:manage:add")
    @Log(title = "银行账户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AccountInfo accountInfo)
    {
        return toAjax(accountInfoService.insertAccountInfo(accountInfo));
    }

    /**
     * 修改银行账户管理
     */
    @RequiresPermissions("bank_account_management:manage:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AccountInfo accountInfo = accountInfoService.selectAccountInfoById(id);
        mmap.put("accountInfo", accountInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存银行账户管理
     */
    @RequiresPermissions("bank_account_management:manage:edit")
    @Log(title = "银行账户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AccountInfo accountInfo)
    {
        return toAjax(accountInfoService.updateAccountInfo(accountInfo));
    }

    /**
     * 删除银行账户管理
     */
    @RequiresPermissions("bank_account_management:manage:remove")
    @Log(title = "银行账户管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(accountInfoService.deleteAccountInfoByIds(ids));
    }
}
