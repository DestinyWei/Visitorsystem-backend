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
 * @TableName apply_info
 */
@ApiModel(value = "访问申请实体类")
@TableName(value ="apply_info")
@Data
public class ApplyInfoEntity implements Serializable {
    /**
     * 访问申请ID
     */
    @ApiModelProperty(value = "访问申请Id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 访问申请开始时间
     */
    @ApiModelProperty(value = "访问申请开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date startTime;

    /**
     * 访问申请结束时间
     */
    @ApiModelProperty(value = "访问申请结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date endTime;

    /**
     * 访问申请的部门ID
     */
    @ApiModelProperty(value = "访问申请的部门Id")
    @NotNull(message = "访问申请的部门Id不能为空")
    private Long deptId;

    /**
     * 申请详情
     */
    @ApiModelProperty(value = "申请详情")
    private String applyInfo;

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 申请人ID
     */
    @ApiModelProperty(value = "申请人Id")
    @NotNull(message = "申请人Id不能为空")
    private Long applicantId;

    /**
     * 负责人ID
     */
    @ApiModelProperty(value = "负责人Id")
    @NotNull(message = "负责人Id不能为空")
    private Long principalId;

    /**
     * 申请状态 0-待审核 1-审核中 2-已通过 3-驳回
     */
    @ApiModelProperty(value = "申请状态 0-待审核 1-审核中 2-已通过 3-驳回")
    private String applyStatus;

    /**
     * 企业ID(申请的)
     */
    @ApiModelProperty(value = "申请的公司Id")
    @NotNull(message = "企业Id(申请的)不能为空")
    private Long companyId;

    /**
     * 申请者
     */
    @ApiModelProperty(value = "申请者")
    @TableField(exist = false)
    private String applicantName;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    @TableField(exist = false)
    private String principalName;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    @TableField(exist = false)
    private String companyName;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    @TableField(exist = false)
    private String deptName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}