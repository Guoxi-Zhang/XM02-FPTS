package com.fpts.system.service.impl;

import java.util.List;

import com.fpts.common.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.system.mapper.CertificationMapper;
import com.fpts.system.domain.Certification;
import com.fpts.system.service.ICertificationService;
import com.fpts.common.core.text.Convert;

/**
 * 实名认证Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Service
public class CertificationServiceImpl implements ICertificationService {
    @Autowired
    private CertificationMapper certificationMapper;

    /**
     * 查询实名认证
     *
     * @param id 实名认证主键
     * @return 实名认证
     */
    @Override
    public Certification selectCertificationById(Long id) {
        return certificationMapper.selectCertificationById(id);
    }

    /**
     * 查询实名认证列表
     *
     * @param certification 实名认证
     * @return 实名认证
     */
    @Override
    @DataScope(userAlias = "certification")
    public List<Certification> selectCertificationList(Certification certification) {
        return certificationMapper.selectCertificationList(certification);
    }

    /**
     * 新增实名认证
     *
     * @param certification 实名认证
     * @return 结果
     */
    @Override
    public int insertCertification(Certification certification) {
        return certificationMapper.insertCertification(certification);
    }

    /**
     * 修改实名认证
     *
     * @param certification 实名认证
     * @return 结果
     */
    @Override
    public int updateCertification(Certification certification) {
        return certificationMapper.updateCertification(certification);
    }

    /**
     * 批量删除实名认证
     *
     * @param ids 需要删除的实名认证主键
     * @return 结果
     */
    @Override
    public int deleteCertificationByIds(String ids) {
        return certificationMapper.deleteCertificationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除实名认证信息
     *
     * @param id 实名认证主键
     * @return 结果
     */
    @Override
    public int deleteCertificationById(Long id) {
        return certificationMapper.deleteCertificationById(id);
    }
}
