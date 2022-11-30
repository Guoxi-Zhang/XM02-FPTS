package com.fpts.finance_collection.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 产品收藏对象 finance_collection
 * 
 * @author laybxc
 * @date 2022-11-30
 */
public class FinanceCollection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 产品代码 */
    @Excel(name = "产品代码")
    private String productId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    public FinanceCollection(Long userId,String productId){
        this.userId = userId;
        this.productId = productId;
    }

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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productId", getProductId())
            .append("userId", getUserId())
            .toString();
    }
}
