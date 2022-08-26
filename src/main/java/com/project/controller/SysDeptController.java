package com.project.controller;

import com.project.common.BaseResponse;
import com.project.model.dto.SysDeptDto;
import com.project.model.entity.SysDeptEntity;
import com.project.service.SysDeptService;
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
 * @description: 部门接口
 * @author: weihaoming
 * @create: 2022-08-25-17:54
 * @version:
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody SysDeptEntity sysDeptEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysDeptService.insert(sysDeptEntity, request);
    }

    @PostMapping("/remove")
    public BaseResponse remove(@RequestBody SysDeptEntity sysDeptEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysDeptService.remove(sysDeptEntity);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody SysDeptEntity sysDeptEntity, HttpServletRequest request){
        boolean admin = SecurityUtils.isAdmin(request);
        if (!admin){
            return ResultUtils.error(NO_AUTH, "无权限");
        }
        return sysDeptService.update(sysDeptEntity);
    }

    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysDeptDto sysDeptDto){
        return sysDeptService.search(sysDeptDto);
    }
}
