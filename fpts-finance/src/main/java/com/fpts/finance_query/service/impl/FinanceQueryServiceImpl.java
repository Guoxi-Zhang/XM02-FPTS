package com.fpts.finance_query.service.impl;

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
 * @date 2022-11-23
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
    public FinanceQuery selectFinanceQueryById(String id)
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
        return financeQueryMapper.selectFinanceQueryList(financeQuery);
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
    public int deleteFinanceQueryById(String id)
    {
        return financeQueryMapper.deleteFinanceQueryById(id);
    }
}
