package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sys_dept
 */
@Data
public class SysDeptEntity implements Serializable {
    /**
     * 部门信息ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 部门编码
     */
    private String departmentCode;

    /**
     * 上级部门
     */
    private Long parentDepartmentId;

    /**
     * 部门类型
     */
    private String departmentType;

    /**
     * 所属单位
     */
    private String unit;

    /**
     * 级别 1-最高级别 以此类推
     */
    private String level;

    /**
     * 备注
     */
    private String departmentNote;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者
     */
    @TableField(exist = false)
    private String createUserName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}