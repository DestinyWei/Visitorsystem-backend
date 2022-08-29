package com.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.model.entity.SysOperLogEntity;

/**
 * 操作日志 服务层
 *
 * @author smalljop
 */
public interface SysOperLogService extends IService<SysOperLogEntity> {


    /**
     * 清空操作日志
     */
    void cleanOperLog();
}
