package com.fpts.finance_query.mapper;

import java.util.List;
import com.fpts.finance_query.domain.FinanceQuery;

/**
 * 行情查询Mapper接口
 * 
 * @author laybxc
 * @date 2022-11-24
 */
public interface FinanceQueryMapper 
{
    /**
     * 查询行情查询
     * 
     * @param id 行情查询主键
     * @return 行情查询
     */
    public FinanceQuery selectFinanceQueryById(String id);

    /**
     * 查询行情查询列表
     * 
     * @param financeQuery 行情查询
     * @return 行情查询集合
     */
    public List<FinanceQuery> selectFinanceQueryList(FinanceQuery financeQuery);

    /**
     * 新增行情查询
     * 
     * @param financeQuery 行情查询
     * @return 结果
     */
    public int insertFinanceQuery(FinanceQuery financeQuery);

    /**
     * 修改行情查询
     * 
     * @param financeQuery 行情查询
     * @return 结果
     */
    public int updateFinanceQuery(FinanceQuery financeQuery);

    /**
     * 删除行情查询
     * 
     * @param id 行情查询主键
     * @return 结果
     */
    public int deleteFinanceQueryById(String id);

    /**
     * 批量删除行情查询
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinanceQueryByIds(String[] ids);
}
