package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysMenuEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-09-05-17:41
 * @version:
 */
@ApiModel(value = "菜单权限请求类")
@Data
public class SysMenuDto extends Page<SysMenuEntity> implements Serializable {

    private static final long serialVersionUID = -3199201293046951486L;
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    @ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
    private String visible;
    @ApiModelProperty(value = "当前登录用户Id")
    private Long currentUserId;
}
