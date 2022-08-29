package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName apply_info
 */
@TableName(value ="apply_info")
@Data
public class ApplyInfoEntity implements Serializable {
    /**
     * 访问申请ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 访问申请开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    /**
     * 访问申请结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    /**
     * 访问申请的部门ID
     */
    private Long departmentId;

    /**
     * 申请详情
     */
    private String applyInfo;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 申请人ID
     */
    private Long applicantId;

    /**
     * 负责人ID
     */
    private Long principalId;

    /**
     * 申请状态 0-待审核 1-审核中 2-已通过 3-驳回
     */
    private String applyStatus;

    /**
     * 企业ID(申请的)
     */
    private Long companyId;

    /**
     * 申请者
     */
    @TableField(exist = false)
    private String applicantName;

    /**
     * 负责人
     */
    @TableField(exist = false)
    private String principalName;

    /**
     * 公司名称
     */
    @TableField(exist = false)
    private String companyName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}