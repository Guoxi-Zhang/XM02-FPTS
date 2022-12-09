package com.fpts.assets.mapper;

import java.util.List;
import com.fpts.assets.domain.AccountAssets;

/**
 * 查看资产Mapper接口
 * 
 * @author lzy
 * @date 2022-12-10
 */
public interface AccountAssetsMapper 
{
    /**
     * 查询查看资产
     * 
     * @param no 查看资产主键
     * @return 查看资产
     */
    public AccountAssets selectAccountAssetsByNo(Long no);

    /**
     * 查询查看资产列表
     * 
     * @param accountAssets 查看资产
     * @return 查看资产集合
     */
    public List<AccountAssets> selectAccountAssetsList(AccountAssets accountAssets);

    /**
     * 新增查看资产
     * 
     * @param accountAssets 查看资产
     * @return 结果
     */
    public int insertAccountAssets(AccountAssets accountAssets);

    /**
     * 修改查看资产
     * 
     * @param accountAssets 查看资产
     * @return 结果
     */
    public int updateAccountAssets(AccountAssets accountAssets);

    /**
     * 删除查看资产
     * 
     * @param no 查看资产主键
     * @return 结果
     */
    public int deleteAccountAssetsByNo(Long no);

    /**
     * 批量删除查看资产
     * 
     * @param nos 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAccountAssetsByNos(String[] nos);
}
