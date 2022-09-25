package com.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.project.common.BaseResponse;
import com.project.model.dto.InfoScoreDto;
import com.project.model.entity.InfoScoreEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dsl
 * @description 针对表【info_score】的数据库操作Service
 * @createDate 2022-09-25 12:43:45
 */
public interface InfoScoreService extends IService<InfoScoreEntity> {
    /**
     * 评分表新增
     * @param infoScoreEntity
     * @param request
     * @return BaseResponse
     */
    BaseResponse insert(InfoScoreEntity infoScoreEntity, HttpServletRequest request);

    /**
     * 评分表删除
     * @param id
     * @return BaseResponse
     */
    BaseResponse remove(Long id);

    /**
     * 评分表根据访问申请id批量删除
     * @param visitIds
     * @return BaseResponse
     */
    BaseResponse removes(Long[] visitIds);

    /**
     * 评分表修改
     * @param infoScoreEntity
     * @return BaseResponse
     */
    BaseResponse update(InfoScoreEntity infoScoreEntity);

    /**
     * 根据评分ID查询评分表
     * @param id
     * @return BaseResponse
     */
    BaseResponse selectById(Long id);

    /**
     * 根据访问申请ID查询评分表
     * @param visitId
     * @return BaseResponse
     */
    BaseResponse selectByVisitId(Long visitId);

    /**
     * 评分表模糊查询
     * @param infoScoreDto
     * @return BaseResponse
     */
    BaseResponse search(InfoScoreDto infoScoreDto);
}
