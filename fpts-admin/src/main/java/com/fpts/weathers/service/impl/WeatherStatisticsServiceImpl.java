package com.fpts.weathers.service.impl;

import com.fpts.weathers.domain.WeatherStatistics;
import com.fpts.weathers.mapper.WeatherStatisticsMapper;
import com.fpts.weathers.service.IWeatherStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherStatisticsServiceImpl implements IWeatherStatisticsService {
    @Autowired
    WeatherStatisticsMapper weatherStatisticsMapper;

    /**
     * 查询统计表
     *
     * @return 结果
     */
    @Override
    public List<WeatherStatistics> searchWeatherStatistics(){
        return weatherStatisticsMapper.searchWeatherStatistics();
    };
}
