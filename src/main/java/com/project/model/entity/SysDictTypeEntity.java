package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 字典类型表 sys_dict_type
 */
@ApiModel(value = "字典类型实体类")
@TableName("sys_dict_type")
@Data
public class SysDictTypeEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 字典主键 */
    @ApiModelProperty(value = "字典类型Id")
    @TableId(type = IdType.AUTO)
    private Long dictId;

    /** 字典名称 */
    @ApiModelProperty(value = "字典名称")
    @NotBlank(message = "字典名称不能为空")
    @Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
    private String dictName;

    /** 字典类型 */
    @ApiModelProperty(value = "字典类型")
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
    @Pattern(regexp = "^[a-z][a-z0-9_]*$", message = "字典类型必须以字母开头，且只能为（小写字母，数字，下滑线）")
    private String dictType;

    /** 状态（0正常 1停用） */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    private String status;

    /** 开始时间 */
    @ApiModelProperty(value = "开始时间")
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date beginTime;

    /** 结束时间 */
    @ApiModelProperty(value = "结束时间")
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dictId", getDictId())
            .append("dictName", getDictName())
            .append("dictType", getDictType())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
