package com.fpts.record.controller;

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
 * @date 2022-11-27
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
    public String edit(@PathVariable("orderId") Integer orderId, ModelMap mmap)
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
}
