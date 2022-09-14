package com.project.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 用户修改密码请求体
 * @author: weihaoming
 * @create: 2022-08-30-18:38
 * @version:
 */
@ApiModel(value = "用户修改密码请求体")
@Data
public class SysUserUpdatePwdRequest implements Serializable {

    private static final long serialVersionUID = 3471520446913825920L;

    @ApiModelProperty(value = "原密码", required = true)
    @NotBlank(message = "原密码不能为空")
    @Size(min = 0, max = 20, message = "用户原密码长度不能超过20个字符")
    private String rawPassword;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Size(min = 0, max = 20, message = "用户密码长度不能超过20个字符")
    private String userPassword;

    @ApiModelProperty(value = "校验密码", required = true)
    @NotBlank(message = "校验密码不能为空")
    @Size(min = 0, max = 20, message = "用户校验密码长度不能超过20个字符")
    private String checkPassword;
}
