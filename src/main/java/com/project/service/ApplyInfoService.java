package com.project.service;

import com.project.common.BaseResponse;
import com.project.model.dto.ApplyInfoDto;
import com.project.model.entity.ApplyInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author Destiny
* @description 针对表【apply_info】的数据库操作Service
* @createDate 2022-08-29 12:43:45
*/
public interface ApplyInfoService extends IService<ApplyInfoEntity> {

    /**
     * 访问申请新增
     * @param applyInfoEntity
     * @param request
     * @return BaseResponse
     */
    BaseResponse insert(ApplyInfoEntity applyInfoEntity, HttpServletRequest request);

    /**
     * 访问申请删除
     * @param applyInfoEntity
     * @return BaseResponse
     */
    BaseResponse remove(ApplyInfoEntity applyInfoEntity);

    /**
     * 访问申请修改
     * @param applyInfoEntity
     * @return BaseResponse
     */
    BaseResponse update(ApplyInfoEntity applyInfoEntity);

    /**
     * 访问申请查询
     * @param applyInfoDto
     * @return BaseResponse
     */
    BaseResponse search(ApplyInfoDto applyInfoDto);

}
