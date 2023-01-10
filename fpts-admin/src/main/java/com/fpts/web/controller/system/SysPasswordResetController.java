package com.fpts.web.controller.system;

import com.fpts.common.annotation.Log;
import com.fpts.common.constant.ShiroConstants;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.core.domain.entity.SysUser;
import com.fpts.common.enums.BusinessType;
import com.fpts.common.exception.user.CaptchaException;
import com.fpts.common.utils.DateUtils;
import com.fpts.common.utils.ServletUtils;
import com.fpts.common.utils.ShiroUtils;
import com.fpts.framework.shiro.service.SysPasswordService;
import com.fpts.system.domain.SysPasswordReset;
import com.fpts.system.service.ISysPasswordResetService;
import com.fpts.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/resetPassword")
public class SysPasswordResetController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysPasswordResetService passwordResetService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SysPasswordService passwordService;


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

    public void sendSimpleMail(String from, String to, String subject, String text)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发件人
        simpleMailMessage.setFrom(from);
        // 收件人
        simpleMailMessage.setTo(to);
        // 邮件主题
        simpleMailMessage.setSubject(subject);
        // 邮件内容
        simpleMailMessage.setText(text);
        mailSender.send(simpleMailMessage);
    }

    @GetMapping("/sendCode")
    public String passwordReset()
    {
        return "sendCode";
    }

    /**
     * 用于向用户的邮箱发送验证码
     * @param email 用户提供的邮箱地址
     * @return 完成状态信息
     */
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
            return error("邮箱不存在");
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
            passwordResetService.insertPasswordReset(record);
            // 发送邮件
            sendSimpleMail(
                    "melody953@qq.com",
                    email,
                    "【XM02-FPTS账号事务局】重置密码令牌",
                    "这是您用于重置密码的令牌，请勿泄露给他人，如果您没有进行相关操作，建议您立刻更改密码并联系管理员！\n" + token);

            return success();
        }
    }

    /**
     * 用于重置用户密码
     * @param newPassword 用户提供的新密码
     * @param token 邮件验证码
     * @return 完成状态信息
     */
    @Log(title = "邮件重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/setNewPassword")
    @ResponseBody
    public AjaxResult setNewPassword(@RequestParam String newPassword, @RequestParam String token)
    {
        // 用 token 和 seesionId 同时查用户
        SysPasswordReset conditions = new SysPasswordReset();
        conditions.setResetToken(token);
        conditions.setSessionId(ServletUtils.getRequest().getSession().getId());
        List<SysPasswordReset> result = passwordResetService.selectPasswordResetList(conditions);
        // 检验查询结果有效性
        if(result.size() != 1)
        {
            // 无效
            return AjaxResult.error("无效的验证码");
        }
        else
        {
            // 有效，重置密码
            SysUser user = userService.selectUserById(result.get(0).getUserId());
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
            user.setPwdUpdateDate(DateUtils.getNowDate());
            userService.updateUserInfo(user);
            return success();
        }
    }
}
