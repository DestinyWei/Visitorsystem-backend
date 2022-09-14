package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysDictDataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-09-01-11:07
 * @version:
 */
@ApiModel(value = "字典数据请求类")
@Data
public class SysDictDataDto extends Page<SysDictDataEntity> implements Serializable {

    private static final long serialVersionUID = -254828971791398390L;
    @ApiModelProperty(value = "字典标签")
    private String dictLabel;
    @ApiModelProperty(value = "字典类型")
    private String dictType;
    @ApiModelProperty(value = "状态（0正常 1停用）")
    private String status;
}
