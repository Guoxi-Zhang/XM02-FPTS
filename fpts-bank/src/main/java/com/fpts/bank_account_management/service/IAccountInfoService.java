package com.fpts.bank_account_management.service;

import java.util.List;
import com.fpts.bank_account_management.domain.AccountInfo;

/**
 * 银行账户管理Service接口
 * 
 * @author ruoyi
 * @date 2022-12-07
 */
public interface IAccountInfoService 
{
    /**
     * 查询银行账户管理
     * 
     * @param userId 银行账户管理主键
     * @return 银行账户管理
     */
    public AccountInfo selectAccountInfoByUserId(Long userId);

    /**
     * 查询银行账户管理列表
     * 
     * @param accountInfo 银行账户管理
     * @return 银行账户管理集合
     */
    public List<AccountInfo> selectAccountInfoList(AccountInfo accountInfo);

    /**
     * 新增银行账户管理
     * 
     * @param accountInfo 银行账户管理
     * @return 结果
     */
    public int insertAccountInfo(AccountInfo accountInfo);

    /**
     * 修改银行账户管理
     * 
     * @param accountInfo 银行账户管理
     * @return 结果
     */
    public int updateAccountInfo(AccountInfo accountInfo);

    /**
     * 批量删除银行账户管理
     * 
     * @param userIds 需要删除的银行账户管理主键集合
     * @return 结果
     */
    public int deleteAccountInfoByUserIds(String userIds);

    /**
     * 删除银行账户管理信息
     * 
     * @param userId 银行账户管理主键
     * @return 结果
     */
    public int deleteAccountInfoByUserId(Long userId);
}
