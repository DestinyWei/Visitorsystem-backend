package com.project.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户登录请求体
 */
@ApiModel(value = "用户登录请求体")
@Data
public class SysUserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    @ApiModelProperty(value = "账户", required = true)
    @NotBlank(message = "账户不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    private String userAccount;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Size(min = 0, max = 20, message = "用户密码长度不能超过20个字符")
    private String userPassword;
}
