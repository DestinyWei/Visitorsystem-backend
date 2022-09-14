package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.SysDeptDto;
import com.project.model.entity.SysDeptEntity;
import com.project.model.enums.BusinessType;
import com.project.service.SysDeptService;
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
 * @description: 部门接口
 * @author: weihaoming
 * @create: 2022-08-25-17:54
 * @version:
 */
@Api(tags = "系统部门接口")
@RestController
@RequestMapping("/dept")
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;

    @ApiOperation(value = "部门新增")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public BaseResponse insert(@Validated @RequestBody SysDeptEntity sysDeptEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysDeptService.insert(sysDeptEntity, request);
    }

    @ApiOperation(value = "部门删除")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public BaseResponse remove(@ApiParam(value = "部门Id") Long deptId, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysDeptService.remove(deptId);
    }

    @ApiOperation(value = "部门修改")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public BaseResponse update(@Validated @RequestBody SysDeptEntity sysDeptEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysDeptService.update(sysDeptEntity);
    }

    @ApiOperation(value = "部门查询")
    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysDeptDto sysDeptDto, HttpServletRequest request){
        Long userId = SecurityUtils.getLoginUserId(request);
        sysDeptDto.setCurrentUserId(userId);
        return sysDeptService.search(sysDeptDto);
    }

    @ApiOperation(value = "部门详情")
    @GetMapping("/detail/{deptId}")
    public BaseResponse detail(@PathVariable("deptId") @ApiParam(value = "部门Id") Long deptId){
        return sysDeptService.detail(deptId);
    }
}
