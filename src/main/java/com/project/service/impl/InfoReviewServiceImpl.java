package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.exception.BusinessException;
import com.project.mapper.ApplyInfoMapper;
import com.project.mapper.InfoReviewMapper;
import com.project.model.dto.InfoReviewDto;
import com.project.model.entity.ApplyInfoEntity;
import com.project.model.entity.InfoReviewEntity;
import com.project.service.InfoReviewService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dsl
 * @description 针对表【info_review】的数据库操作Service实现
 * @createDate 2022-08-25 12:43:45
 */

@Service
public class InfoReviewServiceImpl extends ServiceImpl<InfoReviewMapper, InfoReviewEntity>
    implements InfoReviewService {
    @Resource
    private InfoReviewMapper infoReviewMapper;

    @Resource
    private ApplyInfoMapper applyInfoMapper;

    @Override
    @Transactional
    public BaseResponse insert(InfoReviewEntity infoReviewEntity, HttpServletRequest request) {
        Long LoginUserId = SecurityUtils.getLoginUserId(request);
        infoReviewEntity.setReviewerId(LoginUserId);
        infoReviewEntity.setCreateTime(new Date());
        this.insertInfo(infoReviewEntity);
        int insert = infoReviewMapper.insert(infoReviewEntity);

        //修改关联的访问申请表中的状态
        ApplyInfoEntity applyInfoEntity = applyInfoMapper.selectApplyInfoById(infoReviewEntity.getVisitId());
        applyInfoEntity.setApplyStatus(infoReviewEntity.getStatus());
        applyInfoMapper.updateById(applyInfoEntity);

        if (insert == 0){
            return ResultUtils.error(ErrorCode.SAVE_ERROR, "新增失败");
        }
            return ResultUtils.success(infoReviewEntity.getId(), "新增成功");
    }

    @Override
    @Transactional
    public BaseResponse remove(Long id) {
        if (id == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }

        //删除关联的访问申请表
        InfoReviewEntity infoReviewEntity = infoReviewMapper.selectById(id);
        applyInfoMapper.deleteById(infoReviewEntity.getVisitId());

        int delete = infoReviewMapper.deleteInfoReviewById(id);
        if (delete == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败,该审核表不存在或已被删除");
        }
        return ResultUtils.success(id, "删除成功");
    }

    @Override
    @Transactional
    public BaseResponse removes(Long[] visitIds) {
        List<Long> ids = Arrays.stream(visitIds).collect(Collectors.toList());
        //删除关联的访问申请表
        applyInfoMapper.deleteBatchIds(ids);
        int deletes = infoReviewMapper.deleteBatchIds(ids);
        if (deletes == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败,该审核表不存在或已被删除");
        }
        return ResultUtils.success(deletes, "批量删除成功");
    }

    @Override
    @Transactional
    public BaseResponse update(InfoReviewEntity infoReviewEntity) {
        if (infoReviewEntity.getId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        ApplyInfoEntity applyInfo = applyInfoMapper.selectById(infoReviewEntity.getVisitId());
        if (applyInfo == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "访问申请表不存在");
        }

        //修改关联的访问申请表中的状态
        ApplyInfoEntity applyInfoEntity = applyInfoMapper.selectApplyInfoById(infoReviewEntity.getVisitId());
        applyInfoEntity.setApplyStatus(infoReviewEntity.getStatus());
        applyInfoMapper.updateById(applyInfoEntity);

        int update = infoReviewMapper.updateById(infoReviewEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改失败,该审核表不存在或已被删除");
        }
        return ResultUtils.success(infoReviewEntity.getId(), "修改成功");
    }

    @Override
    public BaseResponse selectById(Long id) {
        InfoReviewEntity infoReviewEntity = infoReviewMapper.selectInfoReviewById(id);
        return ResultUtils.success(infoReviewEntity, "查询详情成功");
    }

    @Override
    public BaseResponse selectByVisitId(Long visitId) {
        InfoReviewEntity infoReviewEntity = infoReviewMapper.selectInfoReviewByVisitId(visitId);
        return ResultUtils.success(infoReviewEntity, "查询详情成功");
    }

    @Override
    public BaseResponse search(InfoReviewDto infoReviewDto) {
        IPage<InfoReviewEntity> page = infoReviewMapper.selectInfoReview(infoReviewDto);
        return ResultUtils.success(page, "查询成功");
    }

    private InfoReviewEntity insertInfo(InfoReviewEntity infoReviewEntity){
        ApplyInfoEntity applyInfo = applyInfoMapper.selectApplyInfoById(infoReviewEntity.getVisitId());
        if (applyInfo == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "访问申请表不存在");
        }
        return infoReviewEntity;
    }
}
