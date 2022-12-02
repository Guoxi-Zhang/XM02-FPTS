package com.fpts.finance_warehouse.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import com.fpts.finance_query.domain.FinanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.finance_warehouse.mapper.FinanceWarehouseMapper;
import com.fpts.finance_warehouse.domain.FinanceWarehouse;
import com.fpts.finance_warehouse.service.IFinanceWarehouseService;
import com.fpts.common.core.text.Convert;

/**
 * 数据仓库Service业务层处理
 * 
 * @author laybxc
 * @date 2022-11-25
 */
@Service
public class FinanceWarehouseServiceImpl implements IFinanceWarehouseService 
{
    @Autowired
    private FinanceWarehouseMapper financeWarehouseMapper;

    /**
     * 查询数据仓库
     * 
     * @param id 数据仓库主键
     * @return 数据仓库
     */
    @Override
    public FinanceWarehouse selectFinanceWarehouseById(Integer id)
    {
        return financeWarehouseMapper.selectFinanceWarehouseById(id);
    }

    /**
     * 查询数据仓库2
     *
     * @param productId 数据仓库产品代码
     * @return 数据仓库
     */
    @Override
    public FinanceWarehouse selectFinanceWarehouseByProductId(String productId)
    {
        return financeWarehouseMapper.selectFinanceWarehouseByProductId(productId);
    }

    /**
     * 查询数据仓库列表
     * 
     * @param financeWarehouse 数据仓库
     * @return 数据仓库
     */
    @Override
    public List<FinanceWarehouse> selectFinanceWarehouseList(FinanceWarehouse financeWarehouse)
    {
        //return financeWarehouseMapper.selectFinanceWarehouseList(financeWarehouse);
        List<FinanceWarehouse> list = financeWarehouseMapper.selectFinanceWarehouseList(financeWarehouse);
        DecimalFormat df = new DecimalFormat("#.00");
        for(FinanceWarehouse f: list){
            if(null!=f.getNewPrice()) f.setNewPrice(Double.parseDouble(df.format(f.getNewPrice())));
            if(null!=f.getOpenPrice()) f.setOpenPrice(Double.parseDouble(df.format(f.getOpenPrice())));
            if(null!=f.getYesterdayPrice()) f.setYesterdayPrice(Double.parseDouble(df.format(f.getYesterdayPrice())));
            if(null!=f.getMaxPrice()) f.setMaxPrice(Double.parseDouble(df.format(f.getMaxPrice())));
            if(null!=f.getMinPrice()) f.setMinPrice(Double.parseDouble(df.format(f.getMinPrice())));
            if(null!=f.getIncrease()) f.setIncrease(Double.parseDouble(df.format(f.getIncrease())));
            if(null!=f.getIncreaseRate()) f.setIncreaseRate(Double.parseDouble(df.format(f.getIncreaseRate())));
            if(null!=f.getTurnoverRate()) f.setTurnoverRate(Double.parseDouble(df.format(f.getTurnoverRate())));
        }
        return list;
    }

    /**
     * 新增数据仓库
     * 
     * @param financeWarehouse 数据仓库
     * @return 结果
     */
    @Override
    public int insertFinanceWarehouse(FinanceWarehouse financeWarehouse)
    {
        return financeWarehouseMapper.insertFinanceWarehouse(financeWarehouse);
    }

    /**
     * 修改数据仓库
     * 
     * @param financeWarehouse 数据仓库
     * @return 结果
     */
    @Override
    public int updateFinanceWarehouse(FinanceWarehouse financeWarehouse)
    {
        return financeWarehouseMapper.updateFinanceWarehouse(financeWarehouse);
    }

    @Override
    public int updateFinanceWarehouse2(FinanceWarehouse financeWarehouse)
    {
        return financeWarehouseMapper.updateFinanceWarehouse2(financeWarehouse);
    }

    /**
     * 批量删除数据仓库
     * 
     * @param ids 需要删除的数据仓库主键
     * @return 结果
     */
    @Override
    public int deleteFinanceWarehouseByIds(String ids)
    {
        return financeWarehouseMapper.deleteFinanceWarehouseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除数据仓库信息
     * 
     * @param id 数据仓库主键
     * @return 结果
     */
    @Override
    public int deleteFinanceWarehouseById(Integer id)
    {
        return financeWarehouseMapper.deleteFinanceWarehouseById(id);
    }
}
