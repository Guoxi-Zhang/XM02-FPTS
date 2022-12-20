package com.fpts.icuniauthloginapi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.icuniauthloginapi.mapper.IcUniAuthLoginApiInfoMapper;
import com.fpts.icuniauthloginapi.domain.IcUniAuthLoginApiInfo;
import com.fpts.icuniauthloginapi.service.IIcUniAuthLoginApiInfoService;
import com.fpts.common.core.text.Convert;

/**
 * 外部用户，用于存储从其他应用登录FPTS的用户的原始信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-17
 */
@Service
public class IIcUniAuthLoginApiInfoServiceImpl implements IIcUniAuthLoginApiInfoService
{
    @Autowired
    private IcUniAuthLoginApiInfoMapper IcUniAuthLoginApiInfoMapper;

    /**
     * 查询外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     *
     * @param userId 外部用户，用于存储从其他应用登录FPTS的用户的原始信息主键
     * @return 外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     */
    @Override
    public IcUniAuthLoginApiInfo selectExternalUserInfoByUserId(Long userId)
    {
        return IcUniAuthLoginApiInfoMapper.selectExternalUserInfoByUserId(userId);
    }

    /**
     * 查询外部用户，用于存储从其他应用登录FPTS的用户的原始信息列表
     *
     * @param externalUserInfo 外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     * @return 外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     */
    @Override
    public List<IcUniAuthLoginApiInfo> selectExternalUserInfoList(IcUniAuthLoginApiInfo externalUserInfo)
    {
        return IcUniAuthLoginApiInfoMapper.selectExternalUserInfoList(externalUserInfo);
    }

    /**
     * 新增外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     *
     * @param externalUserInfo 外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     * @return 结果
     */
    @Override
    public int insertExternalUserInfo(IcUniAuthLoginApiInfo externalUserInfo)
    {
        return IcUniAuthLoginApiInfoMapper.insertExternalUserInfo(externalUserInfo);
    }

    /**
     * 修改外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     *
     * @param externalUserInfo 外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     * @return 结果
     */
    @Override
    public int updateExternalUserInfo(IcUniAuthLoginApiInfo externalUserInfo)
    {
        return IcUniAuthLoginApiInfoMapper.updateExternalUserInfo(externalUserInfo);
    }

    /**
     * 批量删除外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     *
     * @param userIds 需要删除的外部用户，用于存储从其他应用登录FPTS的用户的原始信息主键
     * @return 结果
     */
    @Override
    public int deleteExternalUserInfoByUserIds(String userIds)
    {
        return IcUniAuthLoginApiInfoMapper.deleteExternalUserInfoByUserIds(Convert.toStrArray(userIds));
    }

    /**
     * 删除外部用户，用于存储从其他应用登录FPTS的用户的原始信息信息
     *
     * @param userId 外部用户，用于存储从其他应用登录FPTS的用户的原始信息主键
     * @return 结果
     */
    @Override
    public int deleteExternalUserInfoByUserId(Long userId)
    {
        return IcUniAuthLoginApiInfoMapper.deleteExternalUserInfoByUserId(userId);
    }
}

