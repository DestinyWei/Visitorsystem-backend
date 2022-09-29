package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.mapper.SysResourceMapper;
import com.project.model.dto.SysResourceDto;
import com.project.model.entity.SysResourceEntity;
import com.project.service.SysResourceService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
* @author Destiny
* @description 针对表【sys_resource】的数据库操作Service实现
* @createDate 2022-08-26 16:35:53
*/
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResourceEntity>
    implements SysResourceService{

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Override
    @Transactional
    public BaseResponse insert(SysResourceEntity sysResourceEntity, HttpServletRequest request) {
        Long userId = SecurityUtils.getLoginUserId(request);
        sysResourceEntity.setCreateUserId(userId);
        sysResourceEntity.setCreateTime(new Date());
        int insert = sysResourceMapper.insert(sysResourceEntity);
        if (insert == 0){
            return ResultUtils.error(ErrorCode.SAVE_ERROR, "新增失败");
        }
        return ResultUtils.success(sysResourceEntity.getId(), "新增成功");
    }

    @Override
    @Transactional
    public BaseResponse remove(Long resourceId) {
        if (resourceId == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int delete = sysResourceMapper.deleteById(resourceId);
        if (delete == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败,该系统资源不存在或已被删除");
        }
        return ResultUtils.success(resourceId, "删除成功");
    }

    @Override
    @Transactional
    public BaseResponse update(SysResourceEntity sysResourceEntity) {
        if (sysResourceEntity.getId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int update = sysResourceMapper.updateById(sysResourceEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改失败,该系统资源不存在或已被删除");
        }
        return ResultUtils.success(sysResourceEntity.getId(), "修改成功");
    }

    @Override
    public BaseResponse search(SysResourceDto sysResourceDto) {
        IPage<SysResourceEntity> page = sysResourceMapper.selectResouces(sysResourceDto);
        return ResultUtils.success(page, "查询成功");
    }
}




