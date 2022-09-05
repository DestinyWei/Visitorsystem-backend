package com.project.service;

import com.project.common.BaseResponse;
import com.project.model.dto.SysRoleDto;
import com.project.model.entity.SysRoleEntity;
import com.project.model.entity.SysUserRoleEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色业务层
 * 
 * @author ruoyi
 */
public interface SysRoleService {
    /**
     * 根据条件分页查询角色数据
     * 
     * @param sysRoleDto 角色信息
     * @return 角色数据集合信息
     */
    BaseResponse selectRoleList(SysRoleDto sysRoleDto);

    /**
     * 根据用户ID查询角色列表
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    BaseResponse selectRoleKeys(Long userId);

    /**
     * 根据用户ID查询角色权限
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    BaseResponse selectRolesByUserId(Long userId);

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    List<SysRoleEntity> selectRoleAll();

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    BaseResponse selectRoleById(Long roleId);

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    BaseResponse deleteRoleById(Long roleId);

    /**
     * 批量删除角色用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    BaseResponse deleteRoleByIds(String ids, HttpServletRequest request);

    /**
     * 新增保存角色信息
     * 
     * @param sysRoleEntity 角色信息
     * @return 结果
     */
    BaseResponse insertRole(SysRoleEntity sysRoleEntity);

    /**
     * 修改保存角色信息
     * 
     * @param sysRoleEntity 角色信息
     * @return 结果
     */
    BaseResponse updateRole(SysRoleEntity sysRoleEntity);

    /**
     * 修改角色部门信息
     * 
     * @param sysRoleEntity 角色信息
     * @return 结果
     */
    BaseResponse updateRoleDept(SysRoleEntity sysRoleEntity);

    /**
     * 校验角色名称是否唯一
     * 
     * @param sysRoleEntity 角色信息
     * @return 结果
     */
    String checkRoleNameUnique(SysRoleEntity sysRoleEntity);

    /**
     * 校验角色权限是否唯一
     * 
     * @param sysRoleEntity 角色信息
     * @return 结果
     */
    String checkRoleKeyUnique(SysRoleEntity sysRoleEntity);

    /**
     * 校验角色是否允许操作
     * 
     * @param sysRoleEntity 角色信息
     */
    void checkRoleAllowed(SysRoleEntity sysRoleEntity);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    int countUserRoleByRoleId(Long roleId);

    /**
     * 角色状态修改
     *
     * @param sysRoleEntity 角色信息
     * @return 结果
     */
    BaseResponse changeStatus(SysRoleEntity sysRoleEntity);

}
