package com.fpts.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fpts.bank_account_management.domain.AccountInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fpts.common.annotation.Log;
import com.fpts.common.constant.UserConstants;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.core.domain.entity.SysRole;
import com.fpts.common.core.domain.entity.SysUser;
import com.fpts.common.core.page.TableDataInfo;
import com.fpts.common.core.text.Convert;
import com.fpts.common.enums.BusinessType;
import com.fpts.common.utils.ShiroUtils;
import com.fpts.common.utils.StringUtils;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.framework.shiro.service.SysPasswordService;
import com.fpts.framework.shiro.util.AuthorizationUtils;
import com.fpts.system.service.ISysPostService;
import com.fpts.system.service.ISysRoleService;
import com.fpts.system.service.ISysUserService;

/**
 * 用户信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    private String prefix = "system/user";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String message = userService.importUser(userList, updateSupport, getLoginName());
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 以用户的登录名查询用户的所有信息
     * @param loginName 查询参数，用户的登录名
     * @return SysUser 对象
     */
    @PostMapping("/getUserInfo")
    @ResponseBody
    public SysUser getUserInfoByLoginName(@RequestParam String loginName)
    {
        return userService.selectUserByLoginName(loginName);
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("roles", roleService.selectRoleAll().stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysUser user)
    {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName())))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(getLoginName());
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("system:user:edit")
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        userService.checkUserDataScope(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(userService.updateUser(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        if (userService.resetUserPwd(user) > 0)
        {
            if (ShiroUtils.getUserId().longValue() == user.getUserId().longValue())
            {
                setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    /**
     * 进入授权角色页
     */
    @GetMapping("/authRole/{userId}")
    public String authRole(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        SysUser user = userService.selectUserById(userId);
        // 获取用户所属的角色列表
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", user);
        mmap.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return prefix + "/authRole";
    }

    /**
     * 用户授权角色
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PostMapping("/authRole/insertAuthRole")
    @ResponseBody
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds)
    {
        userService.checkUserDataScope(userId);
        userService.insertUserAuth(userId, roleIds);
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return success();
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        if (ArrayUtils.contains(Convert.toLongArray(ids), getUserId()))
        {
            return error("当前用户不能删除");
        }
        return toAjax(userService.deleteUserByIds(ids));
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user)
    {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user)
    {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user)
    {
        return userService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        return toAjax(userService.changeStatus(user));
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
    public TableDataInfo printToHtml(SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);

        return getDataTable(list);
    }

    /**
     * 统计图表，统计近三个月以来的银行卡绑定数量
     */
    @RequestMapping("/chart")
    public String showChart(ModelMap mmap)
    {
        // 获取原始数据
        List<SysUser> list = userService.selectUserList(new SysUser());
        // 获取今天的时间
        Date now = new Date();
        String [] nowString = new SimpleDateFormat("yyyy-MM-dd").format(now).toString().split("-");

        // 获取本月第一天的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(calendar.DAY_OF_MONTH, 1); // 1-1
        Date FirstDayOfThisMonth = calendar.getTime();
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(FirstDayOfThisMonth).toString());
        // 获取本月最后一天
        calendar.roll(calendar.DAY_OF_MONTH, -1); // 1-31
        Date LastDayOfThisMonth = calendar.getTime();
        calendar.set(calendar.DAY_OF_MONTH, 1); // 1-1
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(LastDayOfThisMonth).toString());

        // 获取上个月第一天
        calendar.add(calendar.MONTH, -1); // 12-1
        calendar.set(calendar.DAY_OF_MONTH, 1); // 12-1
        Date FirstDayOfLastMonth = calendar.getTime();
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(FirstDayOfLastMonth).toString());
        // 获取上个月最后一天
        calendar.roll(calendar.DAY_OF_MONTH, -1); // 12-31
        Date LastDayOfLastMonth = calendar.getTime();
        calendar.set(calendar.DAY_OF_MONTH, 1); // 12-1
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(LastDayOfLastMonth).toString());

        // 获取上上个月第一天
        calendar.add(calendar.MONTH, -1); // 11-1
        calendar.set(calendar.DAY_OF_MONTH, 1); // 11-1
        Date FirstDayOfLLastMonth = calendar.getTime();
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(FirstDayOfLLastMonth).toString());
        // 获取上上个月最后一天
        calendar.roll(calendar.DAY_OF_MONTH, -1);
        Date LastDayOfLLastMonth = calendar.getTime();
        calendar.set(calendar.DAY_OF_MONTH, 1);
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(LastDayOfLLastMonth).toString());

        int [] countArray = new int[3];
        for (SysUser item: list)
        {
            Date time = item.getCreateTime();
            if (time.before(LastDayOfThisMonth) && time.after(FirstDayOfThisMonth) || time.equals(LastDayOfThisMonth) && time.equals(FirstDayOfThisMonth))
            {
                // 时间是否在本月
                countArray[0]++;
            }
            else if (time.before(LastDayOfLastMonth) && time.after(FirstDayOfLastMonth) || time.equals(LastDayOfLastMonth) && time.equals(FirstDayOfLastMonth))
            {
                // 时间是否在上个月
                countArray[1] ++;
            }
            else if (time.before(LastDayOfLLastMonth) && time.after(FirstDayOfLLastMonth) || time.equals(LastDayOfLLastMonth) && time.equals(FirstDayOfLLastMonth))
            {
                // 时间是否在上上个月
                countArray[2] ++;
            }
        }

        // 把月份打包
        String thisMonth = new SimpleDateFormat("yyyy.MM").format(FirstDayOfThisMonth).toString();
        // System.out.println(thisMonth);
        String lastMonth = new SimpleDateFormat("yyyy.MM").format(FirstDayOfLastMonth).toString();
        // System.out.println(lastMonth);
        String llastMonth = new SimpleDateFormat("yyyy.MM").format(FirstDayOfLLastMonth).toString();
        // System.out.println(llastMonth);
        List<String> monthList = new ArrayList<String>();
        monthList.add(thisMonth);
        monthList.add(lastMonth);
        monthList.add(llastMonth);
        System.out.println(monthList);
        mmap.put("monthList", monthList);

        // 把计数结果打包
        List<Integer> countList = new ArrayList<Integer>();
        countList.add(countArray[0]);
        countList.add(countArray[1]);
        countList.add(countArray[2]);
        // System.out.println(countList);
        mmap.put("countList", countList);

        return prefix + "/chart";
    }

}