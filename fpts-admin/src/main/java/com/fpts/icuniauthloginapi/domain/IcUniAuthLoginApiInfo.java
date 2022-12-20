package com.fpts.icuniauthloginapi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 外部用户，用于存储从其他应用登录FPTS的用户的原始信息对象 external_user_info
 *
 * @author ruoyi
 * @date 2022-12-17
 */
public class IcUniAuthLoginApiInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户在FPTS中的ID */
    private Long userId;

    /** OAuth系统提供的用户ID */
    @Excel(name = "OAuth系统提供的用户ID")
    private String openId;

    /** OAuth系统提供的用户Email */
    @Excel(name = "OAuth系统提供的用户Email")
    private String email;

    /** OAuth系统提供的用户昵称 */
    @Excel(name = "OAuth系统提供的用户昵称")
    private String nickName;

    /** OAuth系统或者登录应用的名称 */
    @Excel(name = "OAuth系统或者登录应用的名称")
    private String appName;

    /** OAuth系统或者登录应用在FPTS中的ID */
    @Excel(name = "OAuth系统或者登录应用在FPTS中的ID")
    private String appId;

    /** 访问令牌 */
    @Excel(name = "访问令牌")
    private String accessToken;

    /** 刷新令牌 */
    @Excel(name = "刷新令牌")
    private String refreshToken;

    /**
     * 默认构造函数，还是有用的
     */
    public  IcUniAuthLoginApiInfo() {}

    public IcUniAuthLoginApiInfo(long userId, String accessToken, String refreshToken, String appId, String appName,
                                 String nickName, String email, String openId)
    {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.appId = appId;
        this.appName = appName;
        this.nickName = nickName;
        this.email = email;
        this.openId = openId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    public String getOpenId()
    {
        return openId;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getNickName()
    {
        return nickName;
    }
    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getAppName()
    {
        return appName;
    }
    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getAppId()
    {
        return appId;
    }
    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getAccessToken()
    {
        return accessToken;
    }
    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("openId", getOpenId())
                .append("email", getEmail())
                .append("nickName", getNickName())
                .append("appName", getAppName())
                .append("appId", getAppId())
                .append("accessToken", getAccessToken())
                .append("refreshToken", getRefreshToken())
                .toString();
    }
}
