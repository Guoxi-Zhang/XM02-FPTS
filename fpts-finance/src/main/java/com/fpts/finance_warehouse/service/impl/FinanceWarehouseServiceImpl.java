package com.fpts.finance_warehouse.service.impl;

import java.util.List;
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
     * 查询数据仓库列表
     * 
     * @param financeWarehouse 数据仓库
     * @return 数据仓库
     */
    @Override
    public List<FinanceWarehouse> selectFinanceWarehouseList(FinanceWarehouse financeWarehouse)
    {
        return financeWarehouseMapper.selectFinanceWarehouseList(financeWarehouse);
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
