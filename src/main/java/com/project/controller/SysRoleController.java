package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.SysRoleDto;
import com.project.model.entity.SysRoleEntity;
import com.project.model.enums.BusinessType;
import com.project.service.SysRoleService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.project.common.ErrorCode.NO_AUTH;

/**
 * @description: 角色接口
 * @author: weihaoming
 * @create: 2022-08-25-17:54
 * @version:
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @PostMapping("/insert")
    @Log(title = "角色新增", businessType = BusinessType.INSERT)
    public BaseResponse insert(@RequestBody SysRoleEntity sysRoleEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysRoleService.insert(sysRoleEntity, request);
    }

    @PostMapping("/remove")
    @Log(title = "角色删除", businessType = BusinessType.DELETE)
    public BaseResponse remove(@RequestBody SysRoleEntity sysRoleEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysRoleService.remove(sysRoleEntity);
    }

    @PostMapping("/update")
    @Log(title = "角色修改", businessType = BusinessType.UPDATE)
    public BaseResponse update(@RequestBody SysRoleEntity sysRoleEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysRoleService.update(sysRoleEntity);
    }

    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysRoleDto sysRoleDto){
        return sysRoleService.search(sysRoleDto);
    }
}
