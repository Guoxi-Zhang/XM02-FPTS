package com.fpts.assets.mapper;

import java.util.List;
import com.fpts.assets.domain.AccountAssets;

/**
 * 查看资产Mapper接口
 * 
 * @author lzy
 * @date 2022-11-27
 */
public interface AccountAssetsMapper 
{
    /**
     * 查询查看资产
     * 
     * @param No 查看资产主键
     * @return 查看资产
     */
    public AccountAssets selectAccountAssetsByNo(Integer No);

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
     * @param No 查看资产主键
     * @return 结果
     */
    public int deleteAccountAssetsByNo(Integer No);

    /**
     * 批量删除查看资产
     * 
     * @param Nos 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAccountAssetsByNos(String[] Nos);
}
