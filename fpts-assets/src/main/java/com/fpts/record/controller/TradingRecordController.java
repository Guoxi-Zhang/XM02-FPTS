package com.fpts.record.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.fpts.common.annotation.Log;
import com.fpts.common.enums.BusinessType;
import com.fpts.record.domain.TradingRecord;
import com.fpts.record.service.ITradingRecordService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

/**
 * 交易记录Controller
 * 
 * @author lzy
 * @date 2022-12-02
 */
@Controller
@RequestMapping("/record/transaction_record")
public class TradingRecordController extends BaseController
{
    private String prefix = "record/transaction_record";

    @Autowired
    private ITradingRecordService tradingRecordService;

    @RequiresPermissions("record:transaction_record:view")
    @GetMapping()
    public String transaction_record()
    {
        return prefix + "/transaction_record";
    }

    /**
     * 查询交易记录列表
     */
    @RequiresPermissions("record:transaction_record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TradingRecord tradingRecord)
    {
        startPage();
        List<TradingRecord> list = tradingRecordService.selectTradingRecordList(tradingRecord);
        return getDataTable(list);
    }

    /**
     * 查询A股票交易记录列表
     */
    @RequiresPermissions("record:transaction_record:list")
    @PostMapping("/AStocklist")
    @ResponseBody
    public TableDataInfo AStocklist(TradingRecord tradingRecord)
    {
        startPage();
        List<TradingRecord> list = tradingRecordService.selectAStockTradingRecordList(tradingRecord);
        return getDataTable(list);
    }

    /**
     * 查询股票交易记录列表
     */
    @RequiresPermissions("record:transaction_record:list")
    @PostMapping("/BStocklist")
    @ResponseBody
    public TableDataInfo BSTocklist(TradingRecord tradingRecord)
    {
        startPage();
        List<TradingRecord> list = tradingRecordService.selectBStockTradingRecordList(tradingRecord);
        return getDataTable(list);
    }

    /**
     * 查询债券交易记录列表
     */
    @RequiresPermissions("record:transaction_record:list")
    @PostMapping("/Bondlist")
    @ResponseBody
    public TableDataInfo BondTocklist(TradingRecord tradingRecord)
    {
        startPage();
        List<TradingRecord> list = tradingRecordService.selectBondTradingRecordList(tradingRecord);
        return getDataTable(list);
    }

    /**
     * 导出交易记录列表
     */
    @RequiresPermissions("record:transaction_record:export")
    @Log(title = "交易记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TradingRecord tradingRecord)
    {
        List<TradingRecord> list = tradingRecordService.selectTradingRecordList(tradingRecord);
        ExcelUtil<TradingRecord> util = new ExcelUtil<TradingRecord>(TradingRecord.class);
        return util.exportExcel(list, "交易记录数据");
    }

    /**
     * 新增交易记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存交易记录
     */
    @RequiresPermissions("record:transaction_record:add")
    @Log(title = "交易记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TradingRecord tradingRecord)
    {
        return toAjax(tradingRecordService.insertTradingRecord(tradingRecord));
    }

    /**
     * 修改交易记录
     */
    @RequiresPermissions("record:transaction_record:edit")
    @GetMapping("/edit/{orderId}")
    public String edit(@PathVariable("orderId") Long orderId, ModelMap mmap)
    {
        TradingRecord tradingRecord = tradingRecordService.selectTradingRecordByOrderId(orderId);
        mmap.put("tradingRecord", tradingRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存交易记录
     */
    @RequiresPermissions("record:transaction_record:edit")
    @Log(title = "交易记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TradingRecord tradingRecord)
    {
        return toAjax(tradingRecordService.updateTradingRecord(tradingRecord));
    }

    /**
     * 删除交易记录
     */
    @RequiresPermissions("record:transaction_record:remove")
    @Log(title = "交易记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tradingRecordService.deleteTradingRecordByOrderIds(ids));
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
    public TableDataInfo printToHtml(TradingRecord tradingRecord)
    {
        List<TradingRecord> list = tradingRecordService.selectTradingRecordList(tradingRecord);
        return getDataTable(list);
    }

    @PostMapping("/cancel/{orderId}")
    @ResponseBody
    public AjaxResult toItemComplete(@PathVariable("orderId") Long orderId){
        TradingRecord tradingRecord = tradingRecordService.selectTradingRecordByOrderId(orderId);
        tradingRecord.setOrderDirection("1");
        return toAjax(tradingRecordService.insertTradingRecord(tradingRecord));
    }
    /**
     * 统计
     */
    @GetMapping("/eCharts")
    public String statistics()
    {
        return prefix + "/eCharts";
    }

    @PostMapping("/eCharts")
    @ResponseBody
    public List<Integer> statisticsData()
    {
        return tradingRecordService.getMonthlyData();
    }

    @PostMapping("/searchTradingRecord")
    @ResponseBody
    public List<TradingRecord> searchTradingRecord(@RequestParam String userId, @RequestParam String productType)
    {
        TradingRecord tradingRecord = new TradingRecord();
        tradingRecord.setUserId(userId);
        tradingRecord.setProductType(productType);
        return tradingRecordService.selectTradingRecordList(tradingRecord);
    }

    @PostMapping("/addTradingRecord")
    @ResponseBody
    public List<TradingRecord> addTradingRecord(@RequestParam String userId, @RequestParam String productType) {
        TradingRecord tradingRecord = new TradingRecord();
        tradingRecord.setUserId(userId);
        tradingRecord.setProductType(productType);
        return tradingRecordService.selectTradingRecordList(tradingRecord);
    }
}
