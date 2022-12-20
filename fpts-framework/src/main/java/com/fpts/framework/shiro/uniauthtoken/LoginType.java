package com.fpts.framework.shiro.uniauthtoken;

/**
 * 登录类型枚举类
 *
 * @author melody0123
 */
public enum LoginType
{
    /**
     * 密码登录
     */
    PASSWORD("password"),
    /**
     * 免密码登录
     */
    NOPASSWD("nopasswd");

    private String desc;

    LoginType(String desc)
    {
        this.desc = desc;
    }

    public String getDesc()
    {
        return desc;
    }
}
