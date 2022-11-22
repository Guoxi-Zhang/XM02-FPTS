package com.fpts.weathers.service.impl;

import java.util.List;
import com.fpts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.weathers.mapper.CityWeatherMapper;
import com.fpts.weathers.domain.CityWeather;
import com.fpts.weathers.service.ICityWeatherService;
import com.fpts.common.core.text.Convert;

/**
 * 天气管理Service业务层处理
 * 
 * @author Guoxi Zhang
 * @date 2022-11-15
 */
@Service
public class CityWeatherServiceImpl implements ICityWeatherService 
{
    @Autowired
    private CityWeatherMapper cityWeatherMapper;

    /**
     * 查询天气管理
     * 
     * @param id 天气管理主键
     * @return 天气管理
     */
    @Override
    public CityWeather selectCityWeatherById(Long id)
    {
        return cityWeatherMapper.selectCityWeatherById(id);
    }

    /**
     * 查询天气管理列表
     * 
     * @param cityWeather 天气管理
     * @return 天气管理
     */
    @Override
    public List<CityWeather> selectCityWeatherList(CityWeather cityWeather)
    {
        return cityWeatherMapper.selectCityWeatherList(cityWeather);
    }

    /**
     * 新增天气管理
     * 
     * @param cityWeather 天气管理
     * @return 结果
     */
    @Override
    public int insertCityWeather(CityWeather cityWeather)
    {
        cityWeather.setCreateTime(DateUtils.getNowDate());
        return cityWeatherMapper.insertCityWeather(cityWeather);
    }

    /**
     * 修改天气管理
     * 
     * @param cityWeather 天气管理
     * @return 结果
     */
    @Override
    public int updateCityWeather(CityWeather cityWeather)
    {
        return cityWeatherMapper.updateCityWeather(cityWeather);
    }

    /**
     * 批量删除天气管理
     * 
     * @param ids 需要删除的天气管理主键
     * @return 结果
     */
    @Override
    public int deleteCityWeatherByIds(String ids)
    {
        return cityWeatherMapper.deleteCityWeatherByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除天气管理信息
     * 
     * @param id 天气管理主键
     * @return 结果
     */
    @Override
    public int deleteCityWeatherById(Long id)
    {
        return cityWeatherMapper.deleteCityWeatherById(id);
    }
}
