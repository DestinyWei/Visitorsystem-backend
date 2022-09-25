package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName info_score
 */
@ApiModel(value = "信息评分实体类")
@TableName(value = "info_score")
@Data
public class InfoScoreEntity implements Serializable {
    /**
     * 评分ID
     */
    @ApiModelProperty(value = "评分ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 访问人ID
     */
    @ApiModelProperty(value = "访问人ID")
    private Long applicantId;

    /**
     * 访问申请信息ID
     */
    @ApiModelProperty(value = "访问申请信息ID")
    @NotNull(message = "访问申请信息ID不能为空")
    private Long visitId;

    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Integer score;

    /**
     * 用户建议
     */
    @ApiModelProperty(value = "用户建议")
    private String suggest;

    /**
     * 是否采用
     */
    @ApiModelProperty(value = "是否采用")
    private String isUse;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 评分时间
     */
    @ApiModelProperty(value = "评分时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
