package com.fpts.record.mapper;

import java.util.List;
import com.fpts.record.domain.TradingRecord;

/**
 * 交易记录Mapper接口
 * 
 * @author lzy
 * @date 2022-12-02
 */
public interface TradingRecordMapper 
{
    /**
     * 查询交易记录
     * 
     * @param orderId 交易记录主键
     * @return 交易记录
     */
    public TradingRecord selectTradingRecordByOrderId(Long orderId);

    /**
     * 查询交易记录列表
     *
     * @param tradingRecord 交易记录
     * @return 交易记录集合
     */
    public List<TradingRecord> selectTradingRecordList(TradingRecord tradingRecord);

    /**
     * 查询A股交易记录列表
     *
     * @param tradingRecord 交易记录
     * @return 交易记录集合
     */
    public List<TradingRecord> selectAStockTradingRecordList(TradingRecord tradingRecord);

    /**
     * 查询B股交易记录列表
     *
     * @param tradingRecord 交易记录
     * @return 交易记录集合
     */
    public List<TradingRecord> selectBStockTradingRecordList(TradingRecord tradingRecord);

    /**
     * 查询债券交易记录列表
     *
     * @param tradingRecord 交易记录
     * @return 交易记录集合
     */
    public List<TradingRecord> selectBondTradingRecordList(TradingRecord tradingRecord);

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
     * 删除交易记录
     * 
     * @param orderId 交易记录主键
     * @return 结果
     */
    public int deleteTradingRecordByOrderId(Long orderId);

    /**
     * 批量删除交易记录
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTradingRecordByOrderIds(String[] orderIds);
}
