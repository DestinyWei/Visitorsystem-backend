package com.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.constant.SysUserConstants;
import com.project.exception.ServiceException;
import com.project.mapper.SysRoleDeptMapper;
import com.project.mapper.SysRoleMapper;
import com.project.mapper.SysRoleMenuMapper;
import com.project.mapper.SysUserRoleMapper;
import com.project.model.dto.SysRoleDto;
import com.project.model.entity.*;
import com.project.service.SysRoleService;
import com.project.util.text.Convert;
import com.project.util.ResultUtils;
import com.project.util.SpringUtils;
import com.project.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 角色 业务层处理
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private SysRoleMenuMapper roleMenuMapper;

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Resource
    private SysRoleDeptMapper roleDeptMapper;

    /**
     * 根据条件分页查询角色数据
     * 
     * @param sysRoleDto 角色信息
     * @return 角色数据集合信息
     */
    @Override
    public BaseResponse selectRoleList(SysRoleDto sysRoleDto) {
        IPage<SysRoleEntity> page = roleMapper.selectRoleList(sysRoleDto);
        return ResultUtils.success(page, "查询成功");
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public BaseResponse selectRoleKeys(Long userId) {
        List<SysRoleEntity> perms = roleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRoleEntity perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return ResultUtils.success(permsSet, "查询权限成功");
    }

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public BaseResponse selectRolesByUserId(Long userId) {
        List<SysRoleEntity> userRoles = roleMapper.selectRolesByUserId(userId);
        List<SysRoleEntity> roles = selectRoleAll();
        for (SysRoleEntity role : roles) {
            for (SysRoleEntity userRole : userRoles) {
                if (role.getRoleId().longValue() == userRole.getRoleId().longValue()) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return ResultUtils.success(roles, "查询角色成功");
    }

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    @Override
    public List<SysRoleEntity> selectRoleAll() {
        return (List<SysRoleEntity>) SpringUtils.getAopProxy(this).selectRoleList(new SysRoleDto());
    }

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public BaseResponse selectRoleById(Long roleId) {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", "0")
                    .eq("role_id", roleId);
        SysRoleEntity sysRoleEntity = roleMapper.selectOne(queryWrapper);
        return ResultUtils.success(sysRoleEntity, "通过角色ID查询角色成功");
    }

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    @Transactional
    public BaseResponse deleteRoleById(Long roleId) {
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(roleId);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(roleId);
        boolean b = roleMapper.deleteRoleById(roleId) > 0 ? true : false;
        if (!b){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败,该角色不存在或已被删除");
        }
        return ResultUtils.success(b, "删除成功");
    }

    /**
     * 批量删除角色信息
     * 
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    @Override
    @Transactional
    public BaseResponse deleteRoleByIds(String ids, HttpServletRequest request) {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds) {
            SysRoleEntity sysRoleEntity = new SysRoleEntity();
            sysRoleEntity.setRoleId(roleId);
            checkRoleAllowed(sysRoleEntity);
            SysRoleEntity role = roleMapper.selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenu(roleIds);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDept(roleIds);
        int delete = roleMapper.deleteRoleByIds(roleIds);
        if (delete == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败,角色不存在或已被删除");
        }
        return ResultUtils.success(delete, "删除成功");
    }

    /**
     * 新增保存角色信息
     * 
     * @param sysRoleEntity 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public BaseResponse insertRole(SysRoleEntity sysRoleEntity) {
        sysRoleEntity.setCreateTime(new Date());
        sysRoleEntity.setDelFlag("0");
        // 新增角色信息
        roleMapper.insert(sysRoleEntity);
        this.insertRoleMenu(sysRoleEntity);
        return ResultUtils.success(sysRoleEntity.getRoleId(), "新增成功");
    }

    /**
     * 修改保存角色信息
     * 
     * @param sysRoleEntity 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public BaseResponse updateRole(SysRoleEntity sysRoleEntity) {
        if (sysRoleEntity.getRoleId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        // 修改角色信息
        roleMapper.updateById(sysRoleEntity);
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(sysRoleEntity.getRoleId());
        this.insertRoleMenu(sysRoleEntity);
        return ResultUtils.success(sysRoleEntity.getRoleId(), "修改成功");
    }

    /**
     * 修改角色部门信息
     * 
     * @param sysRoleEntity 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public BaseResponse updateRoleDept(SysRoleEntity sysRoleEntity) {
        if (sysRoleEntity.getRoleId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        // 修改角色信息
        roleMapper.updateRole(sysRoleEntity);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(sysRoleEntity.getRoleId());
        // 新增角色和部门信息（数据权限）
        if (insertRoleDept(sysRoleEntity) > 0){
            return ResultUtils.success(sysRoleEntity.getRoleId(), "修改角色部门信息成功");
        }
        return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改角色部门信息失败");
    }

    /**
     * 新增角色菜单信息
     * 
     * @param role 角色对象
     */
    @Transactional
    public BaseResponse insertRoleMenu(SysRoleEntity role) {
        int rows = 1;
        // 新增用户与角色管理
        List<SysRoleMenuEntity> list = new ArrayList<SysRoleMenuEntity>();
        for (Long menuId : role.getMenuIds()) {
            SysRoleMenuEntity rm = new SysRoleMenuEntity();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0) {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return ResultUtils.success(rows, "新增角色菜单信息及角色成功");
    }

    /**
     * 新增角色部门信息
     *
     * @param role 角色对象
     */
    public int insertRoleDept(SysRoleEntity role) {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<SysRoleDeptEntity> list = new ArrayList<SysRoleDeptEntity>();
        for (Long deptId : role.getDeptIds()) {
            SysRoleDeptEntity rd = new SysRoleDeptEntity();
            rd.setRoleId(role.getRoleId());
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0) {
            rows = roleDeptMapper.batchRoleDept(list);
        }
        return rows;
    }

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(SysRoleEntity role) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRoleEntity info = roleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return SysUserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return SysUserConstants.ROLE_NAME_UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(SysRoleEntity role) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRoleEntity info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return SysUserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return SysUserConstants.ROLE_KEY_UNIQUE;
    }

    /**
     * 校验角色是否允许操作
     * 
     * @param role 角色信息
     */
    @Override
    public void checkRoleAllowed(SysRoleEntity role) {
        if (StringUtils.isNotNull(role.getRoleId()) && role.isAdmin()) {
            throw new ServiceException("不允许操作超级管理员角色");
        }
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Long roleId) {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }

    /**
     * 角色状态修改
     *
     * @param sysRoleEntity 角色信息
     * @return 结果
     */
    @Override
    public BaseResponse changeStatus(SysRoleEntity sysRoleEntity) {
        int update = roleMapper.updateRole(sysRoleEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改失败,该角色不存在或已被删除");
        }
        return ResultUtils.success(sysRoleEntity.getRoleId(), "修改成功");
    }

    @Override
    public BaseResponse detail(Long id) {
        SysRoleEntity sysRoleEntity = roleMapper.selectById(id);
        return ResultUtils.success(sysRoleEntity, "查询详情成功");
    }
}
