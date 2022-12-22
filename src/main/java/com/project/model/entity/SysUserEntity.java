package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户实体类
 */
@ApiModel(value = "系统用户实体类")
@TableName("sys_user")
@Data
public class SysUserEntity implements Serializable {

    /** 用户Id */
    @ApiModelProperty(value = "用户Id")
    @TableId(type = IdType.AUTO)
    private Long userId;

    /** 部门Id */
    @ApiModelProperty(value = "部门Id")
    private Long deptId;

    /** 姓名 */
    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    @Size(min = 0, max = 20, message = "用户姓名不能超过20个字符")
    private String userName;

    /** 性别 0-未知 1-男 2-女 */
    @ApiModelProperty(value = "性别 0-未知 1-男 2-女")
    private String sex;

    /** 身份证号 */
    @ApiModelProperty(value = "身份证号")
    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "(^[1-9]\\\\d{5}(18|19|20)\\\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\\\d{3}[0-9Xx]$)|(^[1-9]\\\\d{5}\\\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\\\d{3}$)",
            message = "身份证号输入有误")
    private String idNumber;

    /** 电话 */
    @ApiModelProperty(value = "手机号码")
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$",
            message = "手机号码输入有误")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",
            message = "邮箱输入有误")
    private String email;

    /** 账户 */
    @ApiModelProperty(value = "账户")
    @NotBlank(message = "账户不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    private String userAccount;

    /** 密码 */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 0, max = 20, message = "用户密码长度不能超过20个字符")
    private String userPassword;

    /** 用户类型 0-默认 1-管理员 2-公司高管 */
    @ApiModelProperty(value = "用户类型 0-默认 1-管理员 2-公司高管")
    private String type;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /** 状态 0-正常 1-停用 */
    @ApiModelProperty(value = "状态 0-正常 1-停用")
    private String userStatus;

    /** 是否删除(逻辑删除) 0-否 1-是 */
    @ApiModelProperty(value = "是否删除(逻辑删除) 0-否 1-是")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty(value = "token")
    @TableField(exist = false)
    private String token;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}