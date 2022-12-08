package com.fpts.position.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 账户持仓对象 account_position
 * 
 * @author lzy
 * @date 2022-12-08
 */
public class AccountPosition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long no;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 账户ID */
    @Excel(name = "账户ID")
    private String accountId;

    /** 金融产品ID */
    @Excel(name = "金融产品ID")
    private String productId;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String productType;

    /** 产品资产数额 */
    @Excel(name = "产品资产数额")
    private BigDecimal productAssets;

    /** 最近下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近下单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastOrderTime;

    public void setNo(Long no) 
    {
        this.no = no;
    }

    public Long getNo() 
    {
        return no;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAccountId(String accountId) 
    {
        this.accountId = accountId;
    }

    public String getAccountId() 
    {
        return accountId;
    }
    public void setProductId(String productId) 
    {
        this.productId = productId;
    }

    public String getProductId() 
    {
        return productId;
    }
    public void setProductType(String productType) 
    {
        this.productType = productType;
    }

    public String getProductType() 
    {
        return productType;
    }
    public void setProductAssets(BigDecimal productAssets) 
    {
        this.productAssets = productAssets;
    }

    public BigDecimal getProductAssets() 
    {
        return productAssets;
    }
    public void setLastOrderTime(Date lastOrderTime) 
    {
        this.lastOrderTime = lastOrderTime;
    }

    public Date getLastOrderTime() 
    {
        return lastOrderTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("no", getNo())
            .append("userId", getUserId())
            .append("accountId", getAccountId())
            .append("productId", getProductId())
            .append("productType", getProductType())
            .append("productAssets", getProductAssets())
            .append("lastOrderTime", getLastOrderTime())
            .toString();
    }
}
