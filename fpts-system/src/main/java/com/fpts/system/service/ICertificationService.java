package com.fpts.system.service;

import java.util.List;

import com.fpts.system.domain.Certification;

/**
 * 实名认证Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface ICertificationService {
    /**
     * 查询实名认证
     *
     * @param id 实名认证主键
     * @return 实名认证
     */
    public Certification selectCertificationById(Long id);

    /**
     * 查询实名认证列表
     *
     * @param certification 实名认证
     * @return 实名认证集合
     */
    public List<Certification> selectCertificationList(Certification certification);

    /**
     * 新增实名认证
     *
     * @param certification 实名认证
     * @return 结果
     */
    public int insertCertification(Certification certification);

    /**
     * 修改实名认证
     *
     * @param certification 实名认证
     * @return 结果
     */
    public int updateCertification(Certification certification);

    /**
     * 批量删除实名认证
     *
     * @param ids 需要删除的实名认证主键集合
     * @return 结果
     */
    public int deleteCertificationByIds(String ids);

    /**
     * 删除实名认证信息
     *
     * @param id 实名认证主键
     * @return 结果
     */
    public int deleteCertificationById(Long id);
}
