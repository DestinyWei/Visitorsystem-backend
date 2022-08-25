package com.project.model.request;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户注册请求体
 */
@Data
public class SysUserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

    private String userName;

    private String sex;

    private String idNumber;

    private String phone;

    private String email;
}
