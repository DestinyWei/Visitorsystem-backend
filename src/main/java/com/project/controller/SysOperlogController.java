package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.SysOperLogDto;
import com.project.model.enums.BusinessType;
import com.project.service.SysOperLogService;
import com.project.util.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 操作日志记录
 */
@RestController
@RequestMapping("/operlog")
public class SysOperlogController {

    @Resource
    private SysOperLogService operLogService;

    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysOperLogDto sysOperLogDto) {
        return operLogService.selectOperLogList(sysOperLogDto);
    }

    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public BaseResponse remove(String ids) {
        return operLogService.deleteOperLogByIds(ids);
    }

    @GetMapping("/detail/{operId}")
    public BaseResponse detail(@PathVariable("operId") Long operId) {
        return operLogService.selectOperLogById(operId);
    }
    
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    public BaseResponse clean() {
        operLogService.cleanOperLog();
        return ResultUtils.success("清空成功");
    }
}
