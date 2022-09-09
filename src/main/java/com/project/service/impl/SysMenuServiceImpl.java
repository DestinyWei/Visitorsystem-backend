package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.constant.SysUserConstants;
import com.project.mapper.SysMenuMapper;
import com.project.mapper.SysRoleMenuMapper;
import com.project.model.dto.SysMenuDto;
import com.project.model.entity.SysMenuEntity;
import com.project.model.entity.SysRoleEntity;
import com.project.model.entity.SysUserEntity;
import com.project.model.entity.Ztree;
import com.project.service.SysMenuService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import com.project.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.*;

/**
 * 菜单 业务层处理
 */
@Service
public class SysMenuServiceImpl implements SysMenuService
{
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Resource
    private SysMenuMapper menuMapper;

    @Resource
    private SysRoleMenuMapper roleMenuMapper;

    /**
     * 根据用户查询菜单
     * 
     * @param request 用户信息
     * @return 菜单列表
     */
    @Override
    public BaseResponse selectMenusByUser(HttpServletRequest request) {
        SysUserEntity loginUser = SecurityUtils.getLoginUser(request);
        List<SysMenuEntity> menus = new LinkedList<SysMenuEntity>();
        // 管理员显示所有菜单信息
        if (SecurityUtils.isAdmin(request)) {
            menus = menuMapper.selectMenuNormalAll();
        }
        else {
            menus = menuMapper.selectMenusByUserId(loginUser.getUserId());
        }
        List<SysMenuEntity> childPerms = getChildPerms(menus, 0);
        return ResultUtils.success(childPerms, "根据用户查询菜单成功");
    }

    /**
     * 查询菜单集合
     * 
     * @return 所有菜单信息
     */
    @Override
    public BaseResponse selectMenuList(SysMenuDto sysMenuDto, HttpServletRequest request) {
        Long userId = SecurityUtils.getLoginUserId(request);
        if (SecurityUtils.isAdmin(request)) {
            IPage<SysMenuEntity> page = menuMapper.selectMenuList(sysMenuDto);
            return ResultUtils.success(page, "查询成功");
        } else {
            sysMenuDto.setCurrentUserId(userId);
            IPage<SysMenuEntity> page = menuMapper.selectMenuListByUserId(sysMenuDto);
            return ResultUtils.success(page, "查询成功");
        }
    }

