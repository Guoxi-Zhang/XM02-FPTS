package com.fpts.finance_query.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.finance_query.mapper.FinanceQueryMapper;
import com.fpts.finance_query.domain.FinanceQuery;
import com.fpts.finance_query.service.IFinanceQueryService;
import com.fpts.common.core.text.Convert;

/**
 * 行情查询Service业务层处理
 * 
 * @author laybxc
 * @date 2022-11-25
 */
@Service
public class FinanceQueryServiceImpl implements IFinanceQueryService 
{
    @Autowired
    private FinanceQueryMapper financeQueryMapper;

    /**
     * 查询行情查询
     * 
     * @param id 行情查询主键
     * @return 行情查询
     */
    @Override
    public FinanceQuery selectFinanceQueryById(Integer id)
    {
        return financeQueryMapper.selectFinanceQueryById(id);
    }

    /**
     * 查询行情查询列表
     * 
     * @param financeQuery 行情查询
     * @return 行情查询
     */
    @Override
    public List<FinanceQuery> selectFinanceQueryList(FinanceQuery financeQuery)
    {
        List<FinanceQuery> list = financeQueryMapper.selectFinanceQueryList(financeQuery);
        DecimalFormat df = new DecimalFormat("#.00");
        for(FinanceQuery f: list){
            if(null!=f.getNewPrice()) f.setNewPrice(Double.parseDouble(df.format(f.getNewPrice())));
            if(null!=f.getOpenPrice()) f.setOpenPrice(Double.parseDouble(df.format(f.getOpenPrice())));
            if(null!=f.getYesterdayPrice()) f.setYesterdayPrice(Double.parseDouble(df.format(f.getYesterdayPrice())));
            if(null!=f.getMaxPrice()) f.setMaxPrice(Double.parseDouble(df.format(f.getMaxPrice())));
            if(null!=f.getMinPrice()) f.setMinPrice(Double.parseDouble(df.format(f.getMinPrice())));
            if(null!=f.getIncrease()) f.setIncrease(Double.parseDouble(df.format(f.getIncrease())));
            if(null!=f.getIncreaseRate()) f.setIncreaseRate(Double.parseDouble(df.format(f.getIncreaseRate())));
            if(null!=f.getTurnoverRate()) f.setTurnoverRate(Double.parseDouble(df.format(f.getTurnoverRate())));
        }
        return list;
    }



    /**
     * 新增行情查询
     * 
     * @param financeQuery 行情查询
     * @return 结果
     */
    @Override
    public int insertFinanceQuery(FinanceQuery financeQuery)
    {
        return financeQueryMapper.insertFinanceQuery(financeQuery);
    }

    /**
     * 修改行情查询
     * 
     * @param financeQuery 行情查询
     * @return 结果
     */
    @Override
    public int updateFinanceQuery(FinanceQuery financeQuery)
    {
        return financeQueryMapper.updateFinanceQuery(financeQuery);
    }

    /**
     * 批量删除行情查询
     * 
     * @param ids 需要删除的行情查询主键
     * @return 结果
     */
    @Override
    public int deleteFinanceQueryByIds(String ids)
    {
        return financeQueryMapper.deleteFinanceQueryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除行情查询信息
     * 
     * @param id 行情查询主键
     * @return 结果
     */
    @Override
    public int deleteFinanceQueryById(Integer id)
    {
        return financeQueryMapper.deleteFinanceQueryById(id);
    }
}
