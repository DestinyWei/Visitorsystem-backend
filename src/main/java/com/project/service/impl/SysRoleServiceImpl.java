package com.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.model.dto.SysRoleDto;
import com.project.model.entity.SysRoleEntity;
import com.project.service.SysRoleService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import com.project.mapper.SysRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-22-11:31
 * @version:
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity>
        implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public BaseResponse insert(SysRoleEntity sysRoleEntity, HttpServletRequest request) {
        Long userId = SecurityUtils.getLoginUserId(request);
        sysRoleEntity.setCreateUserId(userId);
        sysRoleEntity.setCreateTime(new Date());
        sysRoleMapper.insert(sysRoleEntity);
        return ResultUtils.success(sysRoleEntity.getId(), "新增角色成功");
    }

    @Override
    public BaseResponse remove(SysRoleEntity sysRoleEntity) {
        if (sysRoleEntity.getId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int delete = sysRoleMapper.deleteById(sysRoleEntity.getId());
        if (delete == 0){
            return ResultUtils.error("删除失败");
        }
        return ResultUtils.success(sysRoleEntity.getId(), "删除成功");
    }

    @Override
    public BaseResponse update(SysRoleEntity sysRoleEntity) {
        if (sysRoleEntity.getId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", sysRoleEntity.getId());
        int update = sysRoleMapper.update(sysRoleEntity, queryWrapper);
        if (update == 0){
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success(sysRoleEntity.getId(), "修改成功");
    }

    @Override
    public BaseResponse search(SysRoleDto sysRoleDto) {
        IPage<SysRoleEntity> page = sysRoleMapper.selectRole(sysRoleDto);
        return ResultUtils.success(page);
    }
}
