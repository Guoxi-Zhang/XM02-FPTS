package com.fpts.system.mapper;

import java.util.List;
import com.fpts.system.domain.SysPasswordReset;

/**
 * 重置密码请求的数据Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-09
 */
public interface SysPasswordResetMapper
{
    /**
     * 查询重置密码请求的数据
     * 
     * @param id 重置密码请求的数据主键
     * @return 重置密码请求的数据
     */
    public SysPasswordReset selectPasswordResetById(Long id);

    /**
     * 查询重置密码请求的数据列表
     * 
     * @param passwordReset 重置密码请求的数据
     * @return 重置密码请求的数据集合
     */
    public List<SysPasswordReset> selectPasswordResetList(SysPasswordReset passwordReset);

    /**
     * 新增重置密码请求的数据
     * 
     * @param passwordReset 重置密码请求的数据
     * @return 结果
     */
    public int insertPasswordReset(SysPasswordReset passwordReset);

    /**
     * 修改重置密码请求的数据
     * 
     * @param passwordReset 重置密码请求的数据
     * @return 结果
     */
    public int updatePasswordReset(SysPasswordReset passwordReset);

    /**
     * 删除重置密码请求的数据
     * 
     * @param id 重置密码请求的数据主键
     * @return 结果
     */
    public int deletePasswordResetById(Long id);

    /**
     * 批量删除重置密码请求的数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePasswordResetByIds(String[] ids);
}
