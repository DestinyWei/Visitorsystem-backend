package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName role_dept
 */
@TableName(value ="role_dept")
@Data
public class SysRoleDeptEntity implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long departmentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}