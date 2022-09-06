package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysDictDataEntity;
import lombok.Data;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-09-01-11:07
 * @version:
 */
@Data
public class SysDictDataDto extends Page<SysDictDataEntity> {

    private Long dictCode;
    private Long dictSort;
    private String dictLabel;
    private String dictValue;
    private String dictType;
    private String cssClass;
    private String listClass;
    private String isDefault;
    private String status;
}
