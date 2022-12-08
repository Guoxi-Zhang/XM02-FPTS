package com.fpts.bank_account_management.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 银行账户管理对象 account_info
 * 
 * @author ruoyi
 * @date 2022-12-08
 */
public class AccountInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 用户银行账户ID */
    @Excel(name = "用户银行账户ID")
    private String accountId;

    /** 账户类型 */
    @Excel(name = "账户类型")
    private String accountType;

    /** 账户状态 */
    @Excel(name = "账户状态")
    private String accountStatus;

    /** 账户异常原因 */
    @Excel(name = "账户异常原因")
    private String accountSuspendReason;

    /** 账户每日交易上限 */
    @Excel(name = "账户每日交易上限")
    private BigDecimal accountDailyTransactionLimit;

    /** 账户余额数值 */
    @Excel(name = "账户余额数值")
    private BigDecimal accountBalance;

    /** 账户货币单位 */
    @Excel(name = "账户货币单位")
    private String accountBalanceUnit;

    /** 账户创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "账户创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accountCreateTime;

    /** 账户最后一次使用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "账户最后一次使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accountLastUsedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setAccountId(String accountId) 
    {
        this.accountId = accountId;
    }

    public String getAccountId() 
    {
        return accountId;
    }
    public void setAccountType(String accountType) 
    {
        this.accountType = accountType;
    }

    public String getAccountType() 
    {
        return accountType;
    }
    public void setAccountStatus(String accountStatus) 
    {
        this.accountStatus = accountStatus;
    }

    public String getAccountStatus() 
    {
        return accountStatus;
    }
    public void setAccountSuspendReason(String accountSuspendReason) 
    {
        this.accountSuspendReason = accountSuspendReason;
    }

    public String getAccountSuspendReason() 
    {
        return accountSuspendReason;
    }
    public void setAccountDailyTransactionLimit(BigDecimal accountDailyTransactionLimit) 
    {
        this.accountDailyTransactionLimit = accountDailyTransactionLimit;
    }

    public BigDecimal getAccountDailyTransactionLimit() 
    {
        return accountDailyTransactionLimit;
    }
    public void setAccountBalance(BigDecimal accountBalance) 
    {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getAccountBalance() 
    {
        return accountBalance;
    }
    public void setAccountBalanceUnit(String accountBalanceUnit) 
    {
        this.accountBalanceUnit = accountBalanceUnit;
    }

    public String getAccountBalanceUnit() 
    {
        return accountBalanceUnit;
    }
    public void setAccountCreateTime(Date accountCreateTime) 
    {
        this.accountCreateTime = accountCreateTime;
    }

    public Date getAccountCreateTime() 
    {
        return accountCreateTime;
    }
    public void setAccountLastUsedTime(Date accountLastUsedTime) 
    {
        this.accountLastUsedTime = accountLastUsedTime;
    }

    public Date getAccountLastUsedTime() 
    {
        return accountLastUsedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("accountId", getAccountId())
            .append("accountType", getAccountType())
            .append("accountStatus", getAccountStatus())
            .append("accountSuspendReason", getAccountSuspendReason())
            .append("accountDailyTransactionLimit", getAccountDailyTransactionLimit())
            .append("accountBalance", getAccountBalance())
            .append("accountBalanceUnit", getAccountBalanceUnit())
            .append("accountCreateTime", getAccountCreateTime())
            .append("accountLastUsedTime", getAccountLastUsedTime())
            .toString();
    }
}
