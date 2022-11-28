package com.fpts.finance_news.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 新闻管理对象 finance_news
 * 
 * @author Guoxi Zhang
 * @date 2022-11-28
 */
public class FinanceNews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 新闻id */
    private Long newsId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseTime;

    /** 链接 */
    @Excel(name = "链接")
    private String link;

    /** 来源 */
    private String source;

    /** 内容 */
    @Excel(name = "内容")
    private String body;

    /** 阅读数 */
    @Excel(name = "阅读数")
    private Long read;

    /** 评论数 */
    @Excel(name = "评论数")
    private Long comment;

    public void setNewsId(Long newsId) 
    {
        this.newsId = newsId;
    }

    public Long getNewsId() 
    {
        return newsId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setReleaseTime(Date releaseTime) 
    {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseTime() 
    {
        return releaseTime;
    }
    public void setLink(String link) 
    {
        this.link = link;
    }

    public String getLink() 
    {
        return link;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setBody(String body) 
    {
        this.body = body;
    }

    public String getBody() 
    {
        return body;
    }
    public void setRead(Long read) 
    {
        this.read = read;
    }

    public Long getRead() 
    {
        return read;
    }
    public void setComment(Long comment) 
    {
        this.comment = comment;
    }

    public Long getComment() 
    {
        return comment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("newsId", getNewsId())
            .append("title", getTitle())
            .append("author", getAuthor())
            .append("releaseTime", getReleaseTime())
            .append("link", getLink())
            .append("source", getSource())
            .append("body", getBody())
            .append("read", getRead())
            .append("comment", getComment())
            .toString();
    }
}
