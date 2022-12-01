package com.fpts.weathers.mapper;

import com.fpts.weathers.domain.WeatherStatistics;

import java.util.List;

public interface WeatherStatisticsMapper {

    /**
     * 查询统计表
     *
     * @return 结果
     */
    public List<WeatherStatistics> searchWeatherStatistics();
}
