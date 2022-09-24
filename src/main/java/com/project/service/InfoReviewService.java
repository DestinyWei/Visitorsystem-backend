package com.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.project.common.BaseResponse;
import com.project.model.dto.InfoReviewDto;
import com.project.model.entity.InfoReviewEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dsl
 * @description 针对表【info_review】的数据库操作Service
 * @createDate 2022-09-24 12:43:45
 */
public interface InfoReviewService extends IService<InfoReviewEntity> {
    /**
     * 审核表新增
     * @param infoReviewEntity
     * @param request
     * @return BaseResponse
     */
    BaseResponse insert(InfoReviewEntity infoReviewEntity, HttpServletRequest request);

    /**
     * 审核表删除
     * @param id
     * @return BaseResponse
     */
    BaseResponse remove(Long id);

    /**
     * 审核表批量删除
     * @param visitIds
     * @return BaseResponse
     */
    BaseResponse removes(Long[] visitIds);

    /**
     * 审核表修改
     * @param infoReviewEntity
     * @return BaseResponse
     */
    BaseResponse update(InfoReviewEntity infoReviewEntity);

    /**
     * 根据审查ID查询审核表
     * @param id
     * @return BaseResponse
     */
    BaseResponse selectById(Long id);

    /**
     * 根据访问申请ID查询审核表
     * @param visitId
     * @return BaseResponse
     */
    BaseResponse selectByVisitId(Long visitId);

    /**
     * 审核表模糊查询
     * @param infoReviewDto
     * @return BaseResponse
     */
    BaseResponse search(InfoReviewDto infoReviewDto);

}
