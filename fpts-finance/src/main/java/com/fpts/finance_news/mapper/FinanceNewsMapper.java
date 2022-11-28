package com.fpts.finance_news.mapper;

import java.util.List;
import com.fpts.finance_news.domain.FinanceNews;

/**
 * 新闻管理Mapper接口
 * 
 * @author Guoxi Zhang
 * @date 2022-11-28
 */
public interface FinanceNewsMapper 
{
    /**
     * 查询新闻管理
     * 
     * @param newsId 新闻管理主键
     * @return 新闻管理
     */
    public FinanceNews selectFinanceNewsByNewsId(Long newsId);

    /**
     * 查询新闻管理列表
     * 
     * @param financeNews 新闻管理
     * @return 新闻管理集合
     */
    public List<FinanceNews> selectFinanceNewsList(FinanceNews financeNews);

    /**
     * 新增新闻管理
     * 
     * @param financeNews 新闻管理
     * @return 结果
     */
    public int insertFinanceNews(FinanceNews financeNews);

    /**
     * 修改新闻管理
     * 
     * @param financeNews 新闻管理
     * @return 结果
     */
    public int updateFinanceNews(FinanceNews financeNews);

    /**
     * 删除新闻管理
     * 
     * @param newsId 新闻管理主键
     * @return 结果
     */
    public int deleteFinanceNewsByNewsId(Long newsId);

    /**
     * 批量删除新闻管理
     * 
     * @param newsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinanceNewsByNewsIds(String[] newsIds);
}
