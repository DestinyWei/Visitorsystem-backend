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
 * @TableName sys_resource
 */
@TableName(value ="sys_resource")
@Data
public class SysResourceEntity implements Serializable {
    /**
     * 资源ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 上级资源ID
     */
    private Long parentResourceId;

    /**
     * 资源类型 1-系统资源 2-普通资源
     */
    private String resourceType;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 资源URL
     */
    private String resourceUrl;

    /**
     * 图片URL
     */
    private String photoUrl;

    /**
     * 资源编码
     */
    private String resourceCode;

    /**
     * 授权状态 0-未授权 1-已授权
     */
    private Integer authorizationStatus;

    /**
     * 状态 0-正常 1-停用
     */
    private Integer status;

    /**
     * 创建者Id
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建者
     */
    @TableField(exist = false)
    private String createUserName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}