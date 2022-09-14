package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.SysResourceDto;
import com.project.model.entity.SysResourceEntity;
import com.project.model.enums.BusinessType;
import com.project.service.SysResourceService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.project.common.ErrorCode.NO_AUTH;

/**
 * @description: 系统资源接口
 * @author: weihaoming
 * @create: 2022-08-25-17:54
 * @version:
 */
@Api(tags = "系统资源接口")
@RestController
@RequestMapping("/resource")
public class SysResourceController {

    @Resource
    private SysResourceService sysResourceService;

    @ApiOperation(value = "资源新增")
    @Log(title = "资源管理", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public BaseResponse insert(@Validated @RequestBody SysResourceEntity sysResourceEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysResourceService.insert(sysResourceEntity, request);
    }

    @ApiOperation(value = "资源删除")
    @Log(title = "资源管理", businessType = BusinessType.DELETE)
    @GetMapping("/remove")
    public BaseResponse remove(@ApiParam(value = "系统资源Id") Long resourceId, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysResourceService.remove(resourceId);
    }

    @ApiOperation(value = "资源修改")
    @Log(title = "资源管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public BaseResponse update(@Validated @RequestBody SysResourceEntity sysResourceEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysResourceService.update(sysResourceEntity);
    }

    @ApiOperation(value = "资源查询")
    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysResourceDto sysResourceDto){
        return sysResourceService.search(sysResourceDto);
    }
}
