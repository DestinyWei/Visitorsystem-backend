package com.project.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户注册请求体
 */
@ApiModel(value = "用户注册请求体")
@Data
public class SysUserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    @ApiModelProperty(value = "账户", required = true)
    @NotBlank(message = "账户不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    private String userAccount;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Size(min = 0, max = 20, message = "用户密码长度不能超过20个字符")
    private String userPassword;

    @ApiModelProperty(value = "校验密码", required = true)
    @NotBlank(message = "校验密码不能为空")
    @Size(min = 0, max = 20, message = "用户校验密码长度不能超过20个字符")
    private String checkPassword;

    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    @Size(min = 0, max = 20, message = "用户姓名不能超过20个字符")
    private String userName;

    @ApiModelProperty(value = "性别", required = true)
    @NotBlank(message = "性别不能为空")
    private String sex;

    @ApiModelProperty(value = "身份证号", required = true)
    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "(^[1-9]\\\\d{5}(18|19|20)\\\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\\\d{3}[0-9Xx]$)|(^[1-9]\\\\d{5}\\\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\\\d{3}$)",
            message = "身份证号输入有误")
    private String idNumber;

    @ApiModelProperty(value = "手机", required = true)
    @NotBlank(message = "手机不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$",
            message = "手机输入有误")
    private String phone;

    @ApiModelProperty(value = "邮箱", required = true)
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",
            message = "邮箱输入有误")
    private String email;
}
