package com.fpts.feedback.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fpts.common.annotation.Excel;
import com.fpts.common.core.domain.BaseEntity;

/**
 * 用户反馈对象 user_feedback
 * 
 * @author lyc
 * @date 2022-11-27
 */
public class UserFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户反馈信息 */
    @Excel(name = "用户反馈信息")
    private String userFeedbackContent;

    /** 管理员回复内容 */
    @Excel(name = "管理员回复内容")
    private String adminFeedbackContent;

    /** 用户反馈编号 */
    private Long userFeedbackId;

    /** 用户反馈创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "用户反馈创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date userFeedbackCreatetime;

    /** 管理员回复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "管理员回复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adminFeedbackCreatetime;

    /** 完成标记 */
    @Excel(name = "完成标记")
    private Integer completemark;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserFeedbackContent(String userFeedbackContent) 
    {
        this.userFeedbackContent = userFeedbackContent;
    }

    public String getUserFeedbackContent() 
    {
        return userFeedbackContent;
    }
    public void setAdminFeedbackContent(String adminFeedbackContent) 
    {
        this.adminFeedbackContent = adminFeedbackContent;
    }

    public String getAdminFeedbackContent() 
    {
        return adminFeedbackContent;
    }
    public void setUserFeedbackId(Long userFeedbackId) 
    {
        this.userFeedbackId = userFeedbackId;
    }

    public Long getUserFeedbackId() 
    {
        return userFeedbackId;
    }
    public void setUserFeedbackCreatetime(Date userFeedbackCreatetime) 
    {
        this.userFeedbackCreatetime = userFeedbackCreatetime;
    }

    public Date getUserFeedbackCreatetime() 
    {
        return userFeedbackCreatetime;
    }
    public void setAdminFeedbackCreatetime(Date adminFeedbackCreatetime) 
    {
        this.adminFeedbackCreatetime = adminFeedbackCreatetime;
    }

    public Date getAdminFeedbackCreatetime() 
    {
        return adminFeedbackCreatetime;
    }
    public void setCompletemark(Integer completemark) 
    {
        this.completemark = completemark;
    }

    public Integer getCompletemark() 
    {
        return completemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userFeedbackContent", getUserFeedbackContent())
            .append("adminFeedbackContent", getAdminFeedbackContent())
            .append("userFeedbackId", getUserFeedbackId())
            .append("userFeedbackCreatetime", getUserFeedbackCreatetime())
            .append("adminFeedbackCreatetime", getAdminFeedbackCreatetime())
            .append("completemark", getCompletemark())
            .toString();
    }
}
