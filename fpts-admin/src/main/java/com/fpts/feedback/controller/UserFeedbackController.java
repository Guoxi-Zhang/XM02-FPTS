package com.fpts.feedback.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.fpts.todo.domain.TodoList;
import com.fpts.weathers.domain.CityWeather;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/wxGet/{Navtab}", method = RequestMethod.POST)
    @ResponseBody
    public List<UserFeedback> get(UserFeedback userFeedback, @PathVariable("Navtab") String tab){
        List<UserFeedback> list = userFeedbackService.selectUserFeedbackList(userFeedback);
        List<UserFeedback> ansList = new ArrayList<>();
        for(UserFeedback t:list){
            String adminFeedbackContent = t.getAdminFeedbackContent();
            if(adminFeedbackContent.contains("<p>")){
                t.setAdminFeedbackContent(t.getAdminFeedbackContent().replace("<p>", ""));
                System.out.println(adminFeedbackContent.replace("<p>", ""));
            }
            if(adminFeedbackContent.contains("</p>")){
                t.setAdminFeedbackContent(t.getAdminFeedbackContent().replace("</p>", ""));
            }
            if(tab.equals("0") && t.getCompletemark().equals("0")){//进行中待办事项
                ansList.add(t);
            }else if(tab.equals("1") && t.getCompletemark().equals("1")){//已完成待办事项
                ansList.add(t);
            }
        }
        System.out.println(ansList.toString());
        return ansList;
    }

    @RequestMapping(value = "/wxEdit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public UserFeedback wxEdit( @PathVariable("userFeedbackId") Long userFeedbackId){
        UserFeedback item = userFeedbackService.selectUserFeedbackByUserFeedbackId(Long.valueOf(userFeedbackId));
        String adminFeedbackContent = item.getAdminFeedbackContent();
        if(adminFeedbackContent.contains("<p>")){
            item.setAdminFeedbackContent(item.getAdminFeedbackContent().replace("<p>", ""));
            System.out.println(adminFeedbackContent.replace("<p>", ""));
        }
        if(adminFeedbackContent.contains("</p>")){
            item.setAdminFeedbackContent(item.getAdminFeedbackContent().replace("</p>", ""));
        }
        return item;
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

    /**
     * 打印跳转
     */
    @RequestMapping("/feedbackprint")
    public String print(){

        return prefix + "/feedbackprint";
    }

    /**
     * 打印操作
     */
    @PostMapping("/printToHtml")
    @ResponseBody
    public TableDataInfo printToHtml(UserFeedback userFeedback)
    {
//      startPage();
        List<UserFeedback> list = userFeedbackService.selectUserFeedbackList(userFeedback);

        return getDataTable(list);
    }
    /**
     * 统计报表
     */
//    @PostMapping( "/chart")
    @RequestMapping("/feedbackchart")
    public String showChart(ModelMap mmap){
        List<UserFeedback> list = userFeedbackService.selectUserFeedbackList( new UserFeedback());
       SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
        Map<String, Integer> map = new TreeMap<String, Integer>();
        for( UserFeedback t: list){
            Date tempDate = t.getUserFeedbackCreatetime();
            String date = dateformat.format(tempDate);

            if(map.containsKey(date)){
                int cnt = map.get(date);
                map.replace(date, cnt, cnt + 1);
            }else{
                map.put(date, 1);
            }
        }
        String[] dateSet = map.keySet().toArray(new String[0]);
        List<String> dateList= Arrays.asList(dateSet);
        Integer[] cntSet = map.values().toArray(new Integer[0]);
        List<Integer> cntList=Arrays.asList(cntSet);

        System.out.println(dateList.toString());
        System.out.println(cntList.toString());
        mmap.put("dateList", dateList);
        mmap.put("cntList", cntList);
        return prefix + "/feedbackchart";
    }

}
