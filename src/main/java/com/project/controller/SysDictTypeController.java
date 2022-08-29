package com.project.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.BaseResponse;
import com.project.constant.SysUserConstant;
import com.project.model.entity.SysDictTypeEntity;
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
import java.util.List;

/**
 * 数据字典信息
 *
 * @author smalljop
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/dict/type")
@Api(tags = "数据字典信息")
public class SysDictTypeController {
    private final SysDictTypeService dictTypeService;

    @ApiOperation(value = "分页查询数据字典信息")
    @GetMapping("/page")
    public BaseResponse queryPage(Page page, SysDictTypeEntity dictType) {
        return ResultUtils.success(dictTypeService.page(page, QueryWrapperUtils.toSimpleQuery(dictType)), "分页查询数据字典信息成功");
    }

    @ApiOperation(value = "查询所有数据字典信息")
    @GetMapping("all")
    public BaseResponse<List<SysDictTypeEntity>> allDict() {
        return ResultUtils.success(dictTypeService.allDict(), "查询所有数据字典信息成功");
    }

    /**
     * 查询字典类型详细
     */
    @ApiOperation(value = "查询字典类型详细")
    @GetMapping(value = "/{dictId}")
    public BaseResponse getInfo(@PathVariable Long dictId) {
        return ResultUtils.success(dictTypeService.getById(dictId), "查询字典类型详细成功");
    }

    /**
     * 新增字典类型
     */
    @ApiOperation(value = "新增字典类型")
    @PostMapping
    public BaseResponse save(@Validated @RequestBody SysDictTypeEntity dict, HttpServletRequest request) {
        if (SysUserConstant.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return ResultUtils.error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(SecurityUtils.getUsername(request));
        return ResultUtils.success(dictTypeService.saveDictType(dict), "新增字典类型成功");
    }

    /**
     * 修改字典类型
     */
    @ApiOperation(value = "修改字典类型")
    @PutMapping
    public BaseResponse update(@Validated @RequestBody SysDictTypeEntity dict, HttpServletRequest request) {
        if (SysUserConstant.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return ResultUtils.error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(SecurityUtils.getUsername(request));
        return ResultUtils.success(dictTypeService.updateDictType(dict), "修改字典类型成功");
    }

    /**
     * 删除字典类型
     */
    @ApiOperation(value = "删除字典类型")
    @DeleteMapping("/{dictIds}")
    public BaseResponse delete(@PathVariable List<Long> dictIds) {
        dictTypeService.removeByIds(dictIds);
        return ResultUtils.success("删除字典类型成功");
    }

    /**
     * 刷新字典缓存
     */
    @ApiOperation(value = "刷新字典缓存")
    @DeleteMapping("/refreshCache")
    public BaseResponse refreshCache() {
        dictTypeService.resetDictCache();
        return ResultUtils.success("刷新字典缓存成功");
    }

    /**
     * 获取字典选择框列表
     */
    @ApiOperation(value = "获取字典选择框列表")
    @GetMapping("/optionselect")
    public BaseResponse optionselect() {
        List<SysDictTypeEntity> dictTypes = dictTypeService.list();
        return ResultUtils.success(dictTypes);
    }
}
