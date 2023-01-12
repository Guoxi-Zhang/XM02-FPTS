package com.fpts.bank_account_management.controller;

import java.text.SimpleDateFormat;
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

    /**
     * 打印页面跳转
     * @return 页面地址
     */
    @GetMapping("print")
    public String print()
    {
        return prefix + "/print";
    }

    /**
     * 打印操作
     */
    @PostMapping("/printToHtml")
    @ResponseBody
    public TableDataInfo printToHtml(AccountInfo accountInfo)
    {
        List<AccountInfo> list = accountInfoService.selectAccountInfoList(accountInfo);

        return getDataTable(list);
    }

    /**
     * 统计图表，统计近三个月以来的银行卡绑定数量
     */
    @RequestMapping("/chart")
    public void showChart(ModelMap mmap)
    {
        // 获取原始数据
        List<AccountInfo> list = accountInfoService.selectAccountInfoList(new AccountInfo());
        // 获取今天的时间
        Date now = new Date();
        String [] nowString = new SimpleDateFormat("yyyy-MM-dd").format(now).toString().split("-");

        // 获取本月第一天的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(calendar.DAY_OF_MONTH, 1); // 1-1
        Date FirstDayOfThisMonth = calendar.getTime();
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(FirstDayOfThisMonth).toString());
        // 获取本月最后一天
        calendar.roll(calendar.DAY_OF_MONTH, -1); // 1-31
        Date LastDayOfThisMonth = calendar.getTime();
        calendar.set(calendar.DAY_OF_MONTH, 1); // 1-1
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(LastDayOfThisMonth).toString());

        // 获取上个月第一天
        calendar.add(calendar.MONTH, -1); // 12-1
        calendar.set(calendar.DAY_OF_MONTH, 1); // 12-1
        Date FirstDayOfLastMonth = calendar.getTime();
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(FirstDayOfLastMonth).toString());
        // 获取上个月最后一天
        calendar.roll(calendar.DAY_OF_MONTH, -1); // 12-31
        Date LastDayOfLastMonth = calendar.getTime();
        calendar.set(calendar.DAY_OF_MONTH, 1); // 12-1
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(LastDayOfLastMonth).toString());

        // 获取上上个月第一天
        calendar.add(calendar.MONTH, -1); // 11-1
        calendar.set(calendar.DAY_OF_MONTH, 1); // 11-1
        Date FirstDayOfLLastMonth = calendar.getTime();
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(FirstDayOfLLastMonth).toString());
        // 获取上上个月最后一天
        calendar.roll(calendar.DAY_OF_MONTH, -1);
        Date LastDayOfLLastMonth = calendar.getTime();
        calendar.set(calendar.DAY_OF_MONTH, 1);
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(LastDayOfLLastMonth).toString());

        int [] countArray = new int[3];
        for (AccountInfo item: list)
        {
            Date time = item.getAccountCreateTime();
            if (time.before(LastDayOfThisMonth) && time.after(FirstDayOfThisMonth) || time.equals(LastDayOfThisMonth) && time.equals(FirstDayOfThisMonth))
            {
                // 时间是否在本月
                countArray[0]++;
            }
            else if (time.before(LastDayOfLastMonth) && time.after(FirstDayOfLastMonth) || time.equals(LastDayOfLastMonth) && time.equals(FirstDayOfLastMonth))
            {
                // 时间是否在上个月
                countArray[1] ++;
            }
            else if (time.before(LastDayOfLLastMonth) && time.after(FirstDayOfLLastMonth) || time.equals(LastDayOfLLastMonth) && time.equals(FirstDayOfLLastMonth))
            {
                // 时间是否在上上个月
                countArray[2] ++;
            }
        }

        // 把月份打包
        String thisMonth = new SimpleDateFormat("yyyy-MM").format(FirstDayOfThisMonth).toString();
        // System.out.println(thisMonth);
        String lastMonth = new SimpleDateFormat("yyyy-MM").format(FirstDayOfLastMonth).toString();
        // System.out.println(lastMonth);
        String llastMonth = new SimpleDateFormat("yyyy-MM").format(FirstDayOfLLastMonth).toString();
        // System.out.println(llastMonth);
        List<String> monthList = new ArrayList<String>();
        monthList.add(thisMonth);
        monthList.add(lastMonth);
        monthList.add(llastMonth);
        mmap.put("monthList", monthList);

        // 把计数结果打包
        List<Integer> countList = new ArrayList<Integer>();
        countList.add(countArray[0]);
        countList.add(countArray[1]);
        countList.add(countArray[2]);
        // System.out.println(countList);
        mmap.put("countList", countList);
    }
}
