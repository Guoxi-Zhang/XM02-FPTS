package com.fpts.weathers.service;

import com.fpts.weathers.domain.WeatherStatistics;

import java.util.List;

public interface IWeatherStatisticsService {
    /**
     * 查询统计表
     *
     * @return 结果
     */
    public List<WeatherStatistics> searchWeatherStatistics();
}
