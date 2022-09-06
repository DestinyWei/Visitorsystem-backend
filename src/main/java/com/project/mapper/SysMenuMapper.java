package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.SysMenuDto;
import com.project.model.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单表 数据层
 * 
 * @author ruoyi
 */
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
    /**
     * 查询系统所有菜单（含按钮）
     * 
     * @return 菜单列表
     */
    List<SysMenuEntity> selectMenuAll();

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuEntity> selectMenuAllByUserId(Long userId);

    /**
     * 查询系统正常显示菜单（不含按钮）
     * 
     * @return 菜单列表
     */
    List<SysMenuEntity> selectMenuNormalAll();

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuEntity> selectMenusByUserId(Long userId);

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询权限
     * 
     * @param roleId 角色ID
     * @return 权限列表
     */
    List<String> selectPermsByRoleId(Long roleId);

    /**
     * 根据角色ID查询菜单
     * 
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<String> selectMenuTree(Long roleId);

    /**
     * 查询系统菜单列表
     * 
     * @param sysMenuDto 菜单信息
     * @return 菜单列表
     */
    IPage<SysMenuEntity> selectMenuList(SysMenuDto sysMenuDto);

    /**
     * 查询系统菜单列表
     * 
     * @param sysMenuDto 菜单信息
     * @return 菜单列表
     */
    IPage<SysMenuEntity> selectMenuListByUserId(SysMenuDto sysMenuDto);

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    int deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenuEntity selectMenuById(Long menuId);

    /**
     * 查询菜单数量
     * 
     * @param parentId 菜单父ID
     * @return 结果
     */
    int selectCountMenuByParentId(Long parentId);

    /**
     * 新增菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    int insertMenu(SysMenuEntity menu);

    /**
     * 修改菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    int updateMenu(SysMenuEntity menu);

    /**
     * 校验菜单名称是否唯一
     * 
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    SysMenuEntity checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);
}
