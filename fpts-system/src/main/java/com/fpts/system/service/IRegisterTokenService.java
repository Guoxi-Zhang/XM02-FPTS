package com.fpts.system.service;

import java.util.List;
import com.fpts.system.domain.RegisterToken;

/**
 * 注册令牌数据Service接口
 * 
 * @author ruoyi
 * @date 2023-01-13
 */
public interface IRegisterTokenService 
{
    /**
     * 查询注册令牌数据
     * 
     * @param id 注册令牌数据主键
     * @return 注册令牌数据
     */
    public RegisterToken selectRegisterTokenById(Long id);

    /**
     * 查询注册令牌数据列表
     * 
     * @param registerToken 注册令牌数据
     * @return 注册令牌数据集合
     */
    public List<RegisterToken> selectRegisterTokenList(RegisterToken registerToken);

    /**
     * 新增注册令牌数据
     * 
     * @param registerToken 注册令牌数据
     * @return 结果
     */
    public int insertRegisterToken(RegisterToken registerToken);

    /**
     * 修改注册令牌数据
     * 
     * @param registerToken 注册令牌数据
     * @return 结果
     */
    public int updateRegisterToken(RegisterToken registerToken);

    /**
     * 批量删除注册令牌数据
     * 
     * @param ids 需要删除的注册令牌数据主键集合
     * @return 结果
     */
    public int deleteRegisterTokenByIds(String ids);

    /**
     * 删除注册令牌数据信息
     * 
     * @param id 注册令牌数据主键
     * @return 结果
     */
    public int deleteRegisterTokenById(Long id);
}
