package com.fpts.weathers.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 天气管理对象 city_weather
 * 
 * @author Guoxi Zhang
 * @date 2022-11-15
 */
public class CityWeather extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long id;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 日间天气 */
    @Excel(name = "日间天气")
    private String weatherDay;

    /** 夜间天气 */
    @Excel(name = "夜间天气")
    private String weatherNight;

    /** 最低气温 */
    @Excel(name = "最低气温")
    private String temperatureLow;

    /** 最高气温 */
    @Excel(name = "最高气温")
    private String temperatureHigh;

    /** 风向 */
    @Excel(name = "风向")
    private String windDirection;

    /** 风力等级 */
    @Excel(name = "风力等级")
    private String windScale;

    public CityWeather(String city, String weather_day, String weather_night, String temperature_low, String temperature_high, String wind_direction, String wind_scale) {
        super();
        this.city = city;
        this.weatherDay = weather_day;
        this.weatherNight = weather_night;
        this.temperatureLow = temperature_low;
        this.temperatureHigh = temperature_high;
        this.windDirection = wind_direction;
        this.windScale = wind_scale;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setWeatherDay(String weatherDay) 
    {
        this.weatherDay = weatherDay;
    }

    public String getWeatherDay() 
    {
        return weatherDay;
    }
    public void setWeatherNight(String weatherNight) 
    {
        this.weatherNight = weatherNight;
    }

    public String getWeatherNight() 
    {
        return weatherNight;
    }
    public void setTemperatureLow(String temperatureLow) 
    {
        this.temperatureLow = temperatureLow;
    }

    public String getTemperatureLow() 
    {
        return temperatureLow;
    }
    public void setTemperatureHigh(String temperatureHigh) 
    {
        this.temperatureHigh = temperatureHigh;
    }

    public String getTemperatureHigh() 
    {
        return temperatureHigh;
    }
    public void setWindDirection(String windDirection) 
    {
        this.windDirection = windDirection;
    }

    public String getWindDirection() 
    {
        return windDirection;
    }
    public void setWindScale(String windScale) 
    {
        this.windScale = windScale;
    }

    public String getWindScale() 
    {
        return windScale;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("city", getCity())
            .append("weatherDay", getWeatherDay())
            .append("weatherNight", getWeatherNight())
            .append("temperatureLow", getTemperatureLow())
            .append("temperatureHigh", getTemperatureHigh())
            .append("windDirection", getWindDirection())
            .append("windScale", getWindScale())
            .append("createTime", getCreateTime())
            .toString();
    }
}
