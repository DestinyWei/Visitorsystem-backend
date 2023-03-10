package com.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 */
@ApiModel(value = "菜单权限实体类")
@TableName("sys_menu")
@Data
public class SysMenuEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 菜单ID */
    @ApiModelProperty(value = "菜单Id")
    @TableId(type = IdType.AUTO)
    private Long menuId;

    /** 菜单名称 */
    @ApiModelProperty(value = "菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    private String menuName;

    /** 父菜单名称 */
    @ApiModelProperty(value = "父菜单名称")
    @TableField(exist = false)
    private String parentName;

    /** 父菜单ID */
    @ApiModelProperty(value = "父菜单Id")
    private Long parentId;

    /** 显示顺序 */
    @ApiModelProperty(value = "显示顺序")
    @NotBlank(message = "显示顺序不能为空")
    private String orderNum;

    /** 菜单URL */
    @ApiModelProperty(value = "菜单URL")
    @Size(min = 0, max = 200, message = "请求地址不能超过200个字符")
    private String url;

    /** 打开方式（menuItem页签 menuBlank新窗口） */
    @ApiModelProperty(value = "打开方式（menuItem页签 menuBlank新窗口）")
    private String target;

    /** 类型（M目录 C菜单 F按钮） */
    @ApiModelProperty(value = "类型（M目录 C菜单 F按钮）")
    @NotBlank(message = "菜单类型不能为空")
    private String menuType;

    /** 菜单状态（0显示 1隐藏） */
    @ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
    private String visible;

    /** 是否刷新（0刷新 1不刷新） */
    @ApiModelProperty(value = "是否刷新（0刷新 1不刷新）")
    private String isRefresh;

    /** 权限字符串 */
    @ApiModelProperty(value = "权限字符串")
    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    private String perms;

    /** 菜单图标 */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /** 子菜单 */
    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)
    private List<SysMenuEntity> children = new ArrayList<SysMenuEntity>();

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("menuId", getMenuId())
            .append("menuName", getMenuName())
            .append("parentId", getParentId())
            .append("orderNum", getOrderNum())
            .append("url", getUrl())
            .append("target", getTarget())
            .append("menuType", getMenuType())
            .append("visible", getVisible())
            .append("perms", getPerms())
            .append("icon", getIcon())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
