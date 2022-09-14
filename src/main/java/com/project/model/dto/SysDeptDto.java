package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysDeptEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-26-15:58
 * @version:
 */
@ApiModel(value = "系统部门请求类")
@Data
public class SysDeptDto extends Page<SysDeptEntity> implements Serializable {

    private static final long serialVersionUID = -8596715751176429694L;
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    @ApiModelProperty(value = "上级部门Id")
    private Long parentDeptId;
    @ApiModelProperty(value = "部门状态（0正常 1停用）")
    private String status;
    @ApiModelProperty(value = "所属单位")
    private String unit;
    @ApiModelProperty(value = "级别 1-最高级别 以此类推")
    private String level;
    @ApiModelProperty(value = "创建者")
    private String createUserName;
    @ApiModelProperty(value = "当前登录用户")
    private Long currentUserId;
}
