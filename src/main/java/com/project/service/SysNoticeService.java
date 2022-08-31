package com.project.service;

import com.project.common.BaseResponse;
import com.project.model.dto.SysNoticeDto;
import com.project.model.entity.SysNoticeEntity;

/**
 * 公告 服务层
 * 
 * @author ruoyi
 */
public interface SysNoticeService
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    BaseResponse selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * 
     * @param sysNoticeDto 公告信息
     * @return 公告集合
     */
    BaseResponse selectNoticeList(SysNoticeDto sysNoticeDto);

    /**
     * 新增公告
     * 
     * @param sysNoticeEntity 公告信息
     * @return 结果
     */
    BaseResponse insertNotice(SysNoticeEntity sysNoticeEntity);

    /**
     * 修改公告
     * 
     * @param sysNoticeEntity 公告信息
     * @return 结果
     */
    BaseResponse updateNotice(SysNoticeEntity sysNoticeEntity);

    /**
     * 删除公告信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    BaseResponse deleteNoticeByIds(String ids);
}
