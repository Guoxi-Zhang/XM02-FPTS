package com.fpts.assets.domain;

import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    private Long accountBalance;

    /** 用户的资产数额 */
    @Excel(name = "用户的资产数额")
    private Long totalAssets;

    /** A股总资产 */
    @Excel(name = "A股总资产")
    private Long astockAssets;

    /** B股总资产 */
    @Excel(name = "B股总资产")
    private Long bstockAssets;

    /** 债券总资产 */
    @Excel(name = "债券总资产")
    private Long bonfAssets;

    /** 基金总资产 */
    @Excel(name = "基金总资产")
    private Long fundAssets;

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
    public void setAccountBalance(Long accountBalance)
    {
        this.accountBalance = accountBalance;
    }

    public Long getAccountBalance()
    {
        return accountBalance;
    }
    public void setTotalAssets(Long totalAssets)
    {
        this.totalAssets = totalAssets;
    }

    public Long getTotalAssets()
    {
        return totalAssets;
    }
    public void setAstockAssets(Long astockAssets)
    {
        this.astockAssets = astockAssets;
    }

    public Long getAstockAssets()
    {
        return astockAssets;
    }
    public void setBstockAssets(Long bstockAssets)
    {
        this.bstockAssets = bstockAssets;
    }

    public Long getBstockAssets()
    {
        return bstockAssets;
    }
    public void setBonfAssets(Long bonfAssets)
    {
        this.bonfAssets = bonfAssets;
    }

    public Long getBonfAssets()
    {
        return bonfAssets;
    }
    public void setFundAssets(Long fundAssets)
    {
        this.fundAssets = fundAssets;
    }

    public Long getFundAssets()
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
