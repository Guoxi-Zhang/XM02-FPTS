package com.fpts.finance_forum.service.impl;

import java.util.List;
import com.fpts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.finance_forum.mapper.FinanceForumMapper;
import com.fpts.finance_forum.domain.FinanceForum;
import com.fpts.finance_forum.service.IFinanceForumService;
import com.fpts.common.core.text.Convert;

/**
 * 金融论坛Service业务层处理
 * 
 * @author liyicheng
 * @date 2022-12-06
 */
@Service
public class FinanceForumServiceImpl implements IFinanceForumService 
{
    @Autowired
    private FinanceForumMapper financeForumMapper;

    /**
     * 查询金融论坛
     * 
     * @param id 金融论坛主键
     * @return 金融论坛
     */
    @Override
    public FinanceForum selectFinanceForumById(Long id)
    {
        return financeForumMapper.selectFinanceForumById(id);
    }

    /**
     * 查询金融论坛列表
     * 
     * @param financeForum 金融论坛
     * @return 金融论坛
     */
    @Override
    public List<FinanceForum> selectFinanceForumList(FinanceForum financeForum)
    {
        return financeForumMapper.selectFinanceForumList(financeForum);
    }

    /**
     * 新增金融论坛
     * 
     * @param financeForum 金融论坛
     * @return 结果
     */
    @Override
    public int insertFinanceForum(FinanceForum financeForum)
    {
        financeForum.setCreateTime(DateUtils.getNowDate());
        return financeForumMapper.insertFinanceForum(financeForum);
    }

    /**
     * 修改金融论坛
     * 
     * @param financeForum 金融论坛
     * @return 结果
     */
    @Override
    public int updateFinanceForum(FinanceForum financeForum)
    {
        return financeForumMapper.updateFinanceForum(financeForum);
    }

    /**
     * 批量删除金融论坛
     * 
     * @param ids 需要删除的金融论坛主键
     * @return 结果
     */
    @Override
    public int deleteFinanceForumByIds(String ids)
    {
        return financeForumMapper.deleteFinanceForumByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除金融论坛信息
     * 
     * @param id 金融论坛主键
     * @return 结果
     */
    @Override
    public int deleteFinanceForumById(Long id)
    {
        return financeForumMapper.deleteFinanceForumById(id);
    }
}
