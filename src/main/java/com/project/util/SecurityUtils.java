package com.project.util;

import com.project.common.ErrorCode;
import com.project.constant.SysUserConstant;
import com.project.exception.BusinessException;
import com.project.model.entity.SysUserEntity;

import javax.servlet.http.HttpServletRequest;

import static com.project.constant.SysUserConstant.USER_LOGIN_STATE;

/**
 * @description: 安全服务工具类
 * @author: weihaoming
 * @create: 2022-08-22-15:26
 * @version:
 */
public class SecurityUtils {

    /**
     * 获取登录用户Id（查缓存）
     * @param request
     * @return User
     */
    public static Long getLoginUserId(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        SysUserEntity loginUser = (SysUserEntity) userObj;
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        return loginUser.getId();
    }

    /**
     * 获取登录用户（查缓存）
     * @param request
     * @return User
     */
    public static SysUserEntity getLoginUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        SysUserEntity loginUser = (SysUserEntity) userObj;
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        return loginUser;
    }

    /**
     * 是否为管理员
     * @param request
     * @return boolean
     */
    public static boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        SysUserEntity user = (SysUserEntity) userObj;
        return user != null &&  (SysUserConstant.ADMIN_ROLE).equals(user.getType());
    }

    /**
     * 是否为管理员
     * @param user
     * @return boolean
     */
    public static boolean isAdmin(SysUserEntity user) {
        return user != null && (SysUserConstant.ADMIN_ROLE).equals(user.getType());
    }

    /**
     * 断言是否为管理员
     * @param request
     */
    public static void assertAdmin(HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
    }

    /**
     * 是否为公司高管
     * @param request
     * @return boolean
     */
    public static boolean isManager(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        SysUserEntity user = (SysUserEntity) userObj;
        return user != null && (SysUserConstant.MANAGER_ROLE).equals(user.getType());
    }
}
