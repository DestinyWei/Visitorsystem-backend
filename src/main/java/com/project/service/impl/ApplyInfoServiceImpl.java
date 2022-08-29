package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.model.dto.ApplyInfoDto;
import com.project.model.entity.ApplyInfoEntity;
import com.project.service.ApplyInfoService;
import com.project.mapper.ApplyInfoMapper;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
* @author Destiny
* @description 针对表【apply_info】的数据库操作Service实现
* @createDate 2022-08-29 12:43:45
*/
@Service
public class ApplyInfoServiceImpl extends ServiceImpl<ApplyInfoMapper, ApplyInfoEntity>
    implements ApplyInfoService{

    @Resource
    private ApplyInfoMapper applyInfoMapper;

    @Override
    public BaseResponse insert(ApplyInfoEntity applyInfoEntity, HttpServletRequest request) {
        Long userId = SecurityUtils.getLoginUserId(request);
        applyInfoEntity.setCreateTime(new Date());
        applyInfoEntity.setApplicantId(userId);
        applyInfoEntity.setApplyStatus("0");
        int insert = applyInfoMapper.insert(applyInfoEntity);
        if (insert == 0){
            return ResultUtils.error(ErrorCode.SAVE_ERROR, "新增失败");
        }
        return ResultUtils.success(applyInfoEntity.getId(), "新增成功");
    }

    @Override
    public BaseResponse remove(ApplyInfoEntity applyInfoEntity) {
        if (applyInfoEntity.getId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int delete = applyInfoMapper.deleteById(applyInfoEntity.getId());
        if (delete == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败");
        }
        return ResultUtils.success(applyInfoEntity.getId(), "删除成功");
    }

    @Override
    public BaseResponse update(ApplyInfoEntity applyInfoEntity) {
        if (applyInfoEntity.getId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int update = applyInfoMapper.updateById(applyInfoEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改失败");
        }
        return ResultUtils.success(applyInfoEntity.getId(), "修改成功");
    }

    @Override
    public BaseResponse search(ApplyInfoDto applyInfoDto) {
        IPage<ApplyInfoEntity> page = applyInfoMapper.selectApplyInfo(applyInfoDto);
        return ResultUtils.success(page, "查询成功");
    }
}




