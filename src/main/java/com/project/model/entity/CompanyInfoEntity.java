package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName company_info
 */
@TableName(value ="company_info")
@Data
public class CompanyInfoEntity implements Serializable {
    /**
     * 公司ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 地区 如:四川省/成都市/双流区
     */
    private String companyRegion;

    /**
     * 详细地址
     */
    private String companyAddress;

    /**
     * 联系电话
     */
    private String companyPhone;

    /**
     * 企业信息
     */
    private String companyInfo;

    /**
     * 介绍
     */
    private String companyIntroduction;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}