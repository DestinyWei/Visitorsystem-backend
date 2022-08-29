package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.CompanyInfoEntity;
import lombok.Data;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-29-12:36
 * @version:
 */
@Data
public class CompanyInfoDto extends Page<CompanyInfoEntity> {

    private String companyName;
    private String companyRegion;
    private String companyPhone;
}
