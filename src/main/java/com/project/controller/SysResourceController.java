package com.project.controller;

import com.project.common.BaseResponse;
import com.project.model.dto.SysResourceDto;
import com.project.model.entity.SysResourceEntity;
import com.project.service.SysResourceService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.project.common.ErrorCode.NO_AUTH;

/**
 * @description: 系统资源接口
 * @author: weihaoming
 * @create: 2022-08-25-17:54
 * @version:
 */
@RestController
@RequestMapping("/resource")
public class SysResourceController {

    @Resource
    private SysResourceService sysResourceService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody SysResourceEntity sysResourceEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysResourceService.insert(sysResourceEntity, request);
    }

    @PostMapping("/remove")
    public BaseResponse remove(@RequestBody SysResourceEntity sysResourceEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysResourceService.remove(sysResourceEntity);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody SysResourceEntity sysResourceEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysResourceService.update(sysResourceEntity);
    }

    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysResourceDto sysResourceDto){
        return sysResourceService.search(sysResourceDto);
    }
}
