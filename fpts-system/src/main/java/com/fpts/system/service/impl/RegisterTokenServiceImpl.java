package com.fpts.system.service.impl;

import java.util.List;
import com.fpts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.system.mapper.RegisterTokenMapper;
import com.fpts.system.domain.RegisterToken;
import com.fpts.system.service.IRegisterTokenService;
import com.fpts.common.core.text.Convert;

/**
 * 注册令牌数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-13
 */
@Service
public class RegisterTokenServiceImpl implements IRegisterTokenService 
{
    @Autowired
    private RegisterTokenMapper registerTokenMapper;

    /**
     * 查询注册令牌数据
     * 
     * @param id 注册令牌数据主键
     * @return 注册令牌数据
     */
    @Override
    public RegisterToken selectRegisterTokenById(Long id)
    {
        return registerTokenMapper.selectRegisterTokenById(id);
    }

    /**
     * 查询注册令牌数据列表
     * 
     * @param registerToken 注册令牌数据
     * @return 注册令牌数据
     */
    @Override
    public List<RegisterToken> selectRegisterTokenList(RegisterToken registerToken)
    {
        return registerTokenMapper.selectRegisterTokenList(registerToken);
    }

    /**
     * 新增注册令牌数据
     * 
     * @param registerToken 注册令牌数据
     * @return 结果
     */
    @Override
    public int insertRegisterToken(RegisterToken registerToken)
    {
        registerToken.setCreateTime(DateUtils.getNowDate());
        return registerTokenMapper.insertRegisterToken(registerToken);
    }

    /**
     * 修改注册令牌数据
     * 
     * @param registerToken 注册令牌数据
     * @return 结果
     */
    @Override
    public int updateRegisterToken(RegisterToken registerToken)
    {
        return registerTokenMapper.updateRegisterToken(registerToken);
    }

    /**
     * 批量删除注册令牌数据
     * 
     * @param ids 需要删除的注册令牌数据主键
     * @return 结果
     */
    @Override
    public int deleteRegisterTokenByIds(String ids)
    {
        return registerTokenMapper.deleteRegisterTokenByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除注册令牌数据信息
     * 
     * @param id 注册令牌数据主键
     * @return 结果
     */
    @Override
    public int deleteRegisterTokenById(Long id)
    {
        return registerTokenMapper.deleteRegisterTokenById(id);
    }
}
