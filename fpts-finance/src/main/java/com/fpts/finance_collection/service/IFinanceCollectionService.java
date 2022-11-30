package com.fpts.finance_collection.service;

import java.util.List;
import com.fpts.finance_collection.domain.FinanceCollection;

/**
 * 产品收藏Service接口
 * 
 * @author laybxc
 * @date 2022-11-30
 */
public interface IFinanceCollectionService 
{
    /**
     * 查询产品收藏
     * 
     * @param id 产品收藏主键
     * @return 产品收藏
     */
    public FinanceCollection selectFinanceCollectionById(Long id);

    /**
     * 查询产品收藏列表
     * 
     * @param financeCollection 产品收藏
     * @return 产品收藏集合
     */
    public List<FinanceCollection> selectFinanceCollectionList(FinanceCollection financeCollection);

    /**
     * 新增产品收藏
     * 
     * @param financeCollection 产品收藏
     * @return 结果
     */
    public int insertFinanceCollection(FinanceCollection financeCollection);

    /**
     * 修改产品收藏
     * 
     * @param financeCollection 产品收藏
     * @return 结果
     */
    public int updateFinanceCollection(FinanceCollection financeCollection);

    /**
     * 批量删除产品收藏
     * 
     * @param ids 需要删除的产品收藏主键集合
     * @return 结果
     */
    public int deleteFinanceCollectionByIds(String ids);

    /**
     * 删除产品收藏信息
     * 
     * @param id 产品收藏主键
     * @return 结果
     */
    public int deleteFinanceCollectionById(Long id);
}
