package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName company_info
 */
@ApiModel(value = "公司信息实体类")
@TableName(value ="company_info")
@Data
public class CompanyInfoEntity implements Serializable {
    /**
     * 公司ID
     */
    @ApiModelProperty(value = "公司Id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    @NotBlank(message = "公司名称不能为空")
    @Size(min = 0, max = 20, message = "公司名称不能超过20个字符")
    private String companyName;

    /**
     * 地区 如:四川省/成都市/双流区
     */
    @ApiModelProperty(value = "地区 如:四川省/成都市/双流区")
    @NotBlank(message = "地区不能为空")
    private String companyRegion;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    @NotBlank(message = "详细地址不能为空")
    @Size(min = 0, max = 100, message = "详细地址不能超过100个字符")
    private String companyAddress;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    @NotBlank(message = "联系电话不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$",
            message = "手机号码输入有误")
    private String companyPhone;

    /**
     * 企业信息
     */
    @ApiModelProperty(value = "企业信息")
    private String companyInfo;

    /**
     * 介绍
     */
    @ApiModelProperty(value = "介绍")
    private String companyIntroduction;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}