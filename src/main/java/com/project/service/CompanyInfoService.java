package com.project.service;

import com.project.common.BaseResponse;
import com.project.model.dto.CompanyInfoDto;
import com.project.model.entity.CompanyInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Destiny
* @description 针对表【company_info】的数据库操作Service
* @createDate 2022-08-29 12:00:19
*/
public interface CompanyInfoService extends IService<CompanyInfoEntity> {

    /**
     * 公司信息新增
     * @param companyInfoEntity
     * @return BaseResponse
     */
    BaseResponse insert(CompanyInfoEntity companyInfoEntity);

    /**
     * 公司信息删除
     * @param companyInfoEntity
     * @return BaseResponse
     */
    BaseResponse remove(CompanyInfoEntity companyInfoEntity);

    /**
     * 公司信息修改
     * @param companyInfoEntity
     * @return BaseResponse
     */
    BaseResponse update(CompanyInfoEntity companyInfoEntity);

    /**
     * 公司信息查询
     * @param companyInfoDto
     * @return BaseResponse
     */
    BaseResponse search(CompanyInfoDto companyInfoDto);
}
