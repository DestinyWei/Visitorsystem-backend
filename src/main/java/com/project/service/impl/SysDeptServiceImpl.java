package com.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.model.dto.SysDeptDto;
import com.project.model.entity.SysDeptEntity;
import com.project.service.SysDeptService;
import com.project.mapper.SysDeptMapper;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
* @author Destiny
* @description 针对表【sys_dept】的数据库操作Service实现
* @createDate 2022-08-26 15:55:39
*/
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity>
    implements SysDeptService{

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public BaseResponse insert(SysDeptEntity sysDeptEntity, HttpServletRequest request) {
        if (SecurityUtils.isAdmin(request)){
            return ResultUtils.error(ErrorCode.NO_AUTH,"仅限管理员可操作");
        }
        Long userId = SecurityUtils.getLoginUserId(request);
        sysDeptEntity.setCreateUserId(userId);
        sysDeptEntity.setCreateTime(new Date());
        int insert = sysDeptMapper.insert(sysDeptEntity);
        if (insert == 0){
            return ResultUtils.error(ErrorCode.SAVE_ERROR, "新增失败");
        }
        return ResultUtils.success(sysDeptEntity.getDeptId(), "新增成功");
    }

    @Override
    public BaseResponse remove(SysDeptEntity sysDeptEntity) {
        if (sysDeptEntity.getDeptId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int delete = sysDeptMapper.deleteById(sysDeptEntity.getDeptId());
        if (delete == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败");
        }
        return ResultUtils.success(sysDeptEntity.getDeptId(), "删除成功");
    }

    @Override
    public BaseResponse update(SysDeptEntity sysDeptEntity) {
        if (sysDeptEntity.getDeptId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int update = sysDeptMapper.updateById(sysDeptEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改失败");
        }
        return ResultUtils.success(sysDeptEntity.getDeptId(), "修改成功");
    }

    @Override
    public BaseResponse search(SysDeptDto sysDeptDto) {
        IPage<SysDeptEntity> page = sysDeptMapper.selectDept(sysDeptDto);
        return ResultUtils.success(page, "查询成功");
    }
}




