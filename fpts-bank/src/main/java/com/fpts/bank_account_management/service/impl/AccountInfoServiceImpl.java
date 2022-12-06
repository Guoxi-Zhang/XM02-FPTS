package com.fpts.bank_account_management.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.bank_account_management.mapper.AccountInfoMapper;
import com.fpts.bank_account_management.domain.AccountInfo;
import com.fpts.bank_account_management.service.IAccountInfoService;
import com.fpts.common.core.text.Convert;

/**
 * 银行账户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-12-07
 */
@Service
public class AccountInfoServiceImpl implements IAccountInfoService 
{
    @Autowired
    private AccountInfoMapper accountInfoMapper;

    /**
     * 查询银行账户管理
     * 
     * @param userId 银行账户管理主键
     * @return 银行账户管理
     */
    @Override
    public AccountInfo selectAccountInfoByUserId(Long userId)
    {
        return accountInfoMapper.selectAccountInfoByUserId(userId);
    }

    /**
     * 查询银行账户管理列表
     * 
     * @param accountInfo 银行账户管理
     * @return 银行账户管理
     */
    @Override
    public List<AccountInfo> selectAccountInfoList(AccountInfo accountInfo)
    {
        return accountInfoMapper.selectAccountInfoList(accountInfo);
    }

    /**
     * 新增银行账户管理
     * 
     * @param accountInfo 银行账户管理
     * @return 结果
     */
    @Override
    public int insertAccountInfo(AccountInfo accountInfo)
    {
        return accountInfoMapper.insertAccountInfo(accountInfo);
    }

    /**
     * 修改银行账户管理
     * 
     * @param accountInfo 银行账户管理
     * @return 结果
     */
    @Override
    public int updateAccountInfo(AccountInfo accountInfo)
    {
        return accountInfoMapper.updateAccountInfo(accountInfo);
    }

    /**
     * 批量删除银行账户管理
     * 
     * @param userIds 需要删除的银行账户管理主键
     * @return 结果
     */
    @Override
    public int deleteAccountInfoByUserIds(String userIds)
    {
        return accountInfoMapper.deleteAccountInfoByUserIds(Convert.toStrArray(userIds));
    }

    /**
     * 删除银行账户管理信息
     * 
     * @param userId 银行账户管理主键
     * @return 结果
     */
    @Override
    public int deleteAccountInfoByUserId(Long userId)
    {
        return accountInfoMapper.deleteAccountInfoByUserId(userId);
    }
}
