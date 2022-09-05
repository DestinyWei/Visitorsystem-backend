package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.model.entity.SysRoleEntity;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-21-22:24
 * @version:
 */
@Data
public class SysRoleDto extends Page<SysRoleEntity> {

    private Long roleId;
    private String roleName;
    private String roleKey;
    private String roleSort;
    private String dataScope;
    private String status;
    private String delFlag;
    private boolean flag = false;
    private Long[] menuIds;
    private Long[] deptIds;
    private Set<String> permissions;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;
}
