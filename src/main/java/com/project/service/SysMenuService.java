package com.project.service;

import com.project.common.BaseResponse;
import com.project.model.dto.SysMenuDto;
import com.project.model.entity.SysMenuEntity;
import com.project.model.entity.SysRoleEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 菜单 业务层
 * 
 * @author ruoyi
 */
public interface SysMenuService {
    /**
     * 根据用户ID查询菜单
     * 
     * @param request 用户信息
     * @return 菜单列表
     */
    BaseResponse selectMenusByUser(HttpServletRequest request);

    /**
     * 查询系统菜单列表
     * 
     * @param sysMenuDto 菜单信息
     * @param request 用户ID
     * @return 菜单列表
     */
    BaseResponse selectMenuList(SysMenuDto sysMenuDto, HttpServletRequest request);

    /**
     * 查询菜单集合
     * 
     * @param request
     * @return 所有菜单信息
     */
    List<SysMenuEntity> selectMenuAll(HttpServletRequest request);

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    BaseResponse selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询权限
     * 
     * @param roleId 角色ID
     * @return 权限列表
     */
    BaseResponse selectPermsByRoleId(Long roleId);

    /**
     * 根据角色ID查询菜单
     * 
     * @param sysRoleEntity 角色对象
     * @param request
     * @return 菜单列表
     */
    BaseResponse roleMenuTreeData(SysRoleEntity sysRoleEntity, HttpServletRequest request);

    /**
     * 查询所有菜单信息
     * 
     * @param request
     * @return 菜单列表
     */
    BaseResponse menuTreeData(HttpServletRequest request);

    /**
     * 查询系统所有权限
     * 
     * @param request
     * @return 权限列表
     */
    BaseResponse selectPermsAll(HttpServletRequest request);

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    BaseResponse deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    BaseResponse selectMenuById(Long menuId);

    /**
     * 查询菜单数量
     * 
     * @param parentId 菜单父ID
     * @return 结果
     */
    int selectCountMenuByParentId(Long parentId);

    /**
     * 查询菜单使用数量
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    int selectCountRoleMenuByMenuId(Long menuId);

    /**
     * 新增保存菜单信息
     * 
     * @param sysMenuEntity 菜单信息
     * @return 结果
     */
    BaseResponse insertMenu(SysMenuEntity sysMenuEntity);

    /**
     * 修改保存菜单信息
     * 
     * @param sysMenuEntity 菜单信息
     * @return 结果
     */
    BaseResponse updateMenu(SysMenuEntity sysMenuEntity);

    /**
     * 校验菜单名称是否唯一
     * 
     * @param sysMenuEntity 菜单信息
     * @return 结果
     */
    String checkMenuNameUnique(SysMenuEntity sysMenuEntity);
}
