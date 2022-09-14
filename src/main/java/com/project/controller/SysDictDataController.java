package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.model.dto.SysDictDataDto;
import com.project.model.entity.SysDictDataEntity;
import com.project.model.enums.BusinessType;
import com.project.service.SysDictDataService;
import com.project.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 数据字典信息
 */
@Api(tags = "数据字典数据接口")
@RestController
@RequestMapping("/dict/data")
public class SysDictDataController{

    @Resource
    private SysDictDataService dictDataService;

    @ApiOperation(value = "字典数据查询")
    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysDictDataDto sysDictDataDto){
        return dictDataService.selectDictDataList(sysDictDataDto);
    }

    /**
     * 新增保存字典类型
     */
    @ApiOperation(value = "字典数据新增")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public BaseResponse insert(@Validated @RequestBody SysDictDataEntity sysDictDataEntity, HttpServletRequest request) {
        String username = SecurityUtils.getUsername(request);
        sysDictDataEntity.setCreateBy(username);
        sysDictDataEntity.setCreateTime(new Date());
        return dictDataService.insertDictData(sysDictDataEntity);
    }

    /**
     * 修改保存字典类型
     */
    @ApiOperation(value = "字典数据修改")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public BaseResponse update(@Validated @RequestBody SysDictDataEntity sysDictDataEntity, HttpServletRequest request) {
        String username = SecurityUtils.getUsername(request);
        sysDictDataEntity.setUpdateBy(username);
        sysDictDataEntity.setUpdateTime(new Date());
        return dictDataService.updateDictData(sysDictDataEntity);
    }

    @ApiOperation(value = "字典数据删除")
    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public BaseResponse remove(@ApiParam(value = "字典数据Id组") String ids) {
        return dictDataService.deleteDictDataByIds(ids);
    }
}
