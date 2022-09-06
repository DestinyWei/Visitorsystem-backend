package com.project.service;


import com.project.common.BaseResponse;
import com.project.model.dto.SysDictTypeDto;
import com.project.model.entity.SysDictTypeEntity;
import com.project.model.entity.Ztree;

import java.util.List;

/**
 * 字典 业务层
 * 
 * @author ruoyi
 */
public interface SysDictTypeService {
    /**
     * 根据条件分页查询字典类型
     * 
     * @param sysDictTypeDto 字典类型信息
     * @return 字典类型集合信息
     */
    BaseResponse selectDictTypeList(SysDictTypeDto sysDictTypeDto);

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    BaseResponse selectDictTypeAll();

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    BaseResponse selectDictDataByType(String dictType);

    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    BaseResponse selectDictTypeById(Long dictId);

    /**
     * 根据字典类型查询信息
     * 
     * @param dictType 字典类型
     * @return 字典类型
     */
    BaseResponse selectDictTypeByType(String dictType);

    /**
     * 批量删除字典类型
     * 
     * @param ids 需要删除的数据
     */
    BaseResponse deleteDictTypeByIds(String ids);

    /**
     * 加载字典缓存数据
     */
    void loadingDictCache();

    /**
     * 清空字典缓存数据
     */
    void clearDictCache();

    /**
     * 重置字典缓存数据
     */
    void resetDictCache();

    /**
     * 新增保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    BaseResponse insertDictType(SysDictTypeEntity dictType);

    /**
     * 修改保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    BaseResponse updateDictType(SysDictTypeEntity dictType);

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    String checkDictTypeUnique(SysDictTypeEntity dictType);

    /**
     * 查询字典类型树
     * 
     * @param dictType 字典类型
     * @return 所有字典类型
     */
    List<Ztree> selectDictTree(SysDictTypeEntity dictType);
}
