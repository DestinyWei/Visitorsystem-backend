package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.model.entity.SysRoleEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-21-22:24
 * @version:
 */
@ApiModel(value = "系统角色请求类")
@Data
public class SysRoleDto extends Page<SysRoleEntity> implements Serializable {

    private static final long serialVersionUID = -457938079615281258L;
    @ApiModelProperty(value = "角色Id")
    private Long roleId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色权限")
    private String roleKey;
    @ApiModelProperty(value = "角色排序")
    private String roleSort;
    @ApiModelProperty(value = "数据权限")
    private String dataScope;
    @ApiModelProperty(value = "角色状态（0正常 1停用）")
    private String status;
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;
    @ApiModelProperty(value = "用户是否存在此角色标识 默认不存在")
    private boolean flag = false;
    @ApiModelProperty(value = "菜单组")
    private Long[] menuIds;
    @ApiModelProperty(value = "部门组")
    private Long[] deptIds;
    @ApiModelProperty(value = "角色菜单权限")
    private Set<String> permissions;
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date beginTime;
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;
}
