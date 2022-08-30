package com.project.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.BaseResponse;
import com.project.model.entity.SysDictDataEntity;
import com.project.service.SysDictDataService;
import com.project.service.SysDictTypeService;
import com.project.util.QueryWrapperUtils;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author smalljop
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/dict/data")
@Api(tags = "数据字典信息")
public class SysDictDataController {
    private final SysDictDataService dictDataService;

    private final SysDictTypeService dictTypeService;

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public BaseResponse queryPage(Page page, SysDictDataEntity dictData) {
        return ResultUtils.success(dictDataService.page(page, QueryWrapperUtils.toSimpleQuery(dictData)), "分页查询成功");
    }

    /**
     * 查询字典数据详细
     */
    @GetMapping(value = "/{dictCode}")
    @ApiOperation(value = "查询字典数据详细")
    public BaseResponse getInfo(@PathVariable Long dictCode) {
        return ResultUtils.success(dictDataService.getById(dictCode), "查询字典数据详细成功");
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    @ApiOperation(value = "根据字典类型查询字典数据信息")
    public BaseResponse dictType(@PathVariable String dictType) {
        List<SysDictDataEntity> data = dictTypeService.getDictDataByType(dictType);
        if (ObjectUtil.isNull(data)) {
            data = new ArrayList<>();
        }
        return ResultUtils.success(data, "根据字典类型查询字典数据信息成功");
    }

    /**
     * 新增字典类型
     */
    @ApiOperation(value = "新增字典类型")
    @PostMapping
    public BaseResponse save(@Validated @RequestBody SysDictDataEntity dict, HttpServletRequest request) {
        dict.setCreateBy(SecurityUtils.getUsername(request));
        return ResultUtils.success(dictDataService.saveDictData(dict), "新增字典类型成功");
    }

    /**
     * 修改保存字典类型
     */
    @ApiOperation(value = "修改保存字典类型")
    @PutMapping
    public BaseResponse update(@Validated @RequestBody SysDictDataEntity dict, HttpServletRequest request) {
        dict.setUpdateBy(SecurityUtils.getUsername(request));
        return ResultUtils.success(dictDataService.updateDictData(dict), "修改保存字典类型成功");
    }

    /**
     * 删除字典类型
     */
    @ApiOperation(value = "删除字典类型")
    @DeleteMapping("/{dictCodes}")
    public BaseResponse delete(@PathVariable List<Long> dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return ResultUtils.success("删除字典类型成功");
    }
}
