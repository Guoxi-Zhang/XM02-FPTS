package com.fpts.icuniauthloginapi.service;

import java.util.List;
import com.fpts.icuniauthloginapi.domain.IcUniAuthLoginApiInfo;

/**
 * 外部用户，用于存储从其他应用登录FPTS的用户的原始信息Service接口
 *
 * @author ruoyi
 * @date 2022-12-17
 */
public interface IIcUniAuthLoginApiInfoService
{
    /**
     * 查询外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     *
     * @param userId 外部用户，用于存储从其他应用登录FPTS的用户的原始信息主键
     * @return 外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     */
    public IcUniAuthLoginApiInfo selectExternalUserInfoByUserId(Long userId);

    /**
     * 查询外部用户，用于存储从其他应用登录FPTS的用户的原始信息列表
     *
     * @param externalUserInfo 外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     * @return 外部用户，用于存储从其他应用登录FPTS的用户的原始信息集合
     */
    public List<IcUniAuthLoginApiInfo> selectExternalUserInfoList(IcUniAuthLoginApiInfo externalUserInfo);

    /**
     * 新增外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     *
     * @param externalUserInfo 外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     * @return 结果
     */
    public int insertExternalUserInfo(IcUniAuthLoginApiInfo externalUserInfo);

    /**
     * 修改外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     *
     * @param externalUserInfo 外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     * @return 结果
     */
    public int updateExternalUserInfo(IcUniAuthLoginApiInfo externalUserInfo);

    /**
     * 批量删除外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     *
     * @param userIds 需要删除的外部用户，用于存储从其他应用登录FPTS的用户的原始信息主键集合
     * @return 结果
     */
    public int deleteExternalUserInfoByUserIds(String userIds);

    /**
     * 删除外部用户，用于存储从其他应用登录FPTS的用户的原始信息信息
     *
     * @param userId 外部用户，用于存储从其他应用登录FPTS的用户的原始信息主键
     * @return 结果
     */
    public int deleteExternalUserInfoByUserId(Long userId);
}

