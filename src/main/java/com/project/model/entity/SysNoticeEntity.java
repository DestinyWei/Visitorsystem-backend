package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 通知公告表 sys_notice
 */
@ApiModel(value = "公告通知实体类")
@TableName("sys_notice")
@Data
public class SysNoticeEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    @ApiModelProperty(value = "公告Id")
    @TableId(type = IdType.AUTO)
    private Long noticeId;

    /** 公告标题 */
    @ApiModelProperty(value = "公告标题")
    @NotBlank(message = "公告标题不能为空")
    @Size(min = 0, max = 50, message = "公告标题不能超过50个字符")
    private String noticeTitle;

    /** 公告类型（1通知 2公告） */
    @ApiModelProperty(value = "公告类型（1通知 2公告）")
    private String noticeType;

    /** 公告内容 */
    @ApiModelProperty(value = "公告内容")
    private String noticeContent;

    /** 公告状态（0正常 1关闭） */
    @ApiModelProperty(value = "公告状态（0正常 1关闭）")
    private String status;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("noticeId", getNoticeId())
            .append("noticeTitle", getNoticeTitle())
            .append("noticeType", getNoticeType())
            .append("noticeContent", getNoticeContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
