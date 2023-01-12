package com.fpts.web.controller.system;

import com.fpts.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.core.domain.entity.SysUser;
import com.fpts.common.utils.StringUtils;
import com.fpts.framework.shiro.service.SysRegisterService;
import com.fpts.system.service.ISysConfigService;

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
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(SysUser user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        if (StringUtils.isEmpty(msg))
        {
            Long [] roleIds = new Long[1];
            roleIds[0] = 2L;
            userService.insertUserRole(user.getUserId(), roleIds);

            return success();
        }
        else
        {
            return  error();
        }
//        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
