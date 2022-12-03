package com.fpts.record.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * java调用中央气象局天气预报接口
 *
 * @author Guoxi Zhang
 *
 */
public class TransactionChartController {

    public static JSONArray getJSON(String cityName) throws IOException {
        URL url = new URL("local:80/list");
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
            String data = sb.toString();
            System.out.println(data);
            JSONObject jsonData = JSONObject.parseObject(data).getJSONArray("results").getJSONObject(0);
            JSONArray weatherInfo = jsonData.getJSONArray("daily");
            JSONObject locationInfo = jsonData.getJSONObject("location");
            return weatherInfo;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Map<String, String>> getWeatherMap(String cityName) throws IOException, NullPointerException {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 3; i++) {
            // 得到未来3天的日期
            JSONObject info = Objects.requireNonNull(getJSON(cityName)).getJSONObject(i);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, i - 1);
            Date date = cal.getTime();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
            Map<String, String> map = new HashMap<String, String>();
            map.put("date", info.getString("date").toString());// 日期
            map.put("text_day", info.getString("text_day").toString());// 天气
            map.put("text_night", info.getString("text_night").toString());// 夜间天气
            map.put("high", info.getString("high").toString());// 温度
            map.put("low", info.getString("low").toString());// 温度
            map.put("wind_direction", info.getString("wind_direction").toString());// 风况
            map.put("wind_scale", info.getString("wind_scale").toString());// 风速

            list.add(map);
        }
        return list;

    }

    public static void main(String[] args) {
        try {
//
            //测试获取未来3天天气
            List<Map<String, String>> listData = getWeatherMap("成都");
            for (Map<String, String> wMap : listData) {
                System.out.println(wMap.get("date") + "\t" + wMap.get("text_day")
                        + "\t" + wMap.get("wind_direction") + "\t" + wMap.get("wind_speed")
                        + "\t" + wMap.get("high") + "\t" + wMap.get("low"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

