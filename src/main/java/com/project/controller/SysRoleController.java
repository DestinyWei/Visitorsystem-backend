package com.project.controller;

import com.project.common.BaseResponse;
import com.project.model.dto.SysRoleDto;
import com.project.model.entity.SysRoleEntity;
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
    public BaseResponse insert(@RequestBody SysRoleEntity sysRoleEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH);
        }
        return sysRoleService.insert(sysRoleEntity, request);
    }

    @PostMapping("/remove")
    public BaseResponse remove(@RequestBody SysRoleEntity sysRoleEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH);
        }
        return sysRoleService.remove(sysRoleEntity);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody SysRoleEntity sysRoleEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH);
        }
        return sysRoleService.update(sysRoleEntity);
    }

    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysRoleDto sysRoleDto){
        return sysRoleService.search(sysRoleDto);
    }
}
