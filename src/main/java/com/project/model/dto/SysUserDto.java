package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysUserEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户实体类
 */
@ApiModel(value = "系统用户请求类")
@Data
public class SysUserDto extends Page<SysUserEntity> implements Serializable {

    private static final long serialVersionUID = -2676375676294796247L;

    private String userName;
    private String sex;
    private String phone;
    private String email;
    private String userAccount;
    private String type;
    private String userStatus;
    private Long currentUserId;
    private String roleType;
}