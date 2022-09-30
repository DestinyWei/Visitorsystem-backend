package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.exception.BusinessException;
import com.project.model.dto.SysUserDto;
import com.project.model.entity.SysUserEntity;
import com.project.model.enums.BusinessType;
import com.project.model.request.SysUserLoginRequest;
import com.project.model.request.SysUserRegisterRequest;
import com.project.model.request.SysUserUpdatePwdRequest;
import com.project.service.SysUserService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
@Api(tags = "系统用户接口")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public BaseResponse userRegister(@Validated @RequestBody SysUserRegisterRequest sysUserRegisterRequest) {
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

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public BaseResponse userLogin(@Validated @RequestBody SysUserLoginRequest sysUserLoginRequest, HttpServletRequest request) {
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

    @ApiOperation(value = "用户登出")
    @PostMapping("/logout")
    public BaseResponse userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return sysUserService.userLogout(request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/current")
    public BaseResponse getCurrentUser(HttpServletRequest request) {
        SysUserEntity loginUser = SecurityUtils.getLoginUser(request);
        long userId = loginUser.getUserId();
        SysUserEntity user = sysUserService.getById(userId);
        SysUserEntity safetyUser = sysUserService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    @ApiOperation(value = "用户查询")
    @PostMapping("/search")
    public BaseResponse searchUser(@Validated @RequestBody SysUserDto sysUserDto, HttpServletRequest request){
        Long userId = SecurityUtils.getLoginUserId(request);
        sysUserDto.setCurrentUserId(userId);
        return sysUserService.search(sysUserDto);
    }

    @ApiOperation(value = "用户删除")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public BaseResponse removeUser(@ApiParam(value = "用户Id") Long id, HttpServletRequest request) {
        SecurityUtils.assertAdmin(request);
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = sysUserService.removeById(id);
        return ResultUtils.success(result);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/updatePwd")
    public BaseResponse updatePwd(@Validated @RequestBody SysUserUpdatePwdRequest sysUserUpdatePwdRequest, HttpServletRequest request){
        return sysUserService.updatePwd(sysUserUpdatePwdRequest, request);
    }

    @ApiOperation(value = "用户详情")
    @GetMapping("/detail/{userId}")
    public BaseResponse detail(@PathVariable("userId") @ApiParam(value = "用户Id") Long userId){
        return sysUserService.detail(userId);
    }
}
