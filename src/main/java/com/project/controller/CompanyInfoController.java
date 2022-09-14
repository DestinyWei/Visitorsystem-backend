package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.CompanyInfoDto;
import com.project.model.entity.CompanyInfoEntity;
import com.project.model.enums.BusinessType;
import com.project.service.CompanyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description: 公司信息接口
 * @author: weihaoming
 * @create: 2022-08-25-17:54
 * @version:
 */
@Api(tags = "公司信息接口")
@RestController
@RequestMapping("/company")
public class CompanyInfoController {

    @Resource
    private CompanyInfoService companyInfoService;

    @ApiOperation(value = "公司信息新增")
    @Log(title = "公司管理", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public BaseResponse insert(@Validated @RequestBody CompanyInfoEntity companyInfoEntity){
        return companyInfoService.insert(companyInfoEntity);
    }
    // TODO 校验公司名称是否存在
    @ApiOperation(value = "公司信息删除")
    @Log(title = "公司信息管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public BaseResponse remove(@ApiParam(value = "公司Id") Long companyId){
        return companyInfoService.remove(companyId);
    }

    @ApiOperation(value = "公司信息修改")
    @Log(title = "公司管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public BaseResponse update(@Validated @RequestBody CompanyInfoEntity companyInfoEntity){
        return companyInfoService.update(companyInfoEntity);
    }

    @ApiOperation(value = "公司信息查询")
    @PostMapping("/search")
    public BaseResponse search(@RequestBody CompanyInfoDto companyInfoDto){
        return companyInfoService.search(companyInfoDto);
    }

    @ApiOperation(value = "公司信息详情")
    @GetMapping("/detail/{companyId}")
    @ResponseBody
    public BaseResponse detail(@PathVariable("companyId") @ApiParam(value = "公司Id") Long companyId){
        return companyInfoService.detail(companyId);
    }
}
