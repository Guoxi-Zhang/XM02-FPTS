package com.fpts.cockpitInterface;

import com.fpts.common.core.page.TableDataInfo;
import com.fpts.finance_query.domain.FinanceQuery;
import com.fpts.finance_query.service.IFinanceQueryService;
import com.fpts.framework.web.domain.server.Sys;
import com.fpts.system.domain.Certification;
import com.fpts.system.domain.SysNotice;
import com.fpts.system.service.ICertificationService;
import com.fpts.system.service.ISysNoticeService;
import com.fpts.system.service.impl.CertificationServiceImpl;
import com.fpts.todo.domain.TodoList;
import com.fpts.todo.service.ITodoListService;
import com.fpts.finance_forum.domain.FinanceForum;
import com.fpts.finance_forum.service.IFinanceForumService;
import com.fpts.system.domain.SysUserOnline;
import com.fpts.system.service.ISysUserOnlineService;
import com.fpts.weathers.domain.WeatherStatistics;
import com.fpts.weathers.service.IWeatherStatisticsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 可视化大屏幕Controller
 *
 * @author Guoxi Zhang & Yicheng Li & Chenglong Yu
 * @date 2022-12-09
 */
@Controller
@RequestMapping("/data")
public class CockpitInterfaceController {
    private String prefix = "data";

    @Autowired
    private ITodoListService todoListService;
    @Autowired
    private IFinanceForumService financeForumService;
    @Autowired
    private ISysUserOnlineService sysUserOnlineService;
    @Autowired
    private ISysNoticeService noticeService;
    @Autowired
    ICertificationService certificationService;
    @Autowired
    private IFinanceQueryService financeQueryService;

    @Autowired
    private IWeatherStatisticsService weatherStatisticsService;

    @GetMapping()
    public String cockpitInterface(ModelMap mmap, SysNotice notice) {
        //生成mmap
        weatherChart(mmap);
        todoListChart(mmap);
        forumChart(mmap);
        financeQueryChart(mmap);

        List<SysNotice> noticeList = noticeService.selectNoticeList(notice);
        mmap.put("notice", noticeList);

        certificationChart(mmap);
        //返回视图
        return prefix + "/cockpitInterface";
    }

    public void weatherChart(ModelMap mmap) {
        List<WeatherStatistics> weatherStatisticsList = weatherStatisticsService.searchWeatherStatistics();
        List<String> cityList = new ArrayList<String>();
        List<Integer> cityCntList = new ArrayList<Integer>();
        int totalCityNum = 0;
        for (WeatherStatistics w : weatherStatisticsList) {
            cityList.add(w.getCity());
            cityCntList.add(w.getCityCnt());
            totalCityNum += w.getCityCnt();
        }
        mmap.put("cityList", cityList);
        mmap.put("cityCntList", cityCntList);
        mmap.put("totalCityNum", totalCityNum);

    }

    public void todoListChart(ModelMap mmap) {
        List<TodoList> list = todoListService.selectTodoListList(new TodoList());
        for (TodoList t : list) {
            String detail = t.getDetail();
            System.out.println(detail);
            if (detail.contains("<p>")) {
                t.setDetail(t.getDetail().replace("<p>", ""));
                System.out.println(detail.replace("<p>", ""));
            }
            if (detail.contains("</p>")) {
                t.setDetail(t.getDetail().replace("</p>", ""));
            }
        }
        mmap.put("todoList", list);
    }

    public void financeQueryChart(ModelMap mmap){
        List<FinanceQuery> list = financeQueryService.selectFinanceQueryList(new FinanceQuery());
        Map<String, Double> map = new TreeMap<String, Double>();

        for(FinanceQuery f: list){
            String pId = f.getName();
            Double rate = f.getIncrease();
            //改这里，找涨幅最高的十只股票
            map.put(pId, rate);

        }

        List<Map.Entry<String, Double>> list1= new ArrayList<Map.Entry<String, Double>>(map.entrySet());
        Collections.sort(list1,new Comparator<Map.Entry<String, Double>>() {
            //降序排序
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        List<String> pIdList = new ArrayList<String>();   //= Arrays.asList(pIdSet);
        List<Double> incList = new ArrayList<Double>();
        int tot=0;

        for (Map.Entry<String, Double> a: list1) {
            tot++;
            if(tot==10) break;
            pIdList.add(a.getKey());
            incList.add(a.getValue());
        }

        System.out.println(pIdList.toString());
        System.out.println(incList.toString());
        mmap.put("pIdList", pIdList);
        mmap.put("incList", incList);
    }

    public void certificationChart(ModelMap mmap) {
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
        mmap.put("statusList", statusList);
        mmap.put("countList", countList);
    }
    public void forumChart(ModelMap mmap) {
        List<FinanceForum> list = financeForumService.selectFinanceForumList(new FinanceForum());

        for (FinanceForum f : list) {
            String content = f.getContent();
            System.out.println(content);
            if (content.contains("<p>")) {
                f.setContent(f.getContent().replace("<p>", ""));
                System.out.println(content.replace("<p>", ""));
            }
            if (content.contains("</p>")) {
                f.setContent(f.getContent().replace("</p>", ""));
            }
        }
        mmap.put("financeForum", list);
    }
}