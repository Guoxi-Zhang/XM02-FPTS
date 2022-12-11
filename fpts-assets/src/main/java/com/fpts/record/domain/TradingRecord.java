package com.fpts.record.domain;

import java.util.List;
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
 * @date 2022-12-02
 */
public class TradingRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Long orderId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 账户ID */
    @Excel(name = "账户ID")
    private String accountId;

    /** 金融产品ID */
    @Excel(name = "金融产品ID")
    private String productId;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String productType;

    /** 产品成交价格 */
    @Excel(name = "产品成交价格")
    private Double productPrice;

    /** 产品成交数量 */
    @Excel(name = "产品成交数量")
    private Integer productAmount;

    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /** 交易方向(0买入，1卖出) */
    @Excel(name = "交易方向(0买入，1卖出)")
    private String orderDirection;

    /** 交易方式（0实时下单，1挂单） */
    @Excel(name = "交易方式", readConverterExp = "0=实时下单，1挂单")
    private String orderPattern;

    /** 订单完成情况（0未完成，1完成） */
    @Excel(name = "订单完成情况", readConverterExp = "0=未完成，1完成")
    private String orderSituation;

    public TradingRecord(){

    }

    public TradingRecord(String userId, String accountId, Integer productAmount, String orderPattern, Date datetime, Double productPrice, String productId, String orderDirection, String productType) {
        setAccountId(accountId);
        setOrderDirection(orderDirection);
        setOrderId(orderId);
        setUserId(userId);
        setOrderSituation(orderSituation);
        setOrderPattern(orderPattern);
        setOrderTime(datetime);
        setProductPrice(productPrice);
        setProductAmount(productAmount);
    }

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserId() { return userId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

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
    public void setProductPrice(Double productPrice) 
    {
        this.productPrice = productPrice;
    }

    public Double getProductPrice() 
    {
        return productPrice;
    }
    public void setProductAmount(Integer productAmount)
    {
        this.productAmount = productAmount;
    }

    public Integer getProductAmount()
    {
        return productAmount;
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
            .append("userId", getUserId())
            .append("accountId", getAccountId())
            .append("productId", getProductId())
            .append("productType", getProductType())
            .append("productPrice", getProductPrice())
            .append("productAmount", getProductAmount())
            .append("orderTime", getOrderTime())
            .append("orderDirection", getOrderDirection())
            .append("orderPattern", getOrderPattern())
            .append("orderSituation", getOrderSituation())
            .toString();
    }
}
