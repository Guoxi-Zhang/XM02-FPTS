package com.fpts.finance_forum.service;

import java.util.List;
import com.fpts.finance_forum.domain.FinanceForum;

/**
 * 金融论坛Service接口
 * 
 * @author liyicheng
 * @date 2022-12-06
 */
public interface IFinanceForumService 
{
    /**
     * 查询金融论坛
     * 
     * @param id 金融论坛主键
     * @return 金融论坛
     */
    public FinanceForum selectFinanceForumById(Long id);

    /**
     * 查询金融论坛列表
     * 
     * @param financeForum 金融论坛
     * @return 金融论坛集合
     */
    public List<FinanceForum> selectFinanceForumList(FinanceForum financeForum);

    /**
     * 新增金融论坛
     * 
     * @param financeForum 金融论坛
     * @return 结果
     */
    public int insertFinanceForum(FinanceForum financeForum);

    /**
     * 修改金融论坛
     * 
     * @param financeForum 金融论坛
     * @return 结果
     */
    public int updateFinanceForum(FinanceForum financeForum);

    /**
     * 批量删除金融论坛
     * 
     * @param ids 需要删除的金融论坛主键集合
     * @return 结果
     */
    public int deleteFinanceForumByIds(String ids);

    /**
     * 删除金融论坛信息
     * 
     * @param id 金融论坛主键
     * @return 结果
     */
    public int deleteFinanceForumById(Long id);
}
