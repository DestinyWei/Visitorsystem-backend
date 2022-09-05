package com.project.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.entity.SysNoticeEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-31-11:16
 * @version:
 */
@Data
public class SysNoticeDto extends Page<SysNoticeEntity> {

    private Long noticeId;
    private String noticeTitle;
    private String noticeType;
    private String noticeContent;
    private String status;
    private String createBy;
}
