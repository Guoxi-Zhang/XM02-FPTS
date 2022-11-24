package com.fpts.finance_warehouse.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 数据仓库对象 finance_summary
 * 
 * @author laybxc
 * @date 2022-11-24
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
    private Double newPrice;

    /** 开盘价 */
    @Excel(name = "开盘价")
    private Double openPrice;

    /** 昨日结算 */
    @Excel(name = "昨日结算")
    private Double yesterdayPrice;

    /** 最高价 */
    @Excel(name = "最高价")
    private Double maxPrice;

    /** 最低价 */
    @Excel(name = "最低价")
    private Double minPrice;

    /** 涨幅 */
    @Excel(name = "涨幅")
    private Double increase;

    /** 涨速 */
    @Excel(name = "涨速")
    private Double increaseRate;

    /** 换手率 */
    @Excel(name = "换手率")
    private Double turnoverRate;

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
    public void setNewPrice(Double newPrice) 
    {
        this.newPrice = newPrice;
    }

    public Double getNewPrice() 
    {
        return newPrice;
    }
    public void setOpenPrice(Double openPrice) 
    {
        this.openPrice = openPrice;
    }

    public Double getOpenPrice() 
    {
        return openPrice;
    }
    public void setYesterdayPrice(Double yesterdayPrice) 
    {
        this.yesterdayPrice = yesterdayPrice;
    }

    public Double getYesterdayPrice() 
    {
        return yesterdayPrice;
    }
    public void setMaxPrice(Double maxPrice) 
    {
        this.maxPrice = maxPrice;
    }

    public Double getMaxPrice() 
    {
        return maxPrice;
    }
    public void setMinPrice(Double minPrice) 
    {
        this.minPrice = minPrice;
    }

    public Double getMinPrice() 
    {
        return minPrice;
    }
    public void setIncrease(Double increase) 
    {
        this.increase = increase;
    }

    public Double getIncrease() 
    {
        return increase;
    }
    public void setIncreaseRate(Double increaseRate) 
    {
        this.increaseRate = increaseRate;
    }

    public Double getIncreaseRate() 
    {
        return increaseRate;
    }
    public void setTurnoverRate(Double turnoverRate) 
    {
        this.turnoverRate = turnoverRate;
    }

    public Double getTurnoverRate() 
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
