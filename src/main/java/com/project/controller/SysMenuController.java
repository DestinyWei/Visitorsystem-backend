package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.constant.SysUserConstants;
import com.project.model.dto.SysMenuDto;
import com.project.model.entity.SysMenuEntity;
import com.project.model.entity.SysRoleEntity;
import com.project.model.enums.BusinessType;
import com.project.service.SysMenuService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 菜单信息
 */
@Api(tags = "菜单信息接口")
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @ApiOperation(value = "菜单查询")
    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysMenuDto sysMenuDto, HttpServletRequest request) {
        return sysMenuService.selectMenuList(sysMenuDto, request);
    }

    /**
     * 删除菜单
     */
    @ApiOperation(value = "菜单删除")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{menuId}")
    public BaseResponse remove(@PathVariable("menuId") @ApiParam(value = "菜单Id") Long menuId) {
        if (sysMenuService.selectCountMenuByParentId(menuId) > 0) {
            return ResultUtils.error("存在子菜单,不允许删除");
        }
        if (sysMenuService.selectCountRoleMenuByMenuId(menuId) > 0) {
            return ResultUtils.error("菜单已分配,不允许删除");
        }
        return sysMenuService.deleteMenuById(menuId);
    }

    /**
     * 新增保存菜单
     */
    @ApiOperation(value = "菜单新增")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public BaseResponse insert(@Validated @RequestBody SysMenuEntity sysMenuEntity, HttpServletRequest request) {
        if (SysUserConstants.MENU_NAME_NOT_UNIQUE.equals(sysMenuService.checkMenuNameUnique(sysMenuEntity))) {
            return ResultUtils.error("新增菜单'" + sysMenuEntity.getMenuName() + "'失败，菜单名称已存在");
        }
        String username = SecurityUtils.getUsername(request);
        sysMenuEntity.setCreateBy(username);
        sysMenuEntity.setCreateTime(new Date());
        return sysMenuService.insertMenu(sysMenuEntity);
    }

    /**
     * 菜单详情
     */
    @ApiOperation(value = "菜单详情")
    @GetMapping("/detail/{menuId}")
    public BaseResponse detail(@PathVariable("menuId") @ApiParam(value = "菜单Id") Long menuId) {
        return sysMenuService.selectMenuById(menuId);
    }

    /**
     * 修改保存菜单
     */
    @ApiOperation(value = "菜单修改")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public BaseResponse update(@Validated @RequestBody SysMenuEntity sysMenuEntity, HttpServletRequest request) {
        if (SysUserConstants.MENU_NAME_NOT_UNIQUE.equals(sysMenuService.checkMenuNameUnique(sysMenuEntity))) {
            return ResultUtils.error("修改菜单'" + sysMenuEntity.getMenuName() + "'失败，菜单名称已存在");
        }
        String username = SecurityUtils.getUsername(request);
        sysMenuEntity.setUpdateBy(username);
        sysMenuEntity.setUpdateTime(new Date());
        return sysMenuService.updateMenu(sysMenuEntity);
    }

    /**
     * 校验菜单名称
     */
    @ApiOperation(value = "校验菜单名称")
    @PostMapping("/checkMenuNameUnique")
    public String checkMenuNameUnique(@RequestBody SysMenuEntity sysMenuEntity) {
        return sysMenuService.checkMenuNameUnique(sysMenuEntity);
    }

    /**
     * 加载角色菜单列表树
     */
    @ApiOperation(value = "加载角色菜单列表树")
    @PostMapping("/roleMenuTreeData")
    public BaseResponse roleMenuTreeData(@RequestBody SysRoleEntity sysRoleEntity, HttpServletRequest request) {
        // TODO 权限
        return sysMenuService.roleMenuTreeData(sysRoleEntity, request);
    }

    /**
     * 加载所有菜单列表树
     */
    @ApiOperation(value = "加载所有菜单列表树")
    @GetMapping("/menuTreeData")
    public BaseResponse menuTreeData(HttpServletRequest request) {
        return sysMenuService.menuTreeData(request);
    }

    /**
     * 选择菜单树
     */
    @ApiOperation(value = "选择菜单树")
    @GetMapping("/selectMenuTree/{menuId}")
    public BaseResponse selectMenuTree(@PathVariable("menuId") @ApiParam(value = "菜单Id") Long menuId) {
        return sysMenuService.selectMenuById(menuId);
    }
}