package com.fpts.feedback.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fpts.common.annotation.Log;
import com.fpts.common.enums.BusinessType;
import com.fpts.feedback.domain.UserFeedback;
import com.fpts.feedback.service.IUserFeedbackService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

/**
 * 用户反馈Controller
 * 
 * @author lyc
 * @date 2022-11-27
 */
@Controller
@RequestMapping("/feedback/feedback")
public class UserFeedbackController extends BaseController
{
    private String prefix = "feedback/feedback";

    @Autowired
    private IUserFeedbackService userFeedbackService;

    @RequiresPermissions("feedback:feedback:view")
    @GetMapping()
    public String feedback()
    {
        return prefix + "/feedback";
    }

    /**
     * 查询用户反馈列表
     */
    @RequiresPermissions("feedback:feedback:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserFeedback userFeedback)
    {
        startPage();
        List<UserFeedback> list = userFeedbackService.selectUserFeedbackList(userFeedback);
        return getDataTable(list);
    }

    /**
     * 导出用户反馈列表
     */
    @RequiresPermissions("feedback:feedback:export")
    @Log(title = "用户反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserFeedback userFeedback)
    {
        List<UserFeedback> list = userFeedbackService.selectUserFeedbackList(userFeedback);
        ExcelUtil<UserFeedback> util = new ExcelUtil<UserFeedback>(UserFeedback.class);
        return util.exportExcel(list, "用户反馈数据");
    }

    /**
     * 新增用户反馈
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户反馈
     */
    @RequiresPermissions("feedback:feedback:add")
    @Log(title = "用户反馈", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserFeedback userFeedback)
    {
        return toAjax(userFeedbackService.insertUserFeedback(userFeedback));
    }

    /**
     * 修改用户反馈
     */
    @RequiresPermissions("feedback:feedback:edit")
    @GetMapping("/edit/{userFeedbackId}")
    public String edit(@PathVariable("userFeedbackId") Long userFeedbackId, ModelMap mmap)
    {
        UserFeedback userFeedback = userFeedbackService.selectUserFeedbackByUserFeedbackId(userFeedbackId);
        mmap.put("userFeedback", userFeedback);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户反馈
     */
    @RequiresPermissions("feedback:feedback:edit")
    @Log(title = "用户反馈", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserFeedback userFeedback)
    {
        return toAjax(userFeedbackService.updateUserFeedback(userFeedback));
    }

    /**
     * 删除用户反馈
     */
    @RequiresPermissions("feedback:feedback:remove")
    @Log(title = "用户反馈", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userFeedbackService.deleteUserFeedbackByUserFeedbackIds(ids));
    }
}
