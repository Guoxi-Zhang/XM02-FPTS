package com.fpts.assets.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.fpts.common.annotation.Log;
import com.fpts.common.enums.BusinessType;
import com.fpts.assets.domain.AccountAssets;
import com.fpts.assets.service.IAccountAssetsService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 查看资产Controller
 * 
 * @author lzy
 * @date 2022-12-10
 */
@Controller
@RequestMapping("/assets/account_assets")
public class AccountAssetsController extends BaseController
{
    private String prefix = "assets/account_assets";

    @Autowired
    private IAccountAssetsService accountAssetsService;

    @RequiresPermissions("assets:account_assets:view")
    @GetMapping()
    public String account_assets()
    {
        return prefix + "/account_assets";
    }

    /**
     * 查询查看资产列表
     */
    @RequiresPermissions("assets:account_assets:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccountAssets accountAssets)
    {
        startPage();
        List<AccountAssets> list = accountAssetsService.selectAccountAssetsList(accountAssets);
        return getDataTable(list);
    }

    /**
     * 导出查看资产列表
     */
    @RequiresPermissions("assets:account_assets:export")
    @Log(title = "查看资产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AccountAssets accountAssets)
    {
        List<AccountAssets> list = accountAssetsService.selectAccountAssetsList(accountAssets);
        ExcelUtil<AccountAssets> util = new ExcelUtil<AccountAssets>(AccountAssets.class);
        return util.exportExcel(list, "查看资产数据");
    }

    /**
     * 新增查看资产
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存查看资产
     */
    @RequiresPermissions("assets:account_assets:add")
    @Log(title = "查看资产", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AccountAssets accountAssets)
    {
        return toAjax(accountAssetsService.insertAccountAssets(accountAssets));
    }

    /**
     * 修改查看资产
     */
    @RequiresPermissions("assets:account_assets:edit")
    @GetMapping("/edit/{no}")
    public String edit(@PathVariable("no") Long no, ModelMap mmap)
    {
        AccountAssets accountAssets = accountAssetsService.selectAccountAssetsByNo(no);
        mmap.put("accountAssets", accountAssets);
        return prefix + "/edit";
    }

    /**
     * 修改保存查看资产
     */
    @RequiresPermissions("assets:account_assets:edit")
    @Log(title = "查看资产", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AccountAssets accountAssets)
    {
        return toAjax(accountAssetsService.updateAccountAssets(accountAssets));
    }

    /**
     * 删除查看资产
     */
    @RequiresPermissions("assets:account_assets:remove")
    @Log(title = "查看资产", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(accountAssetsService.deleteAccountAssetsByNos(ids));
    }

    /**
     * 打印跳转
     */
    @RequestMapping("/print")
    public String print(){
        return prefix + "/print";
    }
    /**
     * 打印操作
     */
    @PostMapping("/printToHtml")
    @ResponseBody
    public TableDataInfo printToHtml(AccountAssets accountAssets)
    {
        List<AccountAssets> list = accountAssetsService.selectAccountAssetsList(accountAssets);
        return getDataTable(list);
    }

    /**
     * 输入充值金额
     */
    @GetMapping("/addBalance/{no}")
    public String addBalance(ModelMap mmap, @PathVariable("no")  Long id)
    {
        AccountAssets accountAssets = accountAssetsService.selectAccountAssetsByNo(id);
        System.out.println(accountAssets.toString());
        mmap.put("accountAssets", accountAssets);
        return prefix + "/addBalance";
    }
    /*
     * 输入充值金额保存
     */
    @PostMapping("/addBalance")
    public String setSellAmountSave(@RequestParam("no") String no, @RequestParam("changedBalance") String changedBalance){
        Long n = Long.parseLong(no);
        Long changebalance = Long.parseLong(changedBalance);
        AccountAssets accountAssets = accountAssetsService.selectAccountAssetsByNo(n);
        Long newBalance = accountAssets.getAccountBalance() + changebalance;
        accountAssets.setAccountBalance(newBalance);
        accountAssetsService.updateAccountAssets(accountAssets);
        return "assets/account_assets";
    }
    @PostMapping("/addBalanceSave")
    @ResponseBody
    public AjaxResult toItemCompleteAdd(@RequestParam(value="no")  Long no, @RequestParam(value="changedBalance")  Long changedBalance){
        //System.out.println(id + " " + sellAmount + " " + productPrice+ " " + productType +" ");
        AccountAssets accountAssets =  accountAssetsService.selectAccountAssetsByNo(no);
        accountAssets.setAccountBalance(accountAssets.getAccountBalance() + changedBalance);
        System.out.println(accountAssets.getAccountBalance());
        //AccountAssets accountAssets = accountAssetsService.selectAccountAssetsByNo()
        return toAjax(accountAssetsService.updateAccountAssets(accountAssets));
    }

    /**
     * 输入取出金额
     */
    @GetMapping("/decreaseBalance/{no}")
    public String decreaseBalance(ModelMap mmap, @PathVariable("no")  Long id)
    {
        AccountAssets accountAssets = accountAssetsService.selectAccountAssetsByNo(id);
        System.out.println(accountAssets.toString());
        mmap.put("accountAssets", accountAssets);
        return prefix + "/decreaseBalance";
    }
    /*
     * 输入取出金额保存
     */
    @PostMapping("/decreaseBalance")
    public String decreaseBalanceSave(@RequestParam("no") String no, @RequestParam("changedBalance") String changedBalance){
        Long n = Long.parseLong(no);
        Long changebalance = Long.parseLong(changedBalance);
        AccountAssets accountAssets = accountAssetsService.selectAccountAssetsByNo(n);
        Long newBalance = accountAssets.getAccountBalance() - changebalance;
        accountAssets.setAccountBalance(newBalance);
        accountAssetsService.updateAccountAssets(accountAssets);
        return "assets/account_assets";
    }
    @PostMapping("/decreaseBalanceSave")
    @ResponseBody
    public AjaxResult toItemCompleteDecrease(@RequestParam(value="no")  Long no, @RequestParam(value="changedBalance")  Long changedBalance){
        //System.out.println(id + " " + sellAmount + " " + productPrice+ " " + productType +" ");
        AccountAssets accountAssets =  accountAssetsService.selectAccountAssetsByNo(no);
        accountAssets.setAccountBalance(accountAssets.getAccountBalance() - changedBalance);
        System.out.println(accountAssets.getAccountBalance());
        //AccountAssets accountAssets = accountAssetsService.selectAccountAssetsByNo()
        return toAjax(accountAssetsService.updateAccountAssets(accountAssets));
    }
}
