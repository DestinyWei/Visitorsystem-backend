package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysResourceEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-26-16:43
 * @version:
 */
@Data
public class SysResourceDto extends Page<SysResourceEntity> implements Serializable {

    private static final long serialVersionUID = 3699346717642107663L;
    @ApiModelProperty(value = "数据类型")
    private String dataType;
    @ApiModelProperty(value = "上级资源Id")
    private Long parentResourceId;
    @ApiModelProperty(value = "资源类型 1-系统资源 2-普通资源")
    private String resourceType;
    @ApiModelProperty(value = "资源名称")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    private String resourceName;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "资源编码")
    private String resourceCode;
    @ApiModelProperty(value = "授权状态")
    private Integer authorizationStatus;
    @ApiModelProperty(value = "状态 0-正常 1-停用")
    private Integer status;
    @ApiModelProperty(value = "创建者Id")
    private Long createUserId;
    @ApiModelProperty(value = "创建者")
    private String createUserName;
}
