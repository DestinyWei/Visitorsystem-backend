package com.project.service.impl;

import com.project.model.entity.SysOperLogEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AsyncLogService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLogEntity log) {

        String sql = "INSERT INTO sys_oper_log(title,business_type,method,STATUS,error_msg,oper_time) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{log.getTitle(),log.getBusinessType(),log.getMethod(),log.getStatus(),log.getErrorMsg(),new Date()});
    }
}