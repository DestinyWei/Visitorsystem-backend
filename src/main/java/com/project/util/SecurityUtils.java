package com.project.util;

import com.project.common.ErrorCode;
import com.project.constant.SysUserConstants;
import com.project.exception.BusinessException;
import com.project.model.entity.SysUserEntity;

import javax.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.project.constant.SysUserConstants.USER_LOGIN_STATE;

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
        return loginUser.getUserId();
    }

    /**
     * 获取登录用户名称（查缓存）
     * @param request
     * @return User
     */
    public static String getUsername(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        SysUserEntity loginUser = (SysUserEntity) userObj;
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        return loginUser.getUserName();
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
        return user != null &&  (SysUserConstants.ADMIN_ROLE).equals(user.getType());
    }

    /**
     * 是否为管理员
     * @param user
     * @return boolean
     */
    public static boolean isAdmin(SysUserEntity user) {
        return user != null && (SysUserConstants.ADMIN_ROLE).equals(user.getType());
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
        return user != null && (SysUserConstants.MANAGER_ROLE).equals(user.getType());
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress= inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
