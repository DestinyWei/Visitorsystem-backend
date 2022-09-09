package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.SysOperLogDto;
import com.project.model.entity.SysOperLogEntity;

/**
 * 操作日志 数据层
 */
public interface SysOperLogMapper extends BaseMapper<SysOperLogEntity> {
    /**
     * 新增操作日志
     * 
     * @param sysOperLogEntity 操作日志对象
     */
    void insertOperlog(SysOperLogEntity sysOperLogEntity);

    /**
     * 查询系统操作日志集合
     * 
     * @param sysOperLogDto 操作日志对象
     * @return 操作日志集合
     */
    IPage<SysOperLogEntity> selectOperLogList(SysOperLogDto sysOperLogDto);
    
    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteOperLogByIds(String[] ids);
    
    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    SysOperLogEntity selectOperLogById(Long operId);
    
    /**
     * 清空操作日志
     */
    void cleanOperLog();
}
