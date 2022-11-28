package com.fpts.record.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 交易记录对象 trading_record
 * 
 * @author lzy
 * @date 2022-11-27
 */
public class TradingRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Integer orderId;

    /** 账户ID */
    @Excel(name = "账户ID")
    private String accountId;

    /** 金融产品 */
    @Excel(name = "金融产品")
    private String productId;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String productType;

    /** 成交价格 */
    @Excel(name = "成交价格")
    private BigDecimal productPrice;

    /** 购买数量 */
    @Excel(name = "购买数量")
    private Integer pruductAmount;

    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /** 交易方向 */
    @Excel(name = "交易方向")
    private String orderDirection;

    /** 交易方式 */
    @Excel(name = "交易方式")
    private String orderPattern;

    /** 订单完成情况 */
    @Excel(name = "订单完成情况")
    private String orderSituation;

    public void setOrderId(Integer orderId) 
    {
        this.orderId = orderId;
    }

    public Integer getOrderId() 
    {
        return orderId;
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
    public void setProductPrice(BigDecimal productPrice) 
    {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductPrice() 
    {
        return productPrice;
    }
    public void setPruductAmount(Integer pruductAmount) 
    {
        this.pruductAmount = pruductAmount;
    }

    public Integer getPruductAmount() 
    {
        return pruductAmount;
    }
    public void setOrderTime(Date orderTime) 
    {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() 
    {
        return orderTime;
    }
    public void setOrderDirection(String orderDirection) 
    {
        this.orderDirection = orderDirection;
    }

    public String getOrderDirection() 
    {
        return orderDirection;
    }
    public void setOrderPattern(String orderPattern) 
    {
        this.orderPattern = orderPattern;
    }

    public String getOrderPattern() 
    {
        return orderPattern;
    }
    public void setOrderSituation(String orderSituation) 
    {
        this.orderSituation = orderSituation;
    }

    public String getOrderSituation() 
    {
        return orderSituation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("accountId", getAccountId())
            .append("productId", getProductId())
            .append("productType", getProductType())
            .append("productPrice", getProductPrice())
            .append("pruductAmount", getPruductAmount())
            .append("orderTime", getOrderTime())
            .append("orderDirection", getOrderDirection())
            .append("orderPattern", getOrderPattern())
            .append("orderSituation", getOrderSituation())
            .toString();
    }
}
