package com.fpts.quartz.task;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fpts.finance_warehouse.domain.FinanceWarehouse;
import com.fpts.finance_warehouse.service.impl.FinanceWarehouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("finGetFundDataTask")
//上面是重要引入，将这个Task类声明为bean，因为调用的那些service都是用bean注入的，反射的调用方式是没办法在非bean的对象里面注入bean的

public class FinGetFundDataTask {

    @Autowired
    FinanceWarehouseServiceImpl financeWarehouseService;

    public void GetData(){

        List<String> samples = new ArrayList<>();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String time=dateFormat.format(new Date());
        System.out.println(time);
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://47.108.114.204:8080/api/public/fund_etf_category_sina", String.class);

        JSONArray array = JSONArray.parseArray(forObject);

        FinanceWarehouse fund = new FinanceWarehouse();
        List<FinanceWarehouse> funds = new ArrayList<>();
        for(int i=0;i<array.size();i++){
            JSONObject object=(JSONObject)array.get(i);
            if(object.getString("代码")!=null){
                String product_id =  object.getString("代码");
                fund.setProductId(product_id);
                //System.out.println(product_id);
            }
            if(object.getString("名称")!=null){
                String name =  object.getString("名称"); //key参数为string
                fund.setName(name);
            }
            fund.setType("3");
            if(object.getString("最新价")!=null){
                double new_price=Double.valueOf(object.getString("最新价"));
                fund.setNewPrice(new_price);
            }
            if(object.getString("今开")!=null){
                double open_price=Double.valueOf(object.getString("今开"));
                fund.setOpenPrice(open_price);
            }
            if(object.getString("昨收")!=null){
                double yesterday_price=Double.valueOf(object.getString("昨收"));
                fund.setYesterdayPrice(yesterday_price);
            }
            if(object.getString("最高")!=null){
                double max_price=Double.valueOf(object.getString("最高"));
                fund.setMaxPrice(max_price);
            }
            if(object.getString("最低")!=null){
                double min_price=Double.valueOf(object.getString("最低"));
                fund.setMinPrice(min_price);
            }
            if(object.getString("涨跌幅")!=null){
                double increase=Double.valueOf(object.getString("涨跌幅"));
                fund.setIncrease(increase);
            }
            
//            System.out.println(fund.toString());
//            System.out.println(financeWarehouseService.toString());
            if(null!=fund){
                /*
                financeWarehouseService.insertFinanceWarehouse(fund);
                (old version)
                */
                FinanceWarehouse f = financeWarehouseService.selectFinanceWarehouseByProductId(fund.getProductId());
                if(null==f) financeWarehouseService.insertFinanceWarehouse(fund);
                else financeWarehouseService.updateFinanceWarehouse2(fund);
            }
            //funds.add(fund);

        }

    }
}
