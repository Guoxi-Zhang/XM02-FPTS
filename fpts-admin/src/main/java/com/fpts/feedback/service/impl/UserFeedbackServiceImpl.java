package com.fpts.feedback.service.impl;

import java.util.List;

import com.fpts.common.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.feedback.mapper.UserFeedbackMapper;
import com.fpts.feedback.domain.UserFeedback;
import com.fpts.feedback.service.IUserFeedbackService;
import com.fpts.common.core.text.Convert;

/**
 * 用户反馈Service业务层处理
 * 
 * @author lyc
 * @date 2022-11-27
 */
@Service
public class UserFeedbackServiceImpl implements IUserFeedbackService 
{
    @Autowired
    private UserFeedbackMapper userFeedbackMapper;

    /**
     * 查询用户反馈
     * 
     * @param userFeedbackId 用户反馈主键
     * @return 用户反馈
     */
    @Override
    @DataScope(deptAlias = "user_feedback", userAlias = "user_feedback")
    public UserFeedback selectUserFeedbackByUserFeedbackId(Long userFeedbackId)
    {
        return userFeedbackMapper.selectUserFeedbackByUserFeedbackId(userFeedbackId);
    }

    /**
     * 查询用户反馈列表
     * 
     * @param userFeedback 用户反馈
     * @return 用户反馈
     */
    @Override
    @DataScope(deptAlias = "user_feedback", userAlias = "user_feedback")
    public List<UserFeedback> selectUserFeedbackList(UserFeedback userFeedback)
    {
        return userFeedbackMapper.selectUserFeedbackList(userFeedback);
    }

    /**
     * 新增用户反馈
     * 
     * @param userFeedback 用户反馈
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "user_feedback", userAlias = "user_feedback")
    public int insertUserFeedback(UserFeedback userFeedback)
    {
        return userFeedbackMapper.insertUserFeedback(userFeedback);
    }

    /**
     * 修改用户反馈
     * 
     * @param userFeedback 用户反馈
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "user_feedback", userAlias = "user_feedback")
    public int updateUserFeedback(UserFeedback userFeedback)
    {
        return userFeedbackMapper.updateUserFeedback(userFeedback);
    }

    /**
     * 批量删除用户反馈
     * 
     * @param userFeedbackIds 需要删除的用户反馈主键
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "user_feedback", userAlias = "user_feedback")
    public int deleteUserFeedbackByUserFeedbackIds(String userFeedbackIds)
    {
        return userFeedbackMapper.deleteUserFeedbackByUserFeedbackIds(Convert.toStrArray(userFeedbackIds));
    }

    /**
     * 删除用户反馈信息
     * 
     * @param userFeedbackId 用户反馈主键
     * @return 结果
     */
    @Override
    @DataScope(deptAlias = "user_feedback", userAlias = "user_feedback")
    public int deleteUserFeedbackByUserFeedbackId(Long userFeedbackId)
    {
        return userFeedbackMapper.deleteUserFeedbackByUserFeedbackId(userFeedbackId);
    }
}
