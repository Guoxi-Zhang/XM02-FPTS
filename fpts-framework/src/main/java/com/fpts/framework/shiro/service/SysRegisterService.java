package com.fpts.framework.shiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fpts.common.constant.Constants;
import com.fpts.common.constant.ShiroConstants;
import com.fpts.common.constant.UserConstants;
import com.fpts.common.core.domain.entity.SysUser;
import com.fpts.common.utils.DateUtils;
import com.fpts.common.utils.MessageUtils;
import com.fpts.common.utils.ServletUtils;
import com.fpts.common.utils.ShiroUtils;
import com.fpts.common.utils.StringUtils;
import com.fpts.framework.manager.AsyncManager;
import com.fpts.framework.manager.factory.AsyncFactory;
import com.fpts.system.service.ISysUserService;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    /**
     * 注册
     */
    public String register(SysUser user)
    {
        String msg = "", loginName = user.getLoginName(), password = user.getPassword();

        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            msg = "验证码错误";
        }
        else if (StringUtils.isEmpty(loginName))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (loginName.length() < UserConstants.USERNAME_MIN_LENGTH
                || loginName.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(loginName)))
        {
            msg = "保存用户'" + loginName + "'失败，注册账号已存在";
        }
        else
        {
            user.setPwdUpdateDate(DateUtils.getNowDate());
            user.setUserName(loginName);
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
            boolean regFlag = userService.registerUser(user);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                // 添加角色为普通用户
                // System.out.println(user);
                // 取 userId 并设置 roleId = 2
                Long userId = user.getUserId();
                Long [] roleIds = new Long[1];
                roleIds[0] = 2L;
                // 执行插入操作
                if (userService.insertUserRoleWhenRegister(userId, roleIds) > 0)
                {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.REGISTER, MessageUtils.message("user.register.success")));
                }
                else
                {
                    msg = "分配角色失败，请联系管理员";
                }
            }
        }
        return msg;
    }
}
