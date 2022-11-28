package com.fpts.assets.service.impl;

import java.util.List;
import com.fpts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.assets.mapper.AccountAssetsMapper;
import com.fpts.assets.domain.AccountAssets;
import com.fpts.assets.service.IAccountAssetsService;
import com.fpts.common.core.text.Convert;

/**
 * 查看资产Service业务层处理
 * 
 * @author lzy
 * @date 2022-11-27
 */
@Service
public class AccountAssetsServiceImpl implements IAccountAssetsService 
{
    @Autowired
    private AccountAssetsMapper accountAssetsMapper;

    /**
     * 查询查看资产
     * 
     * @param No 查看资产主键
     * @return 查看资产
     */
    @Override
    public AccountAssets selectAccountAssetsByNo(Integer No)
    {
        return accountAssetsMapper.selectAccountAssetsByNo(No);
    }

    /**
     * 查询查看资产列表
     * 
     * @param accountAssets 查看资产
     * @return 查看资产
     */
    @Override
    public List<AccountAssets> selectAccountAssetsList(AccountAssets accountAssets)
    {
        return accountAssetsMapper.selectAccountAssetsList(accountAssets);
    }

    /**
     * 新增查看资产
     * 
     * @param accountAssets 查看资产
     * @return 结果
     */
    @Override
    public int insertAccountAssets(AccountAssets accountAssets)
    {
        accountAssets.setCreateTime(DateUtils.getNowDate());
        return accountAssetsMapper.insertAccountAssets(accountAssets);
    }

    /**
     * 修改查看资产
     * 
     * @param accountAssets 查看资产
     * @return 结果
     */
    @Override
    public int updateAccountAssets(AccountAssets accountAssets)
    {
        return accountAssetsMapper.updateAccountAssets(accountAssets);
    }

    /**
     * 批量删除查看资产
     * 
     * @param Nos 需要删除的查看资产主键
     * @return 结果
     */
    @Override
    public int deleteAccountAssetsByNos(String Nos)
    {
        return accountAssetsMapper.deleteAccountAssetsByNos(Convert.toStrArray(Nos));
    }

    /**
     * 删除查看资产信息
     * 
     * @param No 查看资产主键
     * @return 结果
     */
    @Override
    public int deleteAccountAssetsByNo(Integer No)
    {
        return accountAssetsMapper.deleteAccountAssetsByNo(No);
    }
}
