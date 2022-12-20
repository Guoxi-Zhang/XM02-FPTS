package com.fpts.framework.shiro.uniauthtoken;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义登录Token
 *
 * @author melody0123
 */
public class ExtendedUsernamePasswordToken extends UsernamePasswordToken
{
    private static final long serialVersionUID = 1L;

    private LoginType type;

    public ExtendedUsernamePasswordToken()
    {
    }

    public ExtendedUsernamePasswordToken(String username, String password, LoginType type, boolean rememberMe)
    {
        super(username, password, rememberMe);
        this.type = type;
    }

    public ExtendedUsernamePasswordToken(String username, LoginType type)
    {
        super(username, "", false, null);
        this.type = type;
    }

    public ExtendedUsernamePasswordToken(String username, String password, LoginType type)
    {
        super(username, password, false, null);
        this.type = type;
    }

    public LoginType getType()
    {
        return type;
    }

    public void setType(LoginType type)
    {
        this.type = type;
    }
}