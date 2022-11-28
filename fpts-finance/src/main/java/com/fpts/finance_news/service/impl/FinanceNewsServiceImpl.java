package com.fpts.finance_news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.finance_news.mapper.FinanceNewsMapper;
import com.fpts.finance_news.domain.FinanceNews;
import com.fpts.finance_news.service.IFinanceNewsService;
import com.fpts.common.core.text.Convert;

/**
 * 新闻管理Service业务层处理
 * 
 * @author Guoxi Zhang
 * @date 2022-11-28
 */
@Service
public class FinanceNewsServiceImpl implements IFinanceNewsService 
{
    @Autowired
    private FinanceNewsMapper financeNewsMapper;

    /**
     * 查询新闻管理
     * 
     * @param newsId 新闻管理主键
     * @return 新闻管理
     */
    @Override
    public FinanceNews selectFinanceNewsByNewsId(Long newsId)
    {
        return financeNewsMapper.selectFinanceNewsByNewsId(newsId);
    }

    /**
     * 查询新闻管理列表
     * 
     * @param financeNews 新闻管理
     * @return 新闻管理
     */
    @Override
    public List<FinanceNews> selectFinanceNewsList(FinanceNews financeNews)
    {
        return financeNewsMapper.selectFinanceNewsList(financeNews);
    }

    /**
     * 新增新闻管理
     * 
     * @param financeNews 新闻管理
     * @return 结果
     */
    @Override
    public int insertFinanceNews(FinanceNews financeNews)
    {
        return financeNewsMapper.insertFinanceNews(financeNews);
    }

    /**
     * 修改新闻管理
     * 
     * @param financeNews 新闻管理
     * @return 结果
     */
    @Override
    public int updateFinanceNews(FinanceNews financeNews)
    {
        return financeNewsMapper.updateFinanceNews(financeNews);
    }

    /**
     * 批量删除新闻管理
     * 
     * @param newsIds 需要删除的新闻管理主键
     * @return 结果
     */
    @Override
    public int deleteFinanceNewsByNewsIds(String newsIds)
    {
        return financeNewsMapper.deleteFinanceNewsByNewsIds(Convert.toStrArray(newsIds));
    }

    /**
     * 删除新闻管理信息
     * 
     * @param newsId 新闻管理主键
     * @return 结果
     */
    @Override
    public int deleteFinanceNewsByNewsId(Long newsId)
    {
        return financeNewsMapper.deleteFinanceNewsByNewsId(newsId);
    }
}
