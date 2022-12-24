package com.project.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.model.entity.InfoReviewEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: dsl
 * @create: 2022-09-13-12:01
 * @version:
 */
@ApiModel(value = "人员信息审核实体类")
@Data
public class InfoReviewDto extends Page<InfoReviewEntity> implements Serializable {

    private static final long serialVersionUID = 7963814922713760301L;
    @ApiModelProperty(value = "审核人ID")
    private Long reviewerId;
    @ApiModelProperty(value = "状态 1-进行中 2-已完成 3-驳回")
    private String status;
    @ApiModelProperty(value = "审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
