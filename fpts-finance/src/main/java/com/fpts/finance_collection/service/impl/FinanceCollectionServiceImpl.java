package com.fpts.finance_collection.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.finance_collection.mapper.FinanceCollectionMapper;
import com.fpts.finance_collection.domain.FinanceCollection;
import com.fpts.finance_collection.service.IFinanceCollectionService;
import com.fpts.common.core.text.Convert;

/**
 * 产品收藏Service业务层处理
 * 
 * @author laybxc
 * @date 2022-11-30
 */
@Service
public class FinanceCollectionServiceImpl implements IFinanceCollectionService 
{
    @Autowired
    private FinanceCollectionMapper financeCollectionMapper;

    /**
     * 查询产品收藏
     * 
     * @param id 产品收藏主键
     * @return 产品收藏
     */
    @Override
    public FinanceCollection selectFinanceCollectionById(Long id)
    {
        return financeCollectionMapper.selectFinanceCollectionById(id);
    }

    /**
     * 查询产品收藏列表
     * 
     * @param financeCollection 产品收藏
     * @return 产品收藏
     */
    @Override
    public List<FinanceCollection> selectFinanceCollectionList(FinanceCollection financeCollection)
    {
        return financeCollectionMapper.selectFinanceCollectionList(financeCollection);
    }

    /**
     * 新增产品收藏
     * 
     * @param financeCollection 产品收藏
     * @return 结果
     */
    @Override
    public int insertFinanceCollection(FinanceCollection financeCollection)
    {
        return financeCollectionMapper.insertFinanceCollection(financeCollection);
    }

    /**
     * 修改产品收藏
     * 
     * @param financeCollection 产品收藏
     * @return 结果
     */
    @Override
    public int updateFinanceCollection(FinanceCollection financeCollection)
    {
        return financeCollectionMapper.updateFinanceCollection(financeCollection);
    }

    /**
     * 批量删除产品收藏
     * 
     * @param ids 需要删除的产品收藏主键
     * @return 结果
     */
    @Override
    public int deleteFinanceCollectionByIds(String ids)
    {
        return financeCollectionMapper.deleteFinanceCollectionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品收藏信息
     * 
     * @param id 产品收藏主键
     * @return 结果
     */
    @Override
    public int deleteFinanceCollectionById(Long id)
    {
        return financeCollectionMapper.deleteFinanceCollectionById(id);
    }
}
