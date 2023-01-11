package com.fpts.web.controller.monitor;

import java.util.List;

import com.fpts.common.core.domain.entity.SysRole;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.fpts.common.annotation.Log;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.core.page.TableDataInfo;
import com.fpts.common.core.text.Convert;
import com.fpts.common.enums.BusinessType;
import com.fpts.common.enums.OnlineStatus;
import com.fpts.common.utils.ShiroUtils;
import com.fpts.framework.shiro.session.OnlineSession;
import com.fpts.framework.shiro.session.OnlineSessionDAO;
import com.fpts.system.domain.SysUserOnline;
import com.fpts.system.service.ISysUserOnlineService;

/**
 * 在线用户监控
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/online")
public class SysUserOnlineController extends BaseController
{
    private String prefix = "monitor/online";

    @Autowired
    private ISysUserOnlineService userOnlineService;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    @PostMapping("/getLoginNameBySessionId")
    @ResponseBody
    public SysUserOnline SysUserOnline(@RequestParam String sessionId)
    {
        return userOnlineService.selectOnlineById(sessionId);
    }

    @RequiresPermissions("monitor:online:view")
    @GetMapping()
    public String online()
    {
        return prefix + "/online";
    }

    @RequiresPermissions("monitor:online:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUserOnline userOnline)
    {
        startPage();
        List<SysUserOnline> list = userOnlineService.selectUserOnlineList(userOnline);
        return getDataTable(list);
    }

    @RequiresPermissions(value = { "monitor:online:batchForceLogout", "monitor:online:forceLogout" }, logical = Logical.OR)
    @Log(title = "在线用户", businessType = BusinessType.FORCE)
    @PostMapping("/batchForceLogout")
    @ResponseBody
    public AjaxResult batchForceLogout(String ids)
    {
        for (String sessionId : Convert.toStrArray(ids))
        {
            SysUserOnline online = userOnlineService.selectOnlineById(sessionId);
            if (online == null)
            {
                return error("用户已下线");
            }
            OnlineSession onlineSession = (OnlineSession) onlineSessionDAO.readSession(online.getSessionId());
            if (onlineSession == null)
            {
                return error("用户已下线");
            }
            if (sessionId.equals(ShiroUtils.getSessionId()))
            {
                return error("当前登录用户无法强退");
            }
            onlineSessionDAO.delete(onlineSession);
            online.setStatus(OnlineStatus.off_line);
            userOnlineService.saveOnline(online);
            userOnlineService.removeUserCache(online.getLoginName(), sessionId);
        }
        return success();
    }

    /**
     * 打印页面跳转
     * @return 页面地址
     */
    @GetMapping("print")
    public String print()
    {
        return prefix + "/print";
    }

    /**
     * 打印操作
     */
    @PostMapping("/printToHtml")
    @ResponseBody
    public TableDataInfo printToHtml(SysUserOnline userOnline)
    {
        List<SysUserOnline> list = userOnlineService.selectUserOnlineList(userOnline);

        return getDataTable(list);
    }
}
