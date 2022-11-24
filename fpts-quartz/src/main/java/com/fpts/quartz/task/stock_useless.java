package com.fpts.quartz.task;

import java.math.BigDecimal;

public class stock_useless {
    public String id,name;
    public char type;
    public BigDecimal new_price,open_price,yesterday_price;
    public BigDecimal max_price,min_price,increase,increase_rate,turnover_rate;


    @Override
    public String toString() {
        return "Stock{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", new_price='" + new_price + '\'' +
                ", open_price='" + open_price + '\'' +
                ", yesterday_price'" + yesterday_price + '\'' +
                ", max_price='" + max_price + '\'' +
                ", min_price='" + min_price + '\'' +
                ", increase='" + increase + '\'' +
                ", increase_rate='" + increase_rate + '\'' +
                ", turnover_rate" + turnover_rate +
                '}';
    }


}