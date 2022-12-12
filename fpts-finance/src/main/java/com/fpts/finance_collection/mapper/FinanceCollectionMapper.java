package com.fpts.finance_collection.mapper;

import java.util.List;
import com.fpts.finance_collection.domain.FinanceCollection;

/**
 * 产品收藏Mapper接口
 * 
 * @author laybxc
 * @date 2022-11-30
 */
public interface FinanceCollectionMapper 
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
    public List<FinanceCollection> selectFinanceCollectionListWithColl(FinanceCollection financeCollection);

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
     * 删除产品收藏
     * 
     * @param id 产品收藏主键
     * @return 结果
     */
    public int deleteFinanceCollectionById(Long id);

    /**
     * 批量删除产品收藏
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinanceCollectionByIds(String[] ids);

}
