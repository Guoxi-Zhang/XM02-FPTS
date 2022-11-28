package com.fpts.assets.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 查看资产对象 account_assets
 * 
 * @author lzy
 * @date 2022-11-27
 */
public class AccountAssets extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Integer No;

    /** 用户ID */
    private String userId;

    /** 账户ID */
    @Excel(name = "账户ID")
    private String accountId;

    /** 账户余额 */
    @Excel(name = "账户余额")
    private BigDecimal accountBalance;

    /** 总资产数 */
    @Excel(name = "总资产数")
    private BigDecimal totalAssets;

    /** 股票总资产 */
    @Excel(name = "股票总资产")
    private BigDecimal stockAssets;

    /** 黄金总资产 */
    @Excel(name = "黄金总资产")
    private BigDecimal goldAssets;

    /** 期货总资产 */
    @Excel(name = "期货总资产")
    private BigDecimal futureAssets;

    public void setNo(Integer No) 
    {
        this.No = No;
    }

    public Integer getNo() 
    {
        return No;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
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
    public void setAccountBalance(BigDecimal accountBalance) 
    {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getAccountBalance() 
    {
        return accountBalance;
    }
    public void setTotalAssets(BigDecimal totalAssets) 
    {
        this.totalAssets = totalAssets;
    }

    public BigDecimal getTotalAssets() 
    {
        return totalAssets;
    }
    public void setStockAssets(BigDecimal stockAssets) 
    {
        this.stockAssets = stockAssets;
    }

    public BigDecimal getStockAssets() 
    {
        return stockAssets;
    }
    public void setGoldAssets(BigDecimal goldAssets) 
    {
        this.goldAssets = goldAssets;
    }

    public BigDecimal getGoldAssets() 
    {
        return goldAssets;
    }
    public void setFutureAssets(BigDecimal futureAssets) 
    {
        this.futureAssets = futureAssets;
    }

    public BigDecimal getFutureAssets() 
    {
        return futureAssets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("No", getNo())
            .append("userId", getUserId())
            .append("accountId", getAccountId())
            .append("accountBalance", getAccountBalance())
            .append("totalAssets", getTotalAssets())
            .append("stockAssets", getStockAssets())
            .append("goldAssets", getGoldAssets())
            .append("futureAssets", getFutureAssets())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
