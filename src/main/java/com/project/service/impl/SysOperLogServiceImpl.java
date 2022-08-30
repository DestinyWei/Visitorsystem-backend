package com.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.mapper.SysOperLogMapper;
import com.project.model.entity.SysOperLogEntity;
import com.project.service.SysOperLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 操作日志 服务层处理
 *
 * @author smalljop
 */
@Service
@RequiredArgsConstructor
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLogEntity> implements SysOperLogService {

    @Override
    public void cleanOperLog() {
        baseMapper.cleanOperLog();
    }
}
