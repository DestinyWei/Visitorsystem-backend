package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.constant.SysUserConstants;
import com.project.model.dto.SysRoleDto;
import com.project.model.entity.SysRoleEntity;
import com.project.model.enums.BusinessType;
import com.project.service.SysRoleService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 角色信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/role")
public class SysRoleController{

    @Resource
    private SysRoleService sysRoleService;

    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysRoleDto sysRoleDto) {
        return sysRoleService.selectRoleList(sysRoleDto);
    }

    /**
     * 新增保存角色
     */
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public BaseResponse insert(@Validated @RequestBody SysRoleEntity sysRoleEntity, HttpServletRequest request) {
        if (!SecurityUtils.isAdmin(request)){
            return ResultUtils.error(ErrorCode.NO_AUTH);
        }
        if (SysUserConstants.ROLE_NAME_NOT_UNIQUE.equals(sysRoleService.checkRoleNameUnique(sysRoleEntity)))
        {
            return ResultUtils.error("新增角色'" + sysRoleEntity.getRoleName() + "'失败，角色名称已存在");
        }
        else if (SysUserConstants.ROLE_KEY_NOT_UNIQUE.equals(sysRoleService.checkRoleKeyUnique(sysRoleEntity)))
        {
            return ResultUtils.error("新增角色'" + sysRoleEntity.getRoleName() + "'失败，角色权限已存在");
        }
        String userName = SecurityUtils.getLoginUser(request).getUserName();
        sysRoleEntity.setCreateBy(userName);
        return sysRoleService.insertRole(sysRoleEntity);
    }

    /**
     * 修改保存角色
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public BaseResponse update(@Validated @RequestBody SysRoleEntity sysRoleEntity, HttpServletRequest request) {
        if (!SecurityUtils.isAdmin(request)){
            return ResultUtils.error(ErrorCode.NO_AUTH);
        }
        sysRoleService.checkRoleAllowed(sysRoleEntity);
        if (SysUserConstants.ROLE_NAME_NOT_UNIQUE.equals(sysRoleService.checkRoleNameUnique(sysRoleEntity))) {
            return ResultUtils.error("修改角色'" + sysRoleEntity.getRoleName() + "'失败，角色名称已存在");
        }
        else if (SysUserConstants.ROLE_KEY_NOT_UNIQUE.equals(sysRoleService.checkRoleKeyUnique(sysRoleEntity))) {
            return ResultUtils.error("修改角色'" + sysRoleEntity.getRoleName() + "'失败，角色权限已存在");
        }
        String userName = SecurityUtils.getLoginUser(request).getUserName();
        sysRoleEntity.setUpdateBy(userName);
        sysRoleEntity.setUpdateTime(new Date());
        return sysRoleService.updateRole(sysRoleEntity);
    }


    /**
     * 保存角色部门信息
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/updateRoleDept")
    public BaseResponse updateRoleDept(@RequestBody SysRoleEntity sysRoleEntity, HttpServletRequest request) {
        if (!SecurityUtils.isAdmin(request)){
            return ResultUtils.error(ErrorCode.NO_AUTH);
        }
        sysRoleService.checkRoleAllowed(sysRoleEntity);
        String userName = SecurityUtils.getLoginUser(request).getUserName();
        sysRoleEntity.setUpdateBy(userName);
        sysRoleEntity.setUpdateTime(new Date());
        return sysRoleService.updateRoleDept(sysRoleEntity);
    }

    /**
     * 删除角色
     */
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public BaseResponse remove(String ids, HttpServletRequest request) {
        if (!SecurityUtils.isAdmin(request)){
            return ResultUtils.error(ErrorCode.NO_AUTH);
        }
        return sysRoleService.deleteRoleByIds(ids, request);
    }

    /**
     * 校验角色名称
     */
    @PostMapping("/checkRoleNameUnique")
    public BaseResponse checkRoleNameUnique(@RequestBody SysRoleEntity sysRoleEntity) {
        String roleNameUnique = sysRoleService.checkRoleNameUnique(sysRoleEntity);
        return ResultUtils.success(roleNameUnique, "校验角色名称成功");
    }

    /**
     * 校验角色权限
     */
    @PostMapping("/checkRoleKeyUnique")
    public BaseResponse checkRoleKeyUnique(@RequestBody SysRoleEntity sysRoleEntity) {
        String checkRoleKeyUnique = sysRoleService.checkRoleKeyUnique(sysRoleEntity);
        return ResultUtils.success(checkRoleKeyUnique, "校验角色权限成功");
    }

    /**
     * 角色状态修改
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    public BaseResponse changeStatus(@RequestBody SysRoleEntity sysRoleEntity, HttpServletRequest request) {
        sysRoleService.checkRoleAllowed(sysRoleEntity);
        return sysRoleService.changeStatus(sysRoleEntity);
    }
}