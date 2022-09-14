package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.CompanyInfoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-29-12:36
 * @version:
 */
@ApiModel(value = "公司信息请求类")
@Data
public class CompanyInfoDto extends Page<CompanyInfoEntity> implements Serializable {

    private static final long serialVersionUID = 801109809249074755L;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "地区 如:四川省/成都市/双流区")
    private String companyRegion;
    @ApiModelProperty(value = "联系电话")
    private String companyPhone;
}
