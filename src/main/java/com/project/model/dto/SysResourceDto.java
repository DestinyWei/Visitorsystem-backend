package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysResourceEntity;
import lombok.Data;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-26-16:43
 * @version:
 */
@Data
public class SysResourceDto extends Page<SysResourceEntity> {

    private String dataType;
    private Long parentResourceId;
    private String resourceType;
    private String resourceName;
    private Integer sort;
    private String resourceCode;
    private Integer authorizationStatus;
    private Integer status;
}
