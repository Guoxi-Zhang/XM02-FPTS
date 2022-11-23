package com.fpts.finance_warehouse.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 数据仓库对象 finance_summary
 * 
 * @author laybxc
 * @date 2022-11-23
 */
public class FinanceWarehouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 代码 */
    @Excel(name = "代码")
    private String id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 最新价 */
    @Excel(name = "最新价")
    private BigDecimal newPrice;

    /** 开盘价 */
    @Excel(name = "开盘价")
    private BigDecimal openPrice;

    /** 昨日结算 */
    @Excel(name = "昨日结算")
    private BigDecimal yesterdayPrice;

    /** 最高价 */
    @Excel(name = "最高价")
    private BigDecimal maxPrice;

    /** 最低价 */
    @Excel(name = "最低价")
    private BigDecimal minPrice;

    /** 涨幅 */
    @Excel(name = "涨幅")
    private BigDecimal increase;

    /** 涨速 */
    @Excel(name = "涨速")
    private BigDecimal increaseRate;

    /** 换手率 */
    @Excel(name = "换手率")
    private BigDecimal turnoverRate;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setNewPrice(BigDecimal newPrice) 
    {
        this.newPrice = newPrice;
    }

    public BigDecimal getNewPrice() 
    {
        return newPrice;
    }
    public void setOpenPrice(BigDecimal openPrice) 
    {
        this.openPrice = openPrice;
    }

    public BigDecimal getOpenPrice() 
    {
        return openPrice;
    }
    public void setYesterdayPrice(BigDecimal yesterdayPrice) 
    {
        this.yesterdayPrice = yesterdayPrice;
    }

    public BigDecimal getYesterdayPrice() 
    {
        return yesterdayPrice;
    }
    public void setMaxPrice(BigDecimal maxPrice) 
    {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMaxPrice() 
    {
        return maxPrice;
    }
    public void setMinPrice(BigDecimal minPrice) 
    {
        this.minPrice = minPrice;
    }

    public BigDecimal getMinPrice() 
    {
        return minPrice;
    }
    public void setIncrease(BigDecimal increase) 
    {
        this.increase = increase;
    }

    public BigDecimal getIncrease() 
    {
        return increase;
    }
    public void setIncreaseRate(BigDecimal increaseRate) 
    {
        this.increaseRate = increaseRate;
    }

    public BigDecimal getIncreaseRate() 
    {
        return increaseRate;
    }
    public void setTurnoverRate(BigDecimal turnoverRate) 
    {
        this.turnoverRate = turnoverRate;
    }

    public BigDecimal getTurnoverRate() 
    {
        return turnoverRate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("newPrice", getNewPrice())
            .append("openPrice", getOpenPrice())
            .append("yesterdayPrice", getYesterdayPrice())
            .append("maxPrice", getMaxPrice())
            .append("minPrice", getMinPrice())
            .append("increase", getIncrease())
            .append("increaseRate", getIncreaseRate())
            .append("turnoverRate", getTurnoverRate())
            .toString();
    }
}
