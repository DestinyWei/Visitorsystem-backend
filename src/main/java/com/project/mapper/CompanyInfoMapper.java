package com.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.CompanyInfoDto;
import com.project.model.entity.CompanyInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Destiny
* @description 针对表【company_info】的数据库操作Mapper
* @createDate 2022-08-29 12:00:19
* @Entity generator.domain.CompanyInfo
*/
public interface CompanyInfoMapper extends BaseMapper<CompanyInfoEntity> {

    IPage<CompanyInfoEntity> selectCompany(CompanyInfoDto companyInfoDto);

}




