package com.project.model.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysUserEntity;
import lombok.Data;

/**
 * 系统用户实体类
 */
@Data
public class SysUserDto extends Page<SysUserEntity> {

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别 0-未知 1-男 2-女
     */
    private String sex;

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
     * 用户类型 0-默认 1-管理员 2-公司高管
     */
    private String type;

    /**
     * 状态 0-正常 1-停用
     */
    private String userStatus;

    @TableField(exist = false)
    private String roleType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}