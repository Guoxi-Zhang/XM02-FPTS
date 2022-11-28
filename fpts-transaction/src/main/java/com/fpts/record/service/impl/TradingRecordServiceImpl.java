package com.fpts.record.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.record.mapper.TradingRecordMapper;
import com.fpts.record.domain.TradingRecord;
import com.fpts.record.service.ITradingRecordService;
import com.fpts.common.core.text.Convert;

/**
 * 交易记录Service业务层处理
 * 
 * @author lzy
 * @date 2022-11-27
 */
@Service
public class TradingRecordServiceImpl implements ITradingRecordService 
{
    @Autowired
    private TradingRecordMapper tradingRecordMapper;

    /**
     * 查询交易记录
     * 
     * @param orderId 交易记录主键
     * @return 交易记录
     */
    @Override
    public TradingRecord selectTradingRecordByOrderId(Integer orderId)
    {
        return tradingRecordMapper.selectTradingRecordByOrderId(orderId);
    }

    /**
     * 查询交易记录列表
     * 
     * @param tradingRecord 交易记录
     * @return 交易记录
     */
    @Override
    public List<TradingRecord> selectTradingRecordList(TradingRecord tradingRecord)
    {
        return tradingRecordMapper.selectTradingRecordList(tradingRecord);
    }

    /**
     * 新增交易记录
     * 
     * @param tradingRecord 交易记录
     * @return 结果
     */
    @Override
    public int insertTradingRecord(TradingRecord tradingRecord)
    {
        return tradingRecordMapper.insertTradingRecord(tradingRecord);
    }

    /**
     * 修改交易记录
     * 
     * @param tradingRecord 交易记录
     * @return 结果
     */
    @Override
    public int updateTradingRecord(TradingRecord tradingRecord)
    {
        return tradingRecordMapper.updateTradingRecord(tradingRecord);
    }

    /**
     * 批量删除交易记录
     * 
     * @param orderIds 需要删除的交易记录主键
     * @return 结果
     */
    @Override
    public int deleteTradingRecordByOrderIds(String orderIds)
    {
        return tradingRecordMapper.deleteTradingRecordByOrderIds(Convert.toStrArray(orderIds));
    }

    /**
     * 删除交易记录信息
     * 
     * @param orderId 交易记录主键
     * @return 结果
     */
    @Override
    public int deleteTradingRecordByOrderId(Integer orderId)
    {
        return tradingRecordMapper.deleteTradingRecordByOrderId(orderId);
    }
}
