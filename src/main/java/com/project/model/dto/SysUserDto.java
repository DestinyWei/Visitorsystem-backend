package com.project.model.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysUserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 系统用户实体类
 */
@ApiModel(value = "系统用户请求类")
@Data
public class SysUserDto extends Page<SysUserEntity> implements Serializable {

    private static final long serialVersionUID = -2676375676294796247L;
    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    @Size(min = 0, max = 20, message = "用户姓名不能超过20个字符")
    private String userName;

    @ApiModelProperty(value = "性别 0-未知 1-男 2-女")
    private String sex;

    @ApiModelProperty(value = "电话")
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$",
            message = "手机号码输入有误")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",
            message = "邮箱输入有误")
    private String email;

    @ApiModelProperty(value = "账户")
    @NotBlank(message = "账户不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    private String userAccount;

    @ApiModelProperty(value = "用户类型 0-默认 1-管理员 2-公司高管")
    private String type;

    @ApiModelProperty(value = "状态 0-正常 1-停用")
    private String userStatus;

    @ApiModelProperty(value = "角色类型")
    @TableField(exist = false)
    private String roleType;
}