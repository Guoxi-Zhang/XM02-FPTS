package com.fpts.web.controller.system;

import com.fpts.common.constant.ShiroConstants;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.core.domain.entity.SysUser;
import com.fpts.common.exception.user.CaptchaException;
import com.fpts.common.utils.ServletUtils;
import com.fpts.system.domain.SysPasswordReset;
import com.fpts.system.service.ISysPasswordResetService;
import com.fpts.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/resetPassword")
public class SysPasswordResetController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysPasswordResetService passwordResetService;

    /**
     * 生成随机字符串，字符串包含字符 a-z & A-Z & 0-9
     * @param length 生成的字符串的长度
     * @return 随机字符串
     */
    public static String getRandomString(int length)
    {
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @PostMapping("/sendCode")
    @ResponseBody
    public AjaxResult validateAndSendCode(@RequestParam String email)
    {
        SysUser queryResult = userService.selectUserByEmail(email);
        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            // 验证码错误
            throw new CaptchaException();
        }
        else if (queryResult == null)
        {
            // 邮箱不存在
            return AjaxResult.error("邮箱不存在");
        }
        else
        {
            // 用邮箱把用户ID查出来
            Long userId = queryResult.getUserId();
            // 获取 sessionId
            String sessionId = ServletUtils.getRequest().getSession().getId();
            // 生成重置令牌
            String token = getRandomString(10);
            // 当前时间
            Date createTime = new Date();
            // 存进数据库
            SysPasswordReset record = new SysPasswordReset();
            record.setResetToken(token);
            record.setUserId(userId);
            record.setSessionId(sessionId);
            record.setCreateTime(createTime);
            // 发送邮件

            return AjaxResult.success();
        }
    }
}
