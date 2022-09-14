package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.model.entity.SysOperLogEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-31-11:36
 * @version:
 */
@ApiModel(value = "系统操作日志请求类")
@Data
public class SysOperLogDto extends Page<SysOperLogEntity> implements Serializable {

    private static final long serialVersionUID = 2216384634355355164L;
    @ApiModelProperty(value = "操作模块")
    private String title;
    @ApiModelProperty(value = "业务类型（0其它 1新增 2修改 3删除）")
    private Integer businessType;
    @ApiModelProperty(value = "业务类型数组")
    private Integer[] businessTypes;
    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date operTime;
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date beginTime;
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;
}
