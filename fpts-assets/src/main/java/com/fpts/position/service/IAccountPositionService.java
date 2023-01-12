package com.fpts.position.service;
import java.util.List;
import com.fpts.position.domain.AccountPosition;
/**
 * 账户持仓Service接口
 *
 * @author lzy
 * @date 2022-12-08
 */
public interface IAccountPositionService
{
    /**
     * 查询账户持仓
     *
     * @param no 账户持仓主键
     * @return 账户持仓
     */
    public AccountPosition selectAccountPositionByOrderId(Long no);
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
     * 批量删除账户持仓
     *
     * @param nos 需要删除的账户持仓主键集合
     * @return 结果
     */
    public int deleteAccountPositionByOrderIds(String nos);
    /**
     * 删除账户持仓信息
     *
     * @param no 账户持仓主键
     * @return 结果
     */
    public int deleteAccountPositionByOrderId(Long no);

    public List<Integer> getMonthlyData(Long userId);
}