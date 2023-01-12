package com.fpts.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 注册令牌数据对象 register_token
 * 
 * @author ruoyi
 * @date 2023-01-13
 */
public class RegisterToken extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 注册令牌 */
    @Excel(name = "注册令牌")
    private String token;

    /** 注册者的session_id */
    @Excel(name = "注册者的session_id")
    private String sessionId;

    /** 注册者的邮箱 */
    @Excel(name = "注册者的邮箱")
    private String emailTo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setToken(String token) 
    {
        this.token = token;
    }

    public String getToken() 
    {
        return token;
    }
    public void setSessionId(String sessionId) 
    {
        this.sessionId = sessionId;
    }

    public String getSessionId() 
    {
        return sessionId;
    }
    public void setEmailTo(String emailTo) 
    {
        this.emailTo = emailTo;
    }

    public String getEmailTo() 
    {
        return emailTo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("token", getToken())
            .append("sessionId", getSessionId())
            .append("emailTo", getEmailTo())
            .append("createTime", getCreateTime())
            .toString();
    }
}
