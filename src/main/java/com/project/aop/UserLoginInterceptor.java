package com.project.aop;

import com.project.common.ErrorCode;
import com.project.exception.BusinessException;
import com.project.util.JwtUtils;
import com.project.util.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 拦截器：验证用户是否登录
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Resource
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /** 地址过滤 */
        String uri = request.getRequestURI() ;
        if (uri.contains("/user/login") || uri.contains("/user/register")){
            return true ;
        }
        /** Token 验证 */
        String token = request.getHeader(jwtUtils.getHeader());
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(jwtUtils.getHeader());
        }
        if(StringUtils.isEmpty(token)){
            throw new BusinessException(ErrorCode.NULL_ERROR, jwtUtils.getHeader()+ "不能为空");
        }

        Claims claims = null;
        try{
            claims = jwtUtils.getTokenClaim(token);
            if(claims == null || jwtUtils.isTokenExpired(claims.getExpiration())){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, jwtUtils.getHeader() + "失效，请重新登录。");
            }
        }catch (Exception e){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, jwtUtils.getHeader() + "失效，请重新登录。");
        }

        /** 设置 identityId 用户身份ID */
        request.setAttribute("identityId", claims.getSubject());
        return true;
    }
}
