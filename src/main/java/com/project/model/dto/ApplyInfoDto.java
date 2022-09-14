package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.model.entity.ApplyInfoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-29-12:48
 * @version:
 */
@ApiModel(value = "访问申请请求类")
@Data
public class ApplyInfoDto extends Page<ApplyInfoEntity> implements Serializable {

    private static final long serialVersionUID = -1045984643777471082L;
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;
    @ApiModelProperty(value = "申请创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "申请状态 0-待审核 1-审核中 2-已通过 3-驳回")
    private String applyStatus;
    @ApiModelProperty(value = "申请者")
    private String applicantName;
    @ApiModelProperty(value = "负责人")
    private String principalName;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "部门名称")
    private String deptName;
}
