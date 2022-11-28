package com.fpts.feedback.service;

import java.util.List;
import com.fpts.feedback.domain.UserFeedback;

/**
 * 用户反馈Service接口
 * 
 * @author lyc
 * @date 2022-11-27
 */
public interface IUserFeedbackService 
{
    /**
     * 查询用户反馈
     * 
     * @param userFeedbackId 用户反馈主键
     * @return 用户反馈
     */
    public UserFeedback selectUserFeedbackByUserFeedbackId(Long userFeedbackId);

    /**
     * 查询用户反馈列表
     * 
     * @param userFeedback 用户反馈
     * @return 用户反馈集合
     */
    public List<UserFeedback> selectUserFeedbackList(UserFeedback userFeedback);

    /**
     * 新增用户反馈
     * 
     * @param userFeedback 用户反馈
     * @return 结果
     */
    public int insertUserFeedback(UserFeedback userFeedback);

    /**
     * 修改用户反馈
     * 
     * @param userFeedback 用户反馈
     * @return 结果
     */
    public int updateUserFeedback(UserFeedback userFeedback);

    /**
     * 批量删除用户反馈
     * 
     * @param userFeedbackIds 需要删除的用户反馈主键集合
     * @return 结果
     */
    public int deleteUserFeedbackByUserFeedbackIds(String userFeedbackIds);

    /**
     * 删除用户反馈信息
     * 
     * @param userFeedbackId 用户反馈主键
     * @return 结果
     */
    public int deleteUserFeedbackByUserFeedbackId(Long userFeedbackId);
}
