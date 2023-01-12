package com.fpts.web.controller.system;

import com.fpts.common.constant.ShiroConstants;
import com.fpts.common.exception.user.CaptchaException;
import com.fpts.common.utils.ServletUtils;
import com.fpts.system.domain.RegisterToken;
import com.fpts.system.domain.SysPasswordReset;
import com.fpts.system.service.IRegisterTokenService;
import com.fpts.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.core.domain.entity.SysUser;
import com.fpts.common.utils.StringUtils;
import com.fpts.framework.shiro.service.SysRegisterService;
import com.fpts.system.service.ISysConfigService;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 注册验证
 * 
 * @author ruoyi
 */
@Controller
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IRegisterTokenService registerTokenService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(SysUser user, @RequestParam String token)
    {
        // System.out.println(token);

        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        //验证令牌的有效性
        // 用 token 和 seesionId 同时查用户
        RegisterToken conditions = new RegisterToken();
        conditions.setToken(token);
        conditions.setSessionId(ServletUtils.getRequest().getSession().getId());
        List<RegisterToken> result = registerTokenService.selectRegisterTokenList(conditions);
        // 检验查询结果有效性
        if(result.size() != 1)
        {
            // 无效
            return AjaxResult.error("无效的令牌");
        }
        else
        {
            // 有效，注册用户
            String msg = registerService.register(user);
            return StringUtils.isEmpty(msg) ? success() : error(msg);
        }
    }

    @GetMapping("/getRegisterToken")
    public String getRegisterToken()
    {
        return "getRegisterToken";
    }

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

    @PostMapping("/getRegisterToken")
    @ResponseBody
    public AjaxResult ajaxGetRegisterToken(@RequestParam String email)
    {
        SysUser queryResult = userService.selectUserByEmail(email);
        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            // 验证码错误
            throw new CaptchaException();
        }
        else if (queryResult != null)
        {
            // 邮箱已经存在
            return error("邮箱已经存在");
        }
        else
        {
            // 已经验证了邮箱的唯一性
            // 获取 sessionId
            String sessionId = ServletUtils.getRequest().getSession().getId();
            // 生成重置令牌
            String token = getRandomString(10);
            // 当前时间
            Date createTime = new Date();
            // 存进数据库
            RegisterToken record = new RegisterToken();
            record.setToken(token);
            record.setSessionId(sessionId);
            record.setEmailTo(email);
            record.setCreateTime(createTime);
            registerTokenService.insertRegisterToken(record);
            // 发邮件
            sendSimpleMail(
                    "melody953@qq.com",
                    email,
                    "【XM02-FPTS账号事务局】注册令牌",
                    "这是您用于注册账号的令牌，请勿泄露给他人，如果您没有进行相关操作，请忽略此邮件！\n" + token);

            return success();
        }
    }
}
