package com.fpts.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 重置密码请求的数据对象 password_reset
 * 
 * @author ruoyi
 * @date 2023-01-09
 */
public class SysPasswordReset extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** session ID */
    @Excel(name = "session ID")
    private String sessionId;

    /** 重置令牌 */
    @Excel(name = "重置令牌")
    private String resetToken;

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
    public void setSessionId(String sessionId) 
    {
        this.sessionId = sessionId;
    }

    public String getSessionId() 
    {
        return sessionId;
    }
    public void setResetToken(String resetToken) 
    {
        this.resetToken = resetToken;
    }

    public String getResetToken() 
    {
        return resetToken;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("sessionId", getSessionId())
            .append("resetToken", getResetToken())
            .append("createTime", getCreateTime())
            .toString();
    }
}
