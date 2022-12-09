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
 * @date 2022-12-10
 */
public class AccountAssets extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long no;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 账户ID */
    @Excel(name = "账户ID")
    private String accountId;

    /** 账户余额 */
    @Excel(name = "账户余额")
    private BigDecimal accountBalance;

    /** 用户的资产数额 */
    @Excel(name = "用户的资产数额")
    private BigDecimal totalAssets;

    /** A股总资产 */
    @Excel(name = "A股总资产")
    private BigDecimal astockAssets;

    /** B股总资产 */
    @Excel(name = "B股总资产")
    private BigDecimal bstockAssets;

    /** 债券总资产 */
    @Excel(name = "债券总资产")
    private BigDecimal bonfAssets;

    /** 基金总资产 */
    @Excel(name = "基金总资产")
    private BigDecimal fundAssets;

    public void setNo(Long no) 
    {
        this.no = no;
    }

    public Long getNo() 
    {
        return no;
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
    public void setAstockAssets(BigDecimal astockAssets) 
    {
        this.astockAssets = astockAssets;
    }

    public BigDecimal getAstockAssets() 
    {
        return astockAssets;
    }
    public void setBstockAssets(BigDecimal bstockAssets) 
    {
        this.bstockAssets = bstockAssets;
    }

    public BigDecimal getBstockAssets() 
    {
        return bstockAssets;
    }
    public void setBonfAssets(BigDecimal bonfAssets) 
    {
        this.bonfAssets = bonfAssets;
    }

    public BigDecimal getBonfAssets() 
    {
        return bonfAssets;
    }
    public void setFundAssets(BigDecimal fundAssets) 
    {
        this.fundAssets = fundAssets;
    }

    public BigDecimal getFundAssets() 
    {
        return fundAssets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("no", getNo())
            .append("userId", getUserId())
            .append("accountId", getAccountId())
            .append("accountBalance", getAccountBalance())
            .append("totalAssets", getTotalAssets())
            .append("astockAssets", getAstockAssets())
            .append("bstockAssets", getBstockAssets())
            .append("bonfAssets", getBonfAssets())
            .append("fundAssets", getFundAssets())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
