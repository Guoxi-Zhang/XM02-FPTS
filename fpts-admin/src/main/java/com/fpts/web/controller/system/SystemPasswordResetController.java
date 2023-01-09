package com.fpts.web.controller.system;

import com.fpts.common.constant.ShiroConstants;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.core.domain.entity.SysUser;
import com.fpts.common.exception.user.CaptchaException;
import com.fpts.common.utils.ServletUtils;
import com.fpts.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resetPassword")
public class SystemPasswordResetController
{
    @Autowired
    private ISysUserService userService;

    @PostMapping("/sendCode")
    @ResponseBody
    public AjaxResult validateAndSendCode(@RequestParam String email)
    {
        SysUser queryResult = new SysUser();
        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            // 验证码错误
            throw new CaptchaException();
        }
        else if (userService.selectUserByEmail(email) == null)
        {
            // 邮箱不存在
            return AjaxResult.error("邮箱不存在");
        }
        else
        {

            return AjaxResult.success();
        }
    }
}
