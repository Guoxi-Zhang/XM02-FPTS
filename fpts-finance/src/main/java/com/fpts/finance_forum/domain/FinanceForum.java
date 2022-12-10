package com.fpts.finance_forum.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 金融论坛对象 finance_forum
 * 
 * @author liyicheng
 * @date 2022-12-06
 */
public class FinanceForum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 论坛帖子ID */
    private Long id;

    /** 产品代码 */
    @Excel(name = "产品代码")
    private String productId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

    /** 发帖人 */
    @Excel(name = "发帖人")
    private Long createUserid;

    /** 帖子内容 */
    @Excel(name = "帖子内容")
    private String content;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductId(String productId) 
    {
        this.productId = productId;
    }

    public String getProductId() 
    {
        return productId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCreateUserid(Long createUserid) 
    {
        this.createUserid = createUserid;
    }

    public Long getCreateUserid() 
    {
        return createUserid;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productId", getProductId())
            .append("name", getName())
            .append("createUserid", getCreateUserid())
            .append("createTime", getCreateTime())
            .append("content", getContent())
            .toString();
    }
}
