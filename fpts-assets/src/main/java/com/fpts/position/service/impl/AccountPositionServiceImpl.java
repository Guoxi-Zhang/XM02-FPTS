package com.fpts.position.service.impl;

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
 * @date 2022-12-08
 */
@Service
public class AccountPositionServiceImpl implements IAccountPositionService 
{
    @Autowired
    private AccountPositionMapper accountPositionMapper;

    /**
     * 查询账户持仓
     * 
     * @param no 账户持仓主键
     * @return 账户持仓
     */
    @Override
    public AccountPosition selectAccountPositionByNo(Long no)
    {
        return accountPositionMapper.selectAccountPositionByNo(no);
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
     * @param nos 需要删除的账户持仓主键
     * @return 结果
     */
    @Override
    public int deleteAccountPositionByNos(String nos)
    {
        return accountPositionMapper.deleteAccountPositionByNos(Convert.toStrArray(nos));
    }

    /**
     * 删除账户持仓信息
     * 
     * @param no 账户持仓主键
     * @return 结果
     */
    @Override
    public int deleteAccountPositionByNo(Long no)
    {
        return accountPositionMapper.deleteAccountPositionByNo(no);
    }
}
