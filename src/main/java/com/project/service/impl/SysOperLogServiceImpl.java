package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.mapper.SysOperLogMapper;
import com.project.model.dto.SysOperLogDto;
import com.project.model.entity.SysOperLogEntity;
import com.project.service.SysOperLogService;
import com.project.text.Convert;
import com.project.util.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 操作日志 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysOperLogServiceImpl implements SysOperLogService
{
    @Resource
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     *
     * @param sysOperLogEntity 操作日志对象
     */
    @Override
    public BaseResponse insertOperlog(SysOperLogEntity sysOperLogEntity)
    {
        operLogMapper.insertOperlog(sysOperLogEntity);
        return ResultUtils.success("新增操作日志成功");
    }

    /**
     * 查询系统操作日志集合
     *
     * @param sysOperLogDto 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public BaseResponse selectOperLogList(SysOperLogDto sysOperLogDto)
    {
        IPage<SysOperLogEntity> page = operLogMapper.selectOperLogList(sysOperLogDto);
        return ResultUtils.success(page, "查询成功");
    }

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public BaseResponse deleteOperLogByIds(String ids)
    {
        int delete = operLogMapper.deleteOperLogByIds(Convert.toStrArray(ids));
        if (delete == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败");
        }
        return ResultUtils.success(delete, "删除成功");
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public BaseResponse selectOperLogById(Long operId)
    {
        SysOperLogEntity log = operLogMapper.selectOperLogById(operId);
        return ResultUtils.success(log, "查询成功");
    }

    /**
     * 清空操作日志
     */
    @Override
    public BaseResponse cleanOperLog()
    {
        operLogMapper.cleanOperLog();
        return ResultUtils.success("清空操作日志成功");
    }
}
