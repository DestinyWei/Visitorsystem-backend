package com.project.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysDeptEntity;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-26-15:58
 * @version:
 */
@Data
public class SysDeptDto extends Page<SysDeptEntity> {

    private String departmentName;
    private String departmentCode;
    private Long parentDepartmentId;
    private String departmentType;
    private String unit;
    private String level;
}
