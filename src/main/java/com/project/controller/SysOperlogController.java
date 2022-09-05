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
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/operlog")
public class SysOperlogController {

    @Resource
    private SysOperLogService operLogService;

    @PostMapping("/search")
    @ResponseBody
    public BaseResponse search(SysOperLogDto sysOperLogDto)
    {
        return operLogService.selectOperLogList(sysOperLogDto);
    }

    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public BaseResponse remove(String ids)
    {
        return operLogService.deleteOperLogByIds(ids);
    }

    @GetMapping("/detail/{operId}")
    public BaseResponse detail(@PathVariable("operId") Long operId)
    {
        return operLogService.selectOperLogById(operId);
    }
    
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public BaseResponse clean()
    {
        operLogService.cleanOperLog();
        return ResultUtils.success("清空成功");
    }
}
