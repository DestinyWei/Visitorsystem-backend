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
 * @TableName info_review
 */
@ApiModel(value = "人员信息审核实体类")
@TableName(value = "info_review")
@Data
public class InfoReviewEntity implements Serializable {
    /**
     * 审核ID
     */
    @ApiModelProperty(value = "审核ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 审核人ID
     */
    @ApiModelProperty(value = "审核人ID")
    private Long reviewerId;

    /**
     * 访问申请ID
     */
    @ApiModelProperty(value = "访问申请ID")
    private Long visitId;

    /**
     * 状态 1-进行中 2-已完成 3-驳回
     */
    @ApiModelProperty(value = "状态 1-进行中 2-已完成 3-驳回")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
