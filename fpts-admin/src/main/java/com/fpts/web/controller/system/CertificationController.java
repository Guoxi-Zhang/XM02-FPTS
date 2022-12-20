package com.fpts.web.controller.system;

import java.util.*;

import com.fpts.finance_forum.domain.FinanceForum;
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
import com.fpts.system.domain.Certification;
import com.fpts.system.service.ICertificationService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

/**
 * 实名认证Controller
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Controller
@RequestMapping("/system/certification")
public class CertificationController extends BaseController {
    private String prefix = "system/certification";

    @Autowired
    private ICertificationService certificationService;

    @RequiresPermissions("system:certification:view")
    @GetMapping()
    public String certification() {
        return prefix + "/certification";
    }

    /**
     * 查询实名认证列表
     */
    @RequiresPermissions("system:certification:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Certification certification) {
        startPage();
        List<Certification> list = certificationService.selectCertificationList(certification);
        return getDataTable(list);
    }

    /**
     * 统计报表
     */
    @RequestMapping("/chart")
    public String showChart(ModelMap mmap) {
        chartList(mmap);
        return prefix + "/chart";
    }

    @RequestMapping("/chartList")
    public void chartList(ModelMap mmap) {
        List<Certification> list = certificationService.selectCertificationList(new Certification());
        Map<String, Integer> map = new TreeMap<String, Integer>();
        String done = "1";
        String undone = "2";
        map.put(done, 0);
        map.put(undone, 0);
        for (Certification c : list) {
            String realName = c.getRealName();
            String identityCardNumber = c.getIdentityCardNumber();
            if ((!realName.isEmpty()) && (!identityCardNumber.isEmpty())) {
                int cnt = map.get(done);
                map.replace(done, cnt, cnt + 1);
            } else {
                int cnt = map.get(undone);
                map.replace(undone, cnt, cnt + 1);
            }
        }
        List<Map.Entry<String, Integer>> list1 = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        List<String> statusList = new ArrayList<String>();
        List<Integer> countList = new ArrayList<Integer>();
        for (Map.Entry<String, Integer> m : list1) {
            statusList.add(m.getKey());
            countList.add(m.getValue());
        }
        System.out.println(statusList.toString());
        System.out.println(countList.toString());
        mmap.put("statusList", statusList);
        mmap.put("countList", countList);
    }

    /**
     * 打印实名认证列表
     */
    @PostMapping("/printToHtml")
    @ResponseBody
    public TableDataInfo printToHtml(Certification certification) {
        List<Certification> list = certificationService.selectCertificationList(certification);
        return getDataTable(list);
    }

    /**
     * 打印实名认证列表
     */
    @RequestMapping("/print")
    public String print() {
        return prefix + "/print";
    }

    /**
     * 导出实名认证列表
     */
    @RequiresPermissions("system:certification:export")
    @Log(title = "实名认证", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Certification certification) {
        List<Certification> list = certificationService.selectCertificationList(certification);
        ExcelUtil<Certification> util = new ExcelUtil<Certification>(Certification.class);
        return util.exportExcel(list, "实名认证数据");
    }

    /**
     * 新增实名认证
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存实名认证
     */
    @RequiresPermissions("system:certification:add")
    @Log(title = "实名认证", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Certification certification) {
        return toAjax(certificationService.insertCertification(certification));
    }

    /**
     * 修改实名认证
     */
    @RequiresPermissions("system:certification:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Certification certification = certificationService.selectCertificationById(id);
        mmap.put("certification", certification);
        return prefix + "/edit";
    }

    /**
     * 修改保存实名认证
     */
    @RequiresPermissions("system:certification:edit")
    @Log(title = "实名认证", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Certification certification) {
        return toAjax(certificationService.updateCertification(certification));
    }

    /**
     * 删除实名认证
     */
    @RequiresPermissions("system:certification:remove")
    @Log(title = "实名认证", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(certificationService.deleteCertificationByIds(ids));
    }
}
