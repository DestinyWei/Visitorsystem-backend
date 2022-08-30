package com.project.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户修改密码请求体
 * @author: weihaoming
 * @create: 2022-08-30-18:38
 * @version:
 */
@Data
public class SysUserUpdatePwdRequest implements Serializable {

    private static final long serialVersionUID = 3471520446913825920L;

    private String rawPassword;

    private String userPassword;

    private String checkPassword;
}
