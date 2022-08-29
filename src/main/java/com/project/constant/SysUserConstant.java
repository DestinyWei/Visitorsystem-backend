package com.project.constant;

/**
 * 用户常量
 */
public interface SysUserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "userLoginState";

    //  ------- 权限 --------

    /**
     * 默认权限-普通成员
     */
    String DEFAULT_ROLE = "0";

    /**
     * 管理员权限
     */
    String ADMIN_ROLE = "1";

    /**
     * 公司高管权限
     */
    String MANAGER_ROLE = "2";

    // -------校验结果-------

    /**
     * 校验返回结果码
     */
    String UNIQUE = "0";
    String NOT_UNIQUE = "1";

}
