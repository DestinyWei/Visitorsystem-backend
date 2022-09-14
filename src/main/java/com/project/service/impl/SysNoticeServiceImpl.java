package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.mapper.SysNoticeMapper;
import com.project.model.dto.SysNoticeDto;
import com.project.model.entity.SysNoticeEntity;
import com.project.service.SysNoticeService;
import com.project.util.text.Convert;
import com.project.util.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 公告 服务层实现
 * @date 2018-06-25
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {
    @Resource
    private SysNoticeMapper noticeMapper;

    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public BaseResponse selectNoticeById(Long noticeId) {
        SysNoticeEntity sysNoticeEntity = noticeMapper.selectNoticeById(noticeId);
        return ResultUtils.success(sysNoticeEntity, "查询公告信息成功");
    }

    /**
     * 查询公告列表
     * 
     * @param sysNoticeDto 公告信息
     * @return 公告集合
     */
    @Override
    public BaseResponse selectNoticeList(SysNoticeDto sysNoticeDto) {
        IPage<SysNoticeEntity> sysNoticeEntities = noticeMapper.selectNoticeList(sysNoticeDto);
        return ResultUtils.success(sysNoticeEntities, "查询公告列表成功");
    }

    /**
     * 新增公告
     * 
     * @param sysNoticeEntity 公告信息
     * @return 结果
     */
    @Override
    public BaseResponse insertNotice(SysNoticeEntity sysNoticeEntity) {
        int insert = noticeMapper.insertNotice(sysNoticeEntity);
        if (insert == 0){
            return ResultUtils.error(ErrorCode.SAVE_ERROR, "新增失败");
        }
        return ResultUtils.success(sysNoticeEntity.getNoticeId(), "新增成功");
    }

    /**
     * 修改公告
     * 
     * @param sysNoticeEntity 公告信息
     * @return 结果
     */
    @Override
    public BaseResponse updateNotice(SysNoticeEntity sysNoticeEntity) {
        if (sysNoticeEntity.getNoticeId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR,"Id为空");
        }
        int update = noticeMapper.updateNotice(sysNoticeEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改失败,该公告不存在或已被删除");
        }
        return ResultUtils.success(sysNoticeEntity.getNoticeId(), "修改成功");
    }

    /**
     * 删除公告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public BaseResponse deleteNoticeByIds(String ids) {
        int delete = noticeMapper.deleteNoticeByIds(Convert.toStrArray(ids));
        if (delete == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败,,该公告不存在或已被删除");
        }
        return ResultUtils.success("删除成功");
    }
}
