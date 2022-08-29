package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.BaseResponse;
import com.project.model.entity.SysOperLogEntity;
import com.project.service.SysOperLogService;
import com.project.util.QueryWrapperUtils;
import com.project.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录
 *
 * @author smalljop
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/monitor/operlog")
@Api(tags = "操作日志记录")
public class SysOperlogController {
    private final SysOperLogService operLogService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询日志记录")
    public BaseResponse queryPage(Page page, SysOperLogEntity operLog) {
        return ResultUtils.success(operLogService.page(page, QueryWrapperUtils.toSimpleQuery(operLog)), "分页查询日志记录成功");
    }

    @ApiOperation(value = "删除日志记录")
    @DeleteMapping("/{operIds}")
    public BaseResponse remove(@PathVariable List<Long> operIds) {
        return ResultUtils.success(operLogService.removeByIds(operIds), "删除日志记录成功");
    }

    @ApiOperation(value = "清空日志记录")
    @DeleteMapping("/clean")
    public BaseResponse clean() {
        operLogService.cleanOperLog();
        return ResultUtils.success("清空日志记录成功");
    }
}
