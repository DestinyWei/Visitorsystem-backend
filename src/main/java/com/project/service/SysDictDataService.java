package com.project.service;

import com.project.common.BaseResponse;
import com.project.model.dto.SysDictDataDto;
import com.project.model.entity.SysDictDataEntity;

/**
 * 字典 业务层
 */
public interface SysDictDataService
{
    /**
     * 根据条件分页查询字典数据
     * 
     * @param sysDictDataDto 字典数据信息
     * @return 字典数据集合信息
     */
    BaseResponse selectDictDataList(SysDictDataDto sysDictDataDto);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    BaseResponse selectDictLabel(String dictType, String dictValue);

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    BaseResponse selectDictDataById(Long dictCode);

    /**
     * 批量删除字典数据
     * 
     * @param ids 需要删除的数据
     */
    BaseResponse deleteDictDataByIds(String ids);

    /**
     * 新增保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    BaseResponse insertDictData(SysDictDataEntity dictData);

    /**
     * 修改保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    BaseResponse updateDictData(SysDictDataEntity dictData);
}
