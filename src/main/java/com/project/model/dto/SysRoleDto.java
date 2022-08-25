package com.project.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.model.entity.SysRoleEntity;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-21-22:24
 * @version:
 */
@TableName("sys_role")
@Data
public class SysRoleDto extends Page<SysRoleEntity> {

    private String roleName;
    private String roleType;
    private Long createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
