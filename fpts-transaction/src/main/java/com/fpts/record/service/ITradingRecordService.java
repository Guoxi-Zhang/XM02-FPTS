package com.fpts.record.service;

import java.util.List;
import com.fpts.record.domain.TradingRecord;

/**
 * 交易记录Service接口
 * 
 * @author lzy
 * @date 2022-11-27
 */
public interface ITradingRecordService 
{
    /**
     * 查询交易记录
     * 
     * @param orderId 交易记录主键
     * @return 交易记录
     */
    public TradingRecord selectTradingRecordByOrderId(Integer orderId);

    /**
     * 查询交易记录列表
     * 
     * @param tradingRecord 交易记录
     * @return 交易记录集合
     */
    public List<TradingRecord> selectTradingRecordList(TradingRecord tradingRecord);

    /**
     * 新增交易记录
     * 
     * @param tradingRecord 交易记录
     * @return 结果
     */
    public int insertTradingRecord(TradingRecord tradingRecord);

    /**
     * 修改交易记录
     * 
     * @param tradingRecord 交易记录
     * @return 结果
     */
    public int updateTradingRecord(TradingRecord tradingRecord);

    /**
     * 批量删除交易记录
     * 
     * @param orderIds 需要删除的交易记录主键集合
     * @return 结果
     */
    public int deleteTradingRecordByOrderIds(String orderIds);

    /**
     * 删除交易记录信息
     * 
     * @param orderId 交易记录主键
     * @return 结果
     */
    public int deleteTradingRecordByOrderId(Integer orderId);
}
