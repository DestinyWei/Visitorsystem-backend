package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName sys_dept
 */
@ApiModel(value = "系统部门实体类")
@TableName("sys_dept")
@Data
public class SysDeptEntity implements Serializable {
    /**
     * 部门信息ID
     */
    @ApiModelProperty(value = "部门Id")
    @TableId(type = IdType.AUTO)
    private Long deptId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 20, message = "部门名称不能超过20个字符")
    private String deptName;

    /**
     * 部门编码
     */
    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    /**
     * 上级部门Id
     */
    @ApiModelProperty(value = "上级部门Id")
    private Long parentDeptId;

    /**
     * 部门状态（0正常 1停用）
     */
    @ApiModelProperty(value = "部门状态（0正常 1停用）")
    private String status;

    /**
     * 所属单位
     */
    @ApiModelProperty(value = "所属单位")
    private String unit;

    /**
     * 级别 1-最高级别 以此类推
     */
    @ApiModelProperty(value = "级别 1-最高级别 以此类推")
    private String level;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者Id")
    private Long createUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @TableField(exist = false)
    private String createUserName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}