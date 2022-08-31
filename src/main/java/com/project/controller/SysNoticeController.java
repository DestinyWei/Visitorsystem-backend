package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.SysNoticeDto;
import com.project.model.entity.SysNoticeEntity;
import com.project.model.enums.BusinessType;
import com.project.service.SysNoticeService;
import com.project.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 公告 信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/notice")
public class SysNoticeController {
    @Resource
    private SysNoticeService sysNoticeService;

    /**
     * 查询公告详情
     */
    @GetMapping("/detail")
    public BaseResponse detail(Long noticeId)
    {
        return sysNoticeService.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     */
    @PostMapping("/search")
    @ResponseBody
    public BaseResponse search(SysNoticeDto sysNoticeDto){
        return sysNoticeService.selectNoticeList(sysNoticeDto);
    }

    /**
     * 新增保存公告
     */
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    @ResponseBody
    public BaseResponse insert(@Validated SysNoticeEntity sysNoticeEntity, HttpServletRequest request) {
        String userName = SecurityUtils.getLoginUser(request).getUserName();
        sysNoticeEntity.setCreateBy(userName);
        return sysNoticeService.insertNotice(sysNoticeEntity);
    }

    /**
     * 修改保存公告
     */
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public BaseResponse update(@Validated SysNoticeEntity sysNoticeEntity, HttpServletRequest request)
    {
        String userName = SecurityUtils.getLoginUser(request).getUserName();
        sysNoticeEntity.setCreateBy(userName);
        return sysNoticeService.updateNotice(sysNoticeEntity);
    }

    /**
     * 删除公告
     */
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public BaseResponse remove(String ids)
    {
        return sysNoticeService.deleteNoticeByIds(ids);
    }
}
