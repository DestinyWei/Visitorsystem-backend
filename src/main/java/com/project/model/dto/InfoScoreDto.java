package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.model.entity.InfoScoreEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: daishanlang
 * @create: 2022-09-13-12:01
 * @version:
 */
@ApiModel(value = "信息评分实体类")
@Data
public class InfoScoreDto extends Page<InfoScoreEntity> implements Serializable {

    @ApiModelProperty(value = "访问人ID")
    private Long applicantId;
    @ApiModelProperty(value = "是否采用")
    private String isUse;
    @ApiModelProperty(value = "评分时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
