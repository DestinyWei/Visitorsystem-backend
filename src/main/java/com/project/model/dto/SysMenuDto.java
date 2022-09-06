package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysMenuEntity;
import lombok.Data;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-09-05-17:41
 * @version:
 */
@Data
public class SysMenuDto extends Page<SysMenuEntity> {

    private Long menuId;
    private String menuName;
    private String parentName;
    private Long parentId;
    private String orderNum;
    private String url;
    private String target;
    private String menuType;
    private String visible;
    private String isRefresh;
    private String perms;
    private String icon;
    private String remark;
    private String createBy;
    private Long currentUserId;
}
