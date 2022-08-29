package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_role_resource
 */
@TableName(value ="sys_role_resource")
@Data
public class SysRoleResourceEntity implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 资源ID
     */
    private Long resourceId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限类型 1-可读可写 2-只读
     */
    private String permissionType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}