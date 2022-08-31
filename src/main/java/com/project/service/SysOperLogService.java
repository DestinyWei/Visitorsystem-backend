package com.project.service;

import com.project.common.BaseResponse;
import com.project.model.dto.SysOperLogDto;
import com.project.model.entity.SysOperLogEntity;

/**
 * 操作日志 服务层
 * 
 * @author ruoyi
 */
public interface SysOperLogService
{
    /**
     * 新增操作日志
     * 
     * @param sysOperLogEntity 操作日志对象
     */
    BaseResponse insertOperlog(SysOperLogEntity sysOperLogEntity);

    /**
     * 查询系统操作日志集合
     * 
     * @param sysOperLogDto 操作日志对象
     * @return 操作日志集合
     */
    BaseResponse selectOperLogList(SysOperLogDto sysOperLogDto);

    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    BaseResponse deleteOperLogByIds(String ids);

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    BaseResponse selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    BaseResponse cleanOperLog();
}
