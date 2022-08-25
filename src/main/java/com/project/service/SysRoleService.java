package com.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.common.BaseResponse;
import com.project.model.dto.SysRoleDto;
import com.project.model.entity.SysRoleEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-21-22:54
 * @version:
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 新增角色
     * @param sysRoleEntity 系统角色类
     * @return long
     */
    BaseResponse insert(SysRoleEntity sysRoleEntity, HttpServletRequest request);

    /**
     * 删除角色
     * @param sysRoleEntity
     * @return long
     */
    BaseResponse remove(SysRoleEntity sysRoleEntity);

    /**
     * 修改角色
     * @param sysRoleEntity
     * @return SysRoleEntity
     */
    BaseResponse update(SysRoleEntity sysRoleEntity);

    /**
     * 分页查询所有角色
     * @param sysRoleDto
     * @return IPage<SysRoleEntity>
     */
    BaseResponse search(SysRoleDto sysRoleDto);

}
