package com.fpts.position.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.position.mapper.AccountPositionMapper;
import com.fpts.position.domain.AccountPosition;
import com.fpts.position.service.IAccountPositionService;
import com.fpts.common.core.text.Convert;

/**
 * 账户持仓Service业务层处理
 *
 * @author lzy
 * @date 2022-12-12
 */
@Service
public class AccountPositionServiceImpl implements IAccountPositionService
{
    @Autowired
    private AccountPositionMapper accountPositionMapper;

    /**
     * 查询账户持仓
     *
     * @param orderId 账户持仓主键
     * @return 账户持仓
     */
    @Override
    public AccountPosition selectAccountPositionByOrderId(Long orderId)
    {
        return accountPositionMapper.selectAccountPositionByOrderId(orderId);
    }

    /**
     * 查询账户持仓列表
     *
     * @param accountPosition 账户持仓
     * @return 账户持仓
     */
    @Override
    public List<AccountPosition> selectAccountPositionList(AccountPosition accountPosition)
    {
        return accountPositionMapper.selectAccountPositionList(accountPosition);
    }

    /**
     * 新增账户持仓
     *
     * @param accountPosition 账户持仓
     * @return 结果
     */
    @Override
    public int insertAccountPosition(AccountPosition accountPosition)
    {
        return accountPositionMapper.insertAccountPosition(accountPosition);
    }

    /**
     * 修改账户持仓
     *
     * @param accountPosition 账户持仓
     * @return 结果
     */
    @Override
    public int updateAccountPosition(AccountPosition accountPosition)
    {
        return accountPositionMapper.updateAccountPosition(accountPosition);
    }

    /**
     * 批量删除账户持仓
     *
     * @param orderIds 需要删除的账户持仓主键
     * @return 结果
     */
    @Override
    public int deleteAccountPositionByOrderIds(String orderIds)
    {
        return accountPositionMapper.deleteAccountPositionByOrderIds(Convert.toStrArray(orderIds));
    }

    /**
     * 删除账户持仓信息
     *
     * @param orderId 账户持仓主键
     * @return 结果
     */
    @Override
    public int deleteAccountPositionByOrderId(Long orderId)
    {
        return accountPositionMapper.deleteAccountPositionByOrderId(orderId);
    }

    //统计
    @Override
    public List<Integer> getMonthlyData() {
        int len = 12;
        List<Integer> list = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            list.add(0);
        }

        Calendar c = Calendar.getInstance();
        List<AccountPosition> accountPositions = accountPositionMapper.selectAccountPositionList(new AccountPosition());
        for (AccountPosition item:accountPositions) {
            if ( !"".equals(item.getAccountId())) {
                c.setTime(item.getOrderTime());
                int idx = c.get(Calendar.MONTH);
                int cur = list.get(idx);
                list.set(idx, 1 + cur);
            }
        }
        return list;
    }
}
