package com.fpts.system.service.impl;

import java.util.List;
import com.fpts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.system.mapper.SysPasswordResetMapper;
import com.fpts.system.domain.SysPasswordReset;
import com.fpts.system.service.ISysPasswordResetService;
import com.fpts.common.core.text.Convert;

/**
 * 重置密码请求的数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-09
 */
@Service
public class SysPasswordResetServiceImpl implements ISysPasswordResetService
{
    @Autowired
    private SysPasswordResetMapper passwordResetMapper;

    /**
     * 查询重置密码请求的数据
     * 
     * @param id 重置密码请求的数据主键
     * @return 重置密码请求的数据
     */
    @Override
    public SysPasswordReset selectPasswordResetById(Long id)
    {
        return passwordResetMapper.selectPasswordResetById(id);
    }

    /**
     * 查询重置密码请求的数据列表
     * 
     * @param passwordReset 重置密码请求的数据
     * @return 重置密码请求的数据
     */
    @Override
    public List<SysPasswordReset> selectPasswordResetList(SysPasswordReset passwordReset)
    {
        return passwordResetMapper.selectPasswordResetList(passwordReset);
    }

    /**
     * 新增重置密码请求的数据
     * 
     * @param passwordReset 重置密码请求的数据
     * @return 结果
     */
    @Override
    public int insertPasswordReset(SysPasswordReset passwordReset)
    {
        passwordReset.setCreateTime(DateUtils.getNowDate());
        return passwordResetMapper.insertPasswordReset(passwordReset);
    }

    /**
     * 修改重置密码请求的数据
     * 
     * @param passwordReset 重置密码请求的数据
     * @return 结果
     */
    @Override
    public int updatePasswordReset(SysPasswordReset passwordReset)
    {
        return passwordResetMapper.updatePasswordReset(passwordReset);
    }

    /**
     * 批量删除重置密码请求的数据
     * 
     * @param ids 需要删除的重置密码请求的数据主键
     * @return 结果
     */
    @Override
    public int deletePasswordResetByIds(String ids)
    {
        return passwordResetMapper.deletePasswordResetByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除重置密码请求的数据信息
     * 
     * @param id 重置密码请求的数据主键
     * @return 结果
     */
    @Override
    public int deletePasswordResetById(Long id)
    {
        return passwordResetMapper.deletePasswordResetById(id);
    }
}
