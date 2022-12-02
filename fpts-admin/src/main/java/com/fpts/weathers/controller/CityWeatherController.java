package com.fpts.weathers.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.fpts.weathers.domain.WeatherStatistics;
import com.fpts.weathers.service.IWeatherStatisticsService;
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
import com.fpts.weathers.domain.CityWeather;
import com.fpts.weathers.service.ICityWeatherService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

/**
 * 天气管理Controller
 * 
 * @author Guoxi Zhang
 * @date 2022-11-15
 */
@Controller
@RequestMapping("/weathers/cityWeathers")
public class CityWeatherController extends BaseController
{
    private String prefix = "weathers/cityWeathers";

    private CityWeather cityWeather;

    @Autowired
    private ICityWeatherService cityWeatherService;

    @Autowired
    private IWeatherStatisticsService weatherStatisticsService;

    @RequiresPermissions("weathers:cityWeathers:view")
    @GetMapping()
    public String cityWeathers()
    {
        return prefix + "/cityWeathers";
    }

    /**
     * 查询天气管理列表
     */
    @RequiresPermissions("weathers:cityWeathers:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CityWeather cityWeather)
    {
        startPage();
        List<CityWeather> list = cityWeatherService.selectCityWeatherList(cityWeather);

        return getDataTable(list);
    }



    /**
     * 导出天气管理列表
     */
    @RequiresPermissions("weathers:cityWeathers:export")
    @Log(title = "天气管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CityWeather cityWeather)
    {
        List<CityWeather> list = cityWeatherService.selectCityWeatherList(cityWeather);
        ExcelUtil<CityWeather> util = new ExcelUtil<CityWeather>(CityWeather.class);
        return util.exportExcel(list, "天气管理数据");
    }

    /**
     * 新增天气管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存天气管理
     */
    @RequiresPermissions("weathers:cityWeathers:add")
    @Log(title = "天气管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CityWeather cityWeather)
    {
        return toAjax(cityWeatherService.insertCityWeather(cityWeather));
    }

    /**
     * 修改天气管理
     */
    @RequiresPermissions("weathers:cityWeathers:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CityWeather cityWeather = cityWeatherService.selectCityWeatherById(id);
        mmap.put("cityWeather", cityWeather);
        return prefix + "/edit";
    }

    /**
     * 修改保存天气管理
     */
    @RequiresPermissions("weathers:cityWeathers:edit")
    @Log(title = "天气管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CityWeather cityWeather)
    {
        return toAjax(cityWeatherService.updateCityWeather(cityWeather));
    }

    /**
     * 删除天气管理
     */
    @RequiresPermissions("weathers:cityWeathers:remove")
    @Log(title = "天气管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cityWeatherService.deleteCityWeatherByIds(ids));
    }

    /**
     * 统计报表
     */
//    @PostMapping( "/chart")
    @RequestMapping("/chart")
    public String showChart(ModelMap mmap){
        List<WeatherStatistics> weatherStatisticsList = weatherStatisticsService.searchWeatherStatistics();
        List<String> cityList = new ArrayList<String>();
        List<Integer> cityCntList = new ArrayList<Integer>();
        for(WeatherStatistics w: weatherStatisticsList){
            cityList.add(w.getCity());
            cityCntList.add(w.getCityCnt());
        }
        mmap.put("cityList", cityList);
        mmap.put("cityCntList", cityCntList);
        return prefix + "/chart";
    }

    /**
     * 打印跳转
     */
    @RequestMapping("/print")
    public String print(){

        return prefix + "/print";
    }

    /**
     * 打印操作
     */
    @PostMapping("/printToHtml")
    @ResponseBody
    public TableDataInfo printToHtml(CityWeather cityWeather)
    {
//        startPage();
        List<CityWeather> list = cityWeatherService.selectCityWeatherList(cityWeather);

        return getDataTable(list);
    }

    /**
     * 查询特定天气
     */
    @PostMapping("/search/{city}")
    @ResponseBody
    public CityWeather searchWeather( @PathVariable("city") String cityName) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar =  Calendar.getInstance();
        List<Map<String, String>> listData = WeatherUtil.getWeatherMap(cityName);
        Map<String, String> map = listData.get(0);
        System.out.println(map.toString());
        cityWeather = new CityWeather(cityName, map.get("text_day"),map.get("text_night"),map.get("low"),map.get("high"),map.get("wind_direction"),map.get("wind_scale"));

        return cityWeather;
    }

    /**
     * 保存该天气
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult saveWeatherRecord(){
        return toAjax(cityWeatherService.insertCityWeather(cityWeather));
    }


}
