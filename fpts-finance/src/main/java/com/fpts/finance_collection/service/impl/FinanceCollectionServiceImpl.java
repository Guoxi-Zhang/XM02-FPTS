package com.fpts.finance_collection.service.impl;

import java.util.List;

import com.fpts.common.annotation.DataScope;
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
    @DataScope(deptAlias = "fc", userAlias = "fc")
    public FinanceCollection selectFinanceCollectionById(Long id)
    {
        return financeCollectionMapper.selectFinanceCollectionById(id);
    }

    /**
     * 查询产品收藏列表_原
     * 
     * @param financeCollection 产品收藏
     * @return 产品收藏
     * DataScope 的注解 要与 xml中SQL的 名称或简写 一致！
     */
    @Override
    @DataScope(deptAlias = "fc", userAlias = "fc")
    public List<FinanceCollection> selectFinanceCollectionList(FinanceCollection financeCollection)
    {
        return financeCollectionMapper.selectFinanceCollectionList(financeCollection);
    }

    /**
     * 查询产品收藏列表_改
     *
     * @param financeCollection 产品收藏
     * @return 产品收藏
     */
    @Override
    @DataScope(deptAlias = "fc", userAlias = "fc")
    public List<FinanceCollection> selectFinanceCollectionListWithColl(FinanceCollection financeCollection)
    {
        List<FinanceCollection> list = financeCollectionMapper.selectFinanceCollectionListWithColl(financeCollection);
        return list;
    }


    /**
     * 新增产品收藏
     * 
     * @param financeCollection 产品收藏
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "fc", userAlias = "fc")
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
    @DataScope(deptAlias = "fc", userAlias = "fc")
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
    @DataScope(deptAlias = "fc", userAlias = "fc")
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
    @DataScope(deptAlias = "fc", userAlias = "fc")
    public int deleteFinanceCollectionById(Long id)
    {
        return financeCollectionMapper.deleteFinanceCollectionById(id);
    }
}
