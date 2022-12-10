package com.fpts.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 实名认证对象 certification
 * 
 * @author ruoyi
 * @date 2022-12-11
 */
public class Certification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String identityCardNumber;

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
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }
    public void setIdentityCardNumber(String identityCardNumber) 
    {
        this.identityCardNumber = identityCardNumber;
    }

    public String getIdentityCardNumber() 
    {
        return identityCardNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("realName", getRealName())
            .append("identityCardNumber", getIdentityCardNumber())
            .toString();
    }
}
