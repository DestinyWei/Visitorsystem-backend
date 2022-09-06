package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.SysNoticeDto;
import com.project.model.entity.SysNoticeEntity;

import java.util.List;

/**
 * 公告 数据层
 * 
 * @author ruoyi
 */
public interface SysNoticeMapper extends BaseMapper<SysNoticeEntity> {
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    SysNoticeEntity selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * 
     * @param sysNoticeDto 公告信息
     * @return 公告集合
     */
    IPage<SysNoticeEntity> selectNoticeList(SysNoticeDto sysNoticeDto);

    /**
     * 新增公告
     * 
     * @param sysNoticeEntity 公告信息
     * @return 结果
     */
    int insertNotice(SysNoticeEntity sysNoticeEntity);

    /**
     * 修改公告
     * 
     * @param sysNoticeEntity 公告信息
     * @return 结果
     */
    int updateNotice(SysNoticeEntity sysNoticeEntity);

    /**
     * 批量删除公告
     * 
     * @param noticeIds 需要删除的数据ID
     * @return 结果
     */
    int deleteNoticeByIds(String[] noticeIds);
}