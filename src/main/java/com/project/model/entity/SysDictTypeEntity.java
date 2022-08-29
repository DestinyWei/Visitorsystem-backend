package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 字典类型表 sys_dict_type
 *
 * @author smalljop
 */
@Data
@TableName("sys_dict_type")
public class SysDictTypeEntity extends SysBaseEntity {


    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    @Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
    private String dictName;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    private String remark;

    /**
     * SysDictEntity
     */
    @TableField(exist = false)
    private List<SysDictDataEntity> sysDictDataEntityList;


}
