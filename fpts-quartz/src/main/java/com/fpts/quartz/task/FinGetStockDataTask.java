package com.fpts.quartz.task;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fpts.finance_warehouse.domain.FinanceWarehouse;
import com.fpts.finance_warehouse.service.IFinanceWarehouseService;
import com.fpts.finance_warehouse.service.impl.FinanceWarehouseServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.fpts.finance_warehouse")
@MapperScan("com.fpts.finance_warehouse.mapper")
public class FinGetStockDataTask {

    @Autowired
    FinanceWarehouseServiceImpl financeWarehouseService;

    @RequiresPermissions("finance_warehouse:finance_warehouse:add")

    public void GetData(){

        List<String> samples = new ArrayList<>();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String time=dateFormat.format(new Date());
        System.out.println(time);
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://47.108.114.204:8080/api/public/stock_zh_a_spot_em", String.class);

        JSONArray array = JSONArray.parseArray(forObject);
        //System.out.println(array);      //到这里是对的

        FinanceWarehouse stock = new FinanceWarehouse();
        List<FinanceWarehouse> stocks = new ArrayList<>();
        for(int i=0;i<array.size();i++){
            JSONObject object=(JSONObject)array.get(i);
            if(object.getString("代码")!=null){
                String id =  object.getString("代码");
                stock.setId(id);
                System.out.println(id);
            }
            if(object.getString("名称")!=null){
                String name =  object.getString("名称"); //key参数为string
                stock.setName(name);
            }
            stock.setType("0");
            if(object.getString("最新价")!=null){
                double new_price=Double.valueOf(object.getString("最新价"));
                stock.setNewPrice(new_price);
            }
            if(object.getString("今开")!=null){
                double open_price=Double.valueOf(object.getString("今开"));
                stock.setOpenPrice(open_price);
            }
            if(object.getString("昨收")!=null){
                double yesterday_price=Double.valueOf(object.getString("昨收"));
                stock.setYesterdayPrice(yesterday_price);
            }
            if(object.getString("最高")!=null){
                double max_price=Double.valueOf(object.getString("最高"));
                stock.setMaxPrice(max_price);
            }
            if(object.getString("最低")!=null){
                double min_price=Double.valueOf(object.getString("最低"));
                stock.setMinPrice(min_price);
            }
            if(object.getString("涨跌幅")!=null){
                double increase=Double.valueOf(object.getString("涨跌幅"));
                stock.setIncrease(increase);
            }
            if(object.getString("涨速")!=null){
                double increase_rate=Double.valueOf(object.getString("涨速"));
                stock.setIncreaseRate(increase_rate);
            }
            if(object.getString("换手率")!=null){
                double turnover_rate=Double.valueOf(object.getString("换手率"));
                stock.setTurnoverRate(turnover_rate);
            }
            System.out.println(stock.toString());
            System.out.println(financeWarehouseService.toString());
            if(stock!=null){
                financeWarehouseService.insertFinanceWarehouse(stock);
            }
            //stocks.add(stock);

        }

    }
}
