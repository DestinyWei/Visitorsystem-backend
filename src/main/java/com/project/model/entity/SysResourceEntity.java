package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName sys_resource
 */
@ApiModel(value = "系统资源实体类")
@TableName(value ="sys_resource")
@Data
public class SysResourceEntity implements Serializable {
    /**
     * 资源ID
     */
    @ApiModelProperty(value = "资源Id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 数据类型
     */
    @ApiModelProperty(value = "数据类型")
    @NotBlank(message = "数据类型不能为空")
    private String dataType;

    /**
     * 上级资源ID
     */
    @ApiModelProperty(value = "上级资源Id")
    private Long parentResourceId;

    /**
     * 资源类型 1-系统资源 2-普通资源
     */
    @ApiModelProperty(value = "资源类型 1-系统资源 2-普通资源")
    @NotBlank(message = "资源类型不能为空")
    private String resourceType;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    private String resourceName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 资源URL
     */
    @ApiModelProperty(value = "资源URL")
    @Size(min = 0, max = 512, message = "资源URL长度不能超过512个字符")
    private String resourceUrl;

    /**
     * 图片URL
     */
    @ApiModelProperty(value = "图片URL")
    @Size(min = 0, max = 512, message = "图片URL长度不能超过512个字符")
    private String photoUrl;

    /**
     * 资源编码
     */
    @ApiModelProperty(value = "资源编码")
    private String resourceCode;

    /**
     * 授权状态 0-未授权 1-已授权
     */
    @ApiModelProperty(value = "授权状态")
    @NotNull(message = "授权状态不能为空")
    private String authorizationStatus;

    /**
     * 状态 0-正常 1-停用
     */
    @ApiModelProperty(value = "状态 0-正常 1-停用")
    private String status;

    /**
     * 创建者Id
     */
    @ApiModelProperty(value = "创建者Id")
    private Long createUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @TableField(exist = false)
    private String createUserName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}