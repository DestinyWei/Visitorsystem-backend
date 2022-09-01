package com.project.constant;

/**
 * 用户常量
 */
public interface SysUserConstants {

    /** 用户登录态键 */
    String USER_LOGIN_STATE = "userLoginState";

    //  ------- 权限 --------

    /** 默认权限-普通成员 */
    String DEFAULT_ROLE = "0";

    /** 管理员权限 */
    String ADMIN_ROLE = "1";

    /** 公司高管权限 */
    String MANAGER_ROLE = "2";

    // -------校验结果-------

    /** 字典正常状态 */
    public static final String DICT_NORMAL = "0";

    /** 是否为系统默认（是） */
    public static final String YES = "Y";

    /** 校验返回结果码 */
    String UNIQUE = "0";
    String NOT_UNIQUE = "1";

    /** 角色名称是否唯一的返回结果码 */
    public final static String ROLE_NAME_UNIQUE = "0";
    public final static String ROLE_NAME_NOT_UNIQUE = "1";

    /** 角色权限是否唯一的返回结果码 */
    public final static String ROLE_KEY_UNIQUE = "0";
    public final static String ROLE_KEY_NOT_UNIQUE = "1";

    /** 菜单名称是否唯一的返回结果码 */
    public final static String MENU_NAME_UNIQUE = "0";
    public final static String MENU_NAME_NOT_UNIQUE = "1";

    /** 字典类型是否唯一的返回结果码 */
    public final static String DICT_TYPE_UNIQUE = "0";
    public final static String DICT_TYPE_NOT_UNIQUE = "1";

}
