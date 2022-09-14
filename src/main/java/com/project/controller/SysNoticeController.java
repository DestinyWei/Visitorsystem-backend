package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.SysNoticeDto;
import com.project.model.entity.SysNoticeEntity;
import com.project.model.enums.BusinessType;
import com.project.service.SysNoticeService;
import com.project.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 公告 信息操作处理
 */
@Api(tags = "公告通知接口")
@RestController
@RequestMapping("/notice")
public class SysNoticeController {
    @Resource
    private SysNoticeService sysNoticeService;

    /**
     * 查询公告详情
     */
    @ApiOperation(value = "公告详情")
    @GetMapping("/detail")
    public BaseResponse detail(@ApiParam(value = "公告Id") Long noticeId) {
        return sysNoticeService.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     */
    @ApiOperation(value = "公告查询")
    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysNoticeDto sysNoticeDto){
        return sysNoticeService.selectNoticeList(sysNoticeDto);
    }

    /**
     * 新增保存公告
     */
    @ApiOperation(value = "公告新增")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public BaseResponse insert(@Validated @RequestBody SysNoticeEntity sysNoticeEntity, HttpServletRequest request) {
        String userName = SecurityUtils.getLoginUser(request).getUserName();
        sysNoticeEntity.setCreateBy(userName);
        sysNoticeEntity.setCreateTime(new Date());
        return sysNoticeService.insertNotice(sysNoticeEntity);
    }

    /**
     * 修改保存公告
     */
    @ApiOperation(value = "公告修改")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public BaseResponse update(@Validated @RequestBody SysNoticeEntity sysNoticeEntity, HttpServletRequest request) {
        String userName = SecurityUtils.getLoginUser(request).getUserName();
        sysNoticeEntity.setUpdateBy(userName);
        sysNoticeEntity.setUpdateTime(new Date());
        return sysNoticeService.updateNotice(sysNoticeEntity);
    }

    /**
     * 删除公告
     */
    @ApiOperation(value = "公告删除")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public BaseResponse remove(@ApiParam(value = "公告Id组") String ids) {
        return sysNoticeService.deleteNoticeByIds(ids);
    }
}
