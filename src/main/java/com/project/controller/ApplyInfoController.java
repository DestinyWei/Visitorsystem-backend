package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.ApplyInfoDto;
import com.project.model.entity.ApplyInfoEntity;
import com.project.model.enums.BusinessType;
import com.project.service.ApplyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 访问申请接口
 * @author: weihaoming
 * @create: 2022-08-25-17:54
 * @version:
 */
@Api(tags = "访问申请接口")
@RestController
@RequestMapping("/apply")
public class ApplyInfoController {

    @Resource
    private ApplyInfoService applyInfoService;

    @ApiOperation(value = "访问申请新增")
    @PostMapping("/insert")
    public BaseResponse insert(@Validated @RequestBody ApplyInfoEntity applyInfoEntity, HttpServletRequest request){
        return applyInfoService.insert(applyInfoEntity, request);
    }

    @ApiOperation(value = "访问申请删除")
    @Log(title = "访问申请管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public BaseResponse remove(@ApiParam(value = "访问申请Id") Long applyId){
        return applyInfoService.remove(applyId);
    }

    // TODO 校验名称是否唯一
    @ApiOperation(value = "访问申请修改")
    @PostMapping("/update")
    public BaseResponse update(@Validated @RequestBody ApplyInfoEntity applyInfoEntity){
        return applyInfoService.update(applyInfoEntity);
    }

    @ApiOperation(value = "访问申请查询")
    @PostMapping("/search")
    public BaseResponse search(@RequestBody ApplyInfoDto applyInfoDto){
        return applyInfoService.search(applyInfoDto);
    }

    @ApiOperation(value = "访问申请详情")
    @GetMapping("/detail/{applyId}")
    @ResponseBody
    public BaseResponse detail(@PathVariable("applyId") @ApiParam(value = "访问申请Id") Long applyId){
        return applyInfoService.detail(applyId);
    }
}
