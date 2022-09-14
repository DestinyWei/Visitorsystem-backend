package com.project.controller;

import com.project.aop.annotation.Log;
import com.project.common.BaseResponse;
import com.project.constant.SysUserConstants;
import com.project.model.dto.SysDictTypeDto;
import com.project.model.entity.SysDictTypeEntity;
import com.project.model.entity.Ztree;
import com.project.model.enums.BusinessType;
import com.project.service.SysDictTypeService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 数据字典信息
 */
@Api(tags = "数据字典类型接口")
@RestController
@RequestMapping("/dict")
public class SysDictTypeController{

    @Resource
    private SysDictTypeService dictTypeService;

    @ApiOperation(value = "数据字典类型查询")
    @PostMapping("/search")
    public BaseResponse search(@RequestBody SysDictTypeDto sysDictTypeDto){
        return dictTypeService.selectDictTypeList(sysDictTypeDto);
    }

    /**
     * 新增保存字典类型
     */
    @ApiOperation(value = "字典类型新增")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public BaseResponse insert(@Validated @RequestBody SysDictTypeEntity sysDictTypeEntity, HttpServletRequest request) {
        if (SysUserConstants.DICT_TYPE_NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(sysDictTypeEntity))) {
            return ResultUtils.error("新增字典'" + sysDictTypeEntity.getDictName() + "'失败，字典类型已存在");
        }
        String username = SecurityUtils.getUsername(request);
        sysDictTypeEntity.setCreateBy(username);
        sysDictTypeEntity.setCreateTime(new Date());
        return dictTypeService.insertDictType(sysDictTypeEntity);
    }

    /**
     * 修改保存字典类型
     */
    @ApiOperation(value = "字典类型修改")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public BaseResponse update(@Validated @RequestBody SysDictTypeEntity sysDictTypeEntity, HttpServletRequest request) {
        if (SysUserConstants.DICT_TYPE_NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(sysDictTypeEntity))) {
            return ResultUtils.error("修改字典'" + sysDictTypeEntity.getDictName() + "'失败，字典类型已存在");
        }
        String username = SecurityUtils.getUsername(request);
        sysDictTypeEntity.setUpdateBy(username);
        sysDictTypeEntity.setUpdateTime(new Date());
        return dictTypeService.updateDictType(sysDictTypeEntity);
    }

    /**
     * 删除字典类型
     */
    @ApiOperation(value = "字典类型删除")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public BaseResponse remove(@ApiParam(value = "字典类型Id组") String ids) {
        return dictTypeService.deleteDictTypeByIds(ids);
    }

    /**
     * 刷新字典缓存
     */
    @ApiOperation(value = "刷新字典缓存")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @GetMapping("/refreshCache")
    public BaseResponse refreshCache() {
        dictTypeService.resetDictCache();
        return ResultUtils.success("刷新字典缓存成功");
    }

    /**
     * 查询字典详细
     */
    @ApiOperation(value = "字典类型详情")
    @GetMapping("/detail/{dictId}")
    public BaseResponse detail(@PathVariable("dictId") @ApiParam(value = "字典类型Id") Long dictId) {
        return dictTypeService.selectDictTypeById(dictId);
    }

    /**
     * 校验字典类型
     */
    @ApiOperation(value = "校验字典类型")
    @PostMapping("/checkDictTypeUnique")
    public BaseResponse checkDictTypeUnique(@RequestBody SysDictTypeEntity sysDictTypeEntity) {
        String typeUnique = dictTypeService.checkDictTypeUnique(sysDictTypeEntity);
        return ResultUtils.success(typeUnique, "校验字典类型成功");
    }

    /**
     * 选择字典树
     */
    @ApiOperation(value = "选择字典树")
    @GetMapping("/selectDictTree/{dictType}")
    public BaseResponse selectDeptTree(@PathVariable("dictType") @ApiParam(value = "字典类型") String dictType) {
        return dictTypeService.selectDictTypeByType(dictType);
    }

    /**
     * 加载字典列表树
     */
    @ApiOperation(value = "加载字典列表树")
    @GetMapping("/treeData")
    public BaseResponse treeData() {
        List<Ztree> ztrees = dictTypeService.selectDictTree(new SysDictTypeEntity());
        return ResultUtils.success(ztrees, "加载字典列表树成功");
    }
}
