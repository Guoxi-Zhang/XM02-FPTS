package com.fpts.web.controller.monitor;

import java.text.SimpleDateFormat;
import java.util.*;

import com.fpts.framework.shiro.service.SysPasswordService;
import com.fpts.system.domain.SysNotice;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fpts.common.annotation.Log;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.core.page.TableDataInfo;
import com.fpts.common.enums.BusinessType;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.system.domain.SysLogininfor;
import com.fpts.system.service.ISysLogininforService;

/**
 * 系统访问记录
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController
{
    private String prefix = "monitor/logininfor";

    @Autowired
    private ISysLogininforService logininforService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("monitor:logininfor:view")
    @GetMapping()
    public String logininfor()
    {
        return prefix + "/logininfor";
    }

    @RequiresPermissions("monitor:logininfor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysLogininfor logininfor)
    {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

    /**
     * 统计报表
     */
    @RequestMapping("/chart")
    public String showChart(ModelMap mmap){
        List<SysLogininfor> list = logininforService.selectLogininforList(new SysLogininfor());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
        Map<String, Integer> map = new TreeMap<String, Integer>();
        for(SysLogininfor l: list){
            Date tempDate = l.getLoginTime();
            String date = dateformat.format(tempDate);

            if(map.containsKey(date)){
                int cnt = map.get(date);
                map.replace(date, cnt, cnt + 1);
            }else{
                map.put(date, 1);
            }
        }
        String[] dateSet = map.keySet().toArray(new String[0]);
        List<String> dateList= Arrays.asList(dateSet);
        Integer[] cntSet = map.values().toArray(new Integer[0]);
        List<Integer> cntList=Arrays.asList(cntSet);

        System.out.println(dateList.toString());
        System.out.println(cntList.toString());
        mmap.put("dateList", dateList);
        mmap.put("cntList", cntList);
        return prefix + "/chart";
    }

    /**
     * 打印操作日志
     */
    @PostMapping("/printToHtml")
    @ResponseBody
    public TableDataInfo printToHtml(SysLogininfor logininfor) {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

    /**
     * 打印跳转
     */
    @RequestMapping("/print")
    public String print() {
        return prefix + "/print";
    }

    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:logininfor:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysLogininfor logininfor)
    {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        return util.exportExcel(list, "登录日志");
    }

    @RequiresPermissions("monitor:logininfor:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(logininforService.deleteLogininforByIds(ids));
    }
    
    @RequiresPermissions("monitor:logininfor:remove")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        logininforService.cleanLogininfor();
        return success();
    }

    @RequiresPermissions("monitor:logininfor:unlock")
    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
    @PostMapping("/unlock")
    @ResponseBody
    public AjaxResult unlock(String loginName)
    {
        passwordService.clearLoginRecordCache(loginName);
        return success();
    }
}