    /**
     * 查询菜单集合
     * 
     * @return 所有菜单信息
     */
    @Override
    public List<SysMenuEntity> selectMenuAll(HttpServletRequest request)
    {
        Long userId = SecurityUtils.getLoginUserId(request);
        List<SysMenuEntity> menuList = null;
        if (SecurityUtils.isAdmin(request))
        {
            menuList = menuMapper.selectMenuAll();
        }
        else
        {
            menuList = menuMapper.selectMenuAllByUserId(userId);
        }
        return menuList;
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public BaseResponse selectPermsByUserId(Long userId)
    {
        List<String> perms = menuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return ResultUtils.success(permsSet, "根据用户ID查询权限成功");
    }

    /**
     * 根据角色ID查询权限
     * 
     * @param roleId 角色ID
     * @return 权限列表
     */
    @Override
    public BaseResponse selectPermsByRoleId(Long roleId)
    {
        List<String> perms = menuMapper.selectPermsByRoleId(roleId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return ResultUtils.success(permsSet, "根据角色ID查询权限成功");
    }

    /**
     * 根据角色ID查询菜单
     * 
     * @param sysRoleEntity 角色对象
     * @return 菜单列表
     */
    @Override
    public BaseResponse roleMenuTreeData(SysRoleEntity sysRoleEntity, HttpServletRequest request) {
        Long roleId = sysRoleEntity.getRoleId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysMenuEntity> menuList = selectMenuAll(request);
        if (StringUtils.isNotNull(roleId)) {
            List<String> roleMenuList = menuMapper.selectMenuTree(roleId);
            ztrees = initZtree(menuList, roleMenuList, true);
        }
        else {
            ztrees = initZtree(menuList, null, true);
        }
        return ResultUtils.success(ztrees, "根据角色ID查询菜单成功");
    }

    /**
     * 查询所有菜单
     * 
     * @return 菜单列表
     */
    @Override
    public BaseResponse menuTreeData(HttpServletRequest request)
    {
        List<SysMenuEntity> menuList = selectMenuAll(request);
        List<Ztree> ztrees = initZtree(menuList);
        return ResultUtils.success(ztrees, "查询所有菜单成功");
    }

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    @Override
    public BaseResponse selectPermsAll(HttpServletRequest request)
    {
        LinkedHashMap<String, String> section = new LinkedHashMap<>();
        List<SysMenuEntity> permissions = selectMenuAll(request);
        if (StringUtils.isNotEmpty(permissions))
        {
            for (SysMenuEntity menu : permissions)
            {
                section.put(menu.getUrl(), MessageFormat.format(PREMISSION_STRING, menu.getPerms()));
            }
        }
        return ResultUtils.success(permissions, "查询系统所有权限成功");
    }

    /**
     * 对象转菜单树
     * 
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysMenuEntity> menuList)
    {
        return initZtree(menuList, null, false);
    }

    /**
     * 对象转菜单树
     * 
     * @param menuList 菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysMenuEntity> menuList, List<String> roleMenuList, boolean permsFlag)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleMenuList);
        for (SysMenuEntity menu : menuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getMenuId());
            ztree.setpId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getMenuName());
            if (isCheck)
            {
                ztree.setChecked(roleMenuList.contains(menu.getMenuId() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(SysMenuEntity menu, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public BaseResponse deleteMenuById(Long menuId)
    {
        int delete = menuMapper.deleteMenuById(menuId);
        if (delete == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "该菜单不存在");
        }
        return ResultUtils.success(delete, "删除菜单管理信息成功");
    }

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public BaseResponse selectMenuById(Long menuId)
    {
        SysMenuEntity sysMenuEntity = menuMapper.selectMenuById(menuId);
        return ResultUtils.success(sysMenuEntity, "查询菜单详情成功");
    }

    /**
     * 查询子菜单数量
     * 
     * @param parentId 父级菜单ID
     * @return 结果
     */
    @Override
    public int selectCountMenuByParentId(Long parentId)
    {
        return menuMapper.selectCountMenuByParentId(parentId);
    }

    /**
     * 查询菜单使用数量
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int selectCountRoleMenuByMenuId(Long menuId) {
        return roleMenuMapper.selectCountRoleMenuByMenuId(menuId);
    }

    /**
     * 新增保存菜单信息
     * 
     * @param sysMenuEntity 菜单信息
     * @return 结果
     */
    @Override
    public BaseResponse insertMenu(SysMenuEntity sysMenuEntity) {
        int insert = menuMapper.insert(sysMenuEntity);
        if (insert == 0){
            return ResultUtils.error(ErrorCode.SAVE_ERROR, "新增失败");
        }
        return ResultUtils.success(sysMenuEntity.getMenuId(), "新增成功");
    }

    /**
     * 修改保存菜单信息
     * 
     * @param sysMenuEntity 菜单信息
     * @return 结果
     */
    @Override
    public BaseResponse updateMenu(SysMenuEntity sysMenuEntity) {
        if (sysMenuEntity.getMenuId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int update = menuMapper.updateById(sysMenuEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改失败");
        }
        return ResultUtils.success(sysMenuEntity.getMenuId(), "修改成功");
    }

    /**
     * 校验菜单名称是否唯一
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public String checkMenuNameUnique(SysMenuEntity menu)
    {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        SysMenuEntity info = menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue())
        {
            return SysUserConstants.MENU_NAME_NOT_UNIQUE;
        }
        return SysUserConstants.MENU_NAME_UNIQUE;
    }

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuEntity> getChildPerms(List<SysMenuEntity> list, int parentId)
    {
        List<SysMenuEntity> returnList = new ArrayList<SysMenuEntity>();
        for (Iterator<SysMenuEntity> iterator = list.iterator(); iterator.hasNext();)
        {
            SysMenuEntity t = (SysMenuEntity) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * 
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenuEntity> list, SysMenuEntity t)
    {
        // 得到子节点列表
        List<SysMenuEntity> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuEntity tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuEntity> getChildList(List<SysMenuEntity> list, SysMenuEntity t)
    {
        List<SysMenuEntity> tlist = new ArrayList<SysMenuEntity>();
        Iterator<SysMenuEntity> it = list.iterator();
        while (it.hasNext())
        {
            SysMenuEntity n = (SysMenuEntity) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuEntity> list, SysMenuEntity t)
    {
        return getChildList(list, t).size() > 0;
    }
}
