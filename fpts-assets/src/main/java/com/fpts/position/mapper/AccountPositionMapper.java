package com.fpts.position.mapper;

import java.util.List;
import com.fpts.position.domain.AccountPosition;

/**
 * 账户持仓Mapper接口
 *
 * @author lzy
 * @date 2022-12-12
 */
public interface AccountPositionMapper
{
    /**
     * 查询账户持仓
     *
     * @param orderId 账户持仓主键
     * @return 账户持仓
     */
    public AccountPosition selectAccountPositionByOrderId(Long orderId);

    /**
     * 查询账户持仓列表
     *
     * @param accountPosition 账户持仓
     * @return 账户持仓集合
     */
    public List<AccountPosition> selectAccountPositionList(AccountPosition accountPosition);

    /**
     * 新增账户持仓
     *
     * @param accountPosition 账户持仓
     * @return 结果
     */
    public int insertAccountPosition(AccountPosition accountPosition);

    /**
     * 修改账户持仓
     *
     * @param accountPosition 账户持仓
     * @return 结果
     */
    public int updateAccountPosition(AccountPosition accountPosition);

    /**
     * 删除账户持仓
     *
     * @param orderId 账户持仓主键
     * @return 结果
     */
    public int deleteAccountPositionByOrderId(Long orderId);

    /**
     * 批量删除账户持仓
     *
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAccountPositionByOrderIds(String[] orderIds);
}
