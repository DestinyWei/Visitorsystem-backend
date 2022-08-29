package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.model.entity.SysOperLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * 操作日志 数据层
 *
 * @author smalljop
 */
@Mapper
public interface SysOperLogMapper extends BaseMapper<SysOperLogEntity> {

    /**
     * 清空操作日志
     */
    @Update(" truncate table sys_oper_log")
    void cleanOperLog();
}
