package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.SysOperLogDto;
import com.project.model.enums.BusinessType;
import com.project.service.SysOperLogService;
import com.project.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 操作日志记录
 */
@Api(tags = "系统操作日志接口")
@RestController
@RequestMapping("/operlog")
public class SysOperlogController {

    @Resource
    private SysOperLogService operLogService;

    @ApiOperation(value = "日志查询")
    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysOperLogDto sysOperLogDto) {
        return operLogService.selectOperLogList(sysOperLogDto);
    }

    @ApiOperation(value = "日志删除")
    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public BaseResponse remove(@ApiParam(value = "操作日志Id组") String ids) {
        return operLogService.deleteOperLogByIds(ids);
    }

    @ApiOperation(value = "日志详情")
    @GetMapping("/detail/{operId}")
    public BaseResponse detail(@PathVariable("operId") @ApiParam(value = "操作日志Id") Long operId) {
        return operLogService.selectOperLogById(operId);
    }

    @ApiOperation(value = "日志清空")
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    public BaseResponse clean() {
        operLogService.cleanOperLog();
        return ResultUtils.success("清空成功");
    }
}
