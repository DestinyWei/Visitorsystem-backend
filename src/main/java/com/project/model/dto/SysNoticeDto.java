package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysNoticeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: weihaoming
 * @create: 202208-31-11:16
 * @version:
 */
@ApiModel(value = "公告通知请求类")
@Data
public class SysNoticeDto extends Page<SysNoticeEntity> implements Serializable {

    private static final long serialVersionUID = -2520638856443661998L;
    @ApiModelProperty(value = "公告标题")
    private String noticeTitle;
    @ApiModelProperty(value = "公告类型（1通知 2公告）")
    private String noticeType;
    @ApiModelProperty(value = "公告状态（0正常 1关闭）")
    private String status;
    @ApiModelProperty(value = "创建者")
    private String createBy;
}
