package com.project.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.exception.BusinessException;
import com.project.mapper.ApplyInfoMapper;
import com.project.mapper.InfoScoreMapper;
import com.project.model.dto.InfoScoreDto;
import com.project.model.entity.ApplyInfoEntity;
import com.project.model.entity.InfoScoreEntity;
import com.project.service.InfoScoreService;
import com.project.util.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author dsl
 * @description 针对表【info_score】的数据库操作Service实现
 * @createDate 2022-09-25 12:43:45
 */
@Service
public class InfoScoreServiceImpl extends ServiceImpl<InfoScoreMapper, InfoScoreEntity>
    implements InfoScoreService {
    @Resource
    private InfoScoreMapper infoScoreMapper;

    @Resource
    private ApplyInfoMapper applyInfoMapper;

    @Override
    public BaseResponse insert(InfoScoreEntity infoScoreEntity, HttpServletRequest request) {
        infoScoreEntity.setCreateTime(new Date());
        this.insertInfo(infoScoreEntity);
        int insert = infoScoreMapper.insert(infoScoreEntity);
        if (insert == 0){
            return ResultUtils.error(ErrorCode.SAVE_ERROR, "新增失败");
        }
        return ResultUtils.success(infoScoreEntity.getId(), "新增成功");
    }

    @Override
    public BaseResponse remove(Long id) {
        if (id == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int delete = infoScoreMapper.deleteById(id);
        if (delete == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败,该评分表不存在或已被删除");
        }
        return ResultUtils.success(id, "删除成功");
    }

    @Override
    public BaseResponse removes(Long[] visitIds) {
        int deletes = infoScoreMapper.deleteInfoScoreByVisitIds(visitIds);
        if (deletes == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败,该评分表不存在或已被删除");
        }
        return ResultUtils.success(deletes, "批量删除成功");
    }

    @Override
    public BaseResponse update(InfoScoreEntity infoScoreEntity) {
        if (infoScoreEntity.getId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        ApplyInfoEntity applyInfo = applyInfoMapper.selectById(infoScoreEntity.getVisitId());
        if (applyInfo == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "访问申请表不存在");
        }
        int update = infoScoreMapper.updateById(infoScoreEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改失败,该评分表不存在或已被删除");
        }
        return ResultUtils.success(infoScoreEntity.getId(), "修改成功");
    }

    @Override
    public BaseResponse selectById(Long id) {
        InfoScoreEntity infoScoreEntity = infoScoreMapper.selectById(id);
        return ResultUtils.success(infoScoreEntity, "查询详情成功");
    }

    @Override
    public BaseResponse selectByVisitId(Long visitId) {
        InfoScoreEntity infoScoreEntity = infoScoreMapper.selectInfoScoreByVisitId(visitId);
        return ResultUtils.success(infoScoreEntity, "查询详情成功");
    }

    @Override
    public BaseResponse search(InfoScoreDto infoScoreDto) {
        IPage<InfoScoreEntity> page = infoScoreMapper.selectInfoScore(infoScoreDto);
        return ResultUtils.success(page, "查询详情成功");
    }

    private InfoScoreEntity insertInfo(InfoScoreEntity infoScoreEntity){
        ApplyInfoEntity apply = applyInfoMapper.selectById(infoScoreEntity.getVisitId());
        if (apply == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "访问申请id不存在");
        }
        infoScoreEntity.setApplicantId(apply.getApplicantId());
        return infoScoreEntity;
    }
}
