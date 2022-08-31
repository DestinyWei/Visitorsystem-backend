package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysOperLogEntity;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-31-11:36
 * @version:
 */
@Data
public class SysOperLogDto extends Page<SysOperLogEntity> {

    private Long operId;
    private String title;
    private Integer businessType;
    private Integer[] businessTypes;
    private String method;
    private String requestMethod;
    private Integer operatorType;
    private String operName;
    private String deptName;
    private String operUrl;
    private String operIp;
    private String operLocation;
    private String operParam;
    private String jsonResult;
    private Integer status;
    private String errorMsg;
    private Date operTime;
}
