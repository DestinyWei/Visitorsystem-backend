package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 系统用户实体类
 */
@TableName("sys_user")
@Data
public class SysUserEntity implements Serializable {

    /**
     * 用户Id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门Id
     */
    private Long departmentId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别 0-未知 1-男 2-女
     */
    private String sex;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 账户
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户类型 0-默认 1-管理员 2-公司高管
     */
    private String type;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 状态 0-正常 1-停用
     */
    private String userStatus;

    /**
     * 是否删除(逻辑删除) 0-否 1-是
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private String roleType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}