package com.project.controller;

import com.project.common.ErrorCode;
import com.project.exception.BusinessException;
import com.project.model.dto.SysUserDto;
import com.project.model.entity.SysUserEntity;
import com.project.model.request.SysUserLoginRequest;
import com.project.model.request.SysUserRegisterRequest;
import com.project.service.SysUserService;
import com.project.common.BaseResponse;
import com.project.util.ResultUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.project.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping("/register")
    public BaseResponse userRegister(@RequestBody SysUserRegisterRequest sysUserRegisterRequest) {
        if (sysUserRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = sysUserRegisterRequest.getUserAccount();
        String userPassword = sysUserRegisterRequest.getUserPassword();
        String checkPassword = sysUserRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return sysUserService.userRegister(sysUserRegisterRequest);
    }

    @PostMapping("/login")
    public BaseResponse userLogin(@RequestBody SysUserLoginRequest sysUserLoginRequest, HttpServletRequest request) {
        if (sysUserLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = sysUserLoginRequest.getUserAccount();
        String userPassword = sysUserLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return sysUserService.userLogin(sysUserLoginRequest, request);
    }

    @PostMapping("/logout")
    public BaseResponse userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return sysUserService.userLogout(request);
    }

    @GetMapping("/current")
    public BaseResponse getCurrentUser(HttpServletRequest request) {
        SysUserEntity loginUser = SecurityUtils.getLoginUser(request);
        long userId = loginUser.getId();
        SysUserEntity user = sysUserService.getById(userId);
        SysUserEntity safetyUser = sysUserService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    @PostMapping("/search")
    public BaseResponse searchUser(@RequestBody SysUserDto sysUserDto){
        return sysUserService.search(sysUserDto);
    }

    @PostMapping("/delete")
    public BaseResponse deleteUser(@RequestBody Long id, HttpServletRequest request) {
        SecurityUtils.assertAdmin(request);
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = sysUserService.removeById(id);
        return ResultUtils.success(result);
    }
}
