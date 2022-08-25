package com.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.common.BaseResponse;
import com.project.model.dto.SysUserDto;
import com.project.model.entity.SysUserEntity;
import com.project.model.request.SysUserLoginRequest;
import com.project.model.request.SysUserRegisterRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 用户注册
     *
     * @param sysUserRegisterRequest 用户注册请求
     * @return 新用户 id
     */
    BaseResponse userRegister(SysUserRegisterRequest sysUserRegisterRequest);

    /**
     * 用户登录
     *
     * @param sysUserLoginRequest 用户登录请求
     * @return 脱敏后的用户信息
     */
    BaseResponse userLogin(SysUserLoginRequest sysUserLoginRequest, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    SysUserEntity getSafetyUser(SysUserEntity originUser);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    BaseResponse userLogout(HttpServletRequest request);

    /**
     * 分页查询所有用户
     * @return BaseResponse<IPage<SysUserEntity>>
     */
    BaseResponse search(SysUserDto sysUserDto);
}
