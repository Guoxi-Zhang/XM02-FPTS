package com.fpts.finance_warehouse.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.fpts.finance_news.domain.FinanceNews;
import com.fpts.finance_query.domain.FinanceQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.fpts.common.annotation.Log;
import com.fpts.common.enums.BusinessType;
import com.fpts.finance_warehouse.domain.FinanceWarehouse;
import com.fpts.finance_warehouse.service.IFinanceWarehouseService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

/**
 * 数据仓库Controller
 * 
 * @author laybxc
 * @date 2022-11-25
 */
@Controller
@RequestMapping("/finance_warehouse/finance_warehouse")
public class FinanceWarehouseController extends BaseController
{
    private String prefix = "finance_warehouse/finance_warehouse";

    @Autowired
    private IFinanceWarehouseService financeWarehouseService;

    @RequiresPermissions("finance_warehouse:finance_warehouse:view")
    @GetMapping()
    public String finance_warehouse()
    {
        return prefix + "/finance_warehouse";
    }

    /**
     * 查询数据仓库列表
     */
    @RequiresPermissions("finance_warehouse:finance_warehouse:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinanceWarehouse financeWarehouse)
    {
        startPage();
        List<FinanceWarehouse> list = financeWarehouseService.selectFinanceWarehouseList(financeWarehouse);
        return getDataTable(list);
    }

    /**
     * 导出数据仓库列表
     */
    @RequiresPermissions("finance_warehouse:finance_warehouse:export")
    @Log(title = "数据仓库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinanceWarehouse financeWarehouse)
    {
        List<FinanceWarehouse> list = financeWarehouseService.selectFinanceWarehouseList(financeWarehouse);
        ExcelUtil<FinanceWarehouse> util = new ExcelUtil<FinanceWarehouse>(FinanceWarehouse.class);
        return util.exportExcel(list, "数据仓库数据");
    }

    /**
     * 新增数据仓库
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存数据仓库
     */
    @RequiresPermissions("finance_warehouse:finance_warehouse:add")
    @Log(title = "数据仓库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinanceWarehouse financeWarehouse)
    {
        return toAjax(financeWarehouseService.insertFinanceWarehouse(financeWarehouse));
    }

    /**
     * 修改数据仓库
     */
    @RequiresPermissions("finance_warehouse:finance_warehouse:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        FinanceWarehouse financeWarehouse = financeWarehouseService.selectFinanceWarehouseById(id);
        mmap.put("financeWarehouse", financeWarehouse);
        return prefix + "/edit";
    }

    /**
     * 修改保存数据仓库
     */
    @RequiresPermissions("finance_warehouse:finance_warehouse:edit")
    @Log(title = "数据仓库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinanceWarehouse financeWarehouse)
    {
        return toAjax(financeWarehouseService.updateFinanceWarehouse(financeWarehouse));
    }

    /**
     * 删除数据仓库
     */
    @RequiresPermissions("finance_warehouse:finance_warehouse:remove")
    @Log(title = "数据仓库", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(financeWarehouseService.deleteFinanceWarehouseByIds(ids));
    }

    /**
     * 统计报表
     */
    @RequestMapping("/chart")

    public String showChart(ModelMap mmap){
        List<FinanceWarehouse> list = financeWarehouseService.selectFinanceWarehouseList(new FinanceWarehouse());
        Map<String, Integer> map = new TreeMap<String, Integer>();

        for(FinanceWarehouse f: list){
            String type = f.getType();

            if(map.containsKey(type)){
                int cnt = map.get(type);
                map.replace(type, cnt, cnt + 1);
            }else{
                map.put(type, 1);
            }
        }
        String[] typeSet = map.keySet().toArray(new String[0]);
        List<String> typeList= Arrays.asList(typeSet);
        Integer[] cntSet = map.values().toArray(new Integer[0]);
        List<Integer> cntList=Arrays.asList(cntSet);

        System.out.println(typeList.toString());
        System.out.println(cntList.toString());
        mmap.put("typeList", typeList);
        mmap.put("cntList", cntList);
        return prefix + "/chart";
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
    public TableDataInfo printToHtml(FinanceWarehouse financeWarehouse)
    {
//        startPage();
        List<FinanceWarehouse> list = financeWarehouseService.selectFinanceWarehouseList(financeWarehouse);
        return getDataTable(list);
    }

    /** 小程序列表 */
    @RequestMapping(value = "/wxGet/{Navtab}", method = RequestMethod.POST)
    @ResponseBody
    public List<FinanceWarehouse> get(FinanceWarehouse financeWarehouse, @PathVariable("Navtab") String tab){
        List<FinanceWarehouse> list = financeWarehouseService.selectFinanceWarehouseList(financeWarehouse);
        List<FinanceWarehouse> ansList = new ArrayList<>();
        int cnt = 0;
        for(FinanceWarehouse t: list){
            if (cnt>500) break;
            if(tab.equals("0") && t.getType().equals("0")){//A股
                ansList.add(t);
                cnt++;
            }
            else if(tab.equals("1") && t.getType().equals("1")){//B股
                ansList.add(t);
                cnt++;
            }
            else if(tab.equals("2") && t.getType().equals("2")){//债券
                ansList.add(t);
                cnt++;
            }
            else if(tab.equals("3") && t.getType().equals("3")){//基金
                ansList.add(t);
                cnt++;
            }
        }
        System.out.println(ansList.toString());
        return ansList;
    }

    @RequestMapping(value = "/wxEdit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public FinanceWarehouse wxEdit( @PathVariable("id") String id){
        FinanceWarehouse item = financeWarehouseService.selectFinanceWarehouseById(Integer.valueOf(id));
        return item;
    }
}
