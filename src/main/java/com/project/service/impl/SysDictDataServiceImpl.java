package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.mapper.SysDictDataMapper;
import com.project.model.dto.SysDictDataDto;
import com.project.model.entity.SysDictDataEntity;
import com.project.service.SysDictDataService;
import com.project.util.DictUtils;
import com.project.util.ResultUtils;
import com.project.util.text.Convert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典 业务层处理
 */
@Service
public class SysDictDataServiceImpl implements SysDictDataService {
    @Resource
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典数据
     * 
     * @param sysDictDataDto 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public BaseResponse selectDictDataList(SysDictDataDto sysDictDataDto) {
        IPage<SysDictDataEntity> page = dictDataMapper.selectDictDataPage(sysDictDataDto);
        return ResultUtils.success(page, "查询成功");
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public BaseResponse selectDictLabel(String dictType, String dictValue) {
        String dictLabel = dictDataMapper.selectDictLabel(dictType, dictValue);
        return ResultUtils.success(dictLabel, "根据字典类型和字典键值查询字典数据信息成功");
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public BaseResponse selectDictDataById(Long dictCode) {
        SysDictDataEntity sysDictDataEntity = dictDataMapper.selectDictDataById(dictCode);
        return ResultUtils.success(sysDictDataEntity, "根据字典数据ID查询信息成功");
    }

    /**
     * 批量删除字典数据
     * 
     * @param ids 需要删除的数据
     */
    @Override
    @Transactional
    public BaseResponse deleteDictDataByIds(String ids) {
        Long[] dictCodes = Convert.toLongArray(ids);
        for (Long dictCode : dictCodes) {
            SysDictDataEntity data = dictDataMapper.selectDictDataById(dictCode);
            dictDataMapper.deleteDictDataById(dictCode);
            List<SysDictDataEntity> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return ResultUtils.success(ids,"删除成功");
    }

    /**
     * 新增保存字典数据信息
     * 
     * @param sysDictDataEntity 字典数据信息
     * @return 结果
     */
    @Override
    @Transactional
    public BaseResponse insertDictData(SysDictDataEntity sysDictDataEntity) {
        int row = dictDataMapper.insert(sysDictDataEntity);
        if (row > 0) {
            List<SysDictDataEntity> dictDatas = dictDataMapper.selectDictDataByType(sysDictDataEntity.getDictType());
            DictUtils.setDictCache(sysDictDataEntity.getDictType(), dictDatas);
        }
        return ResultUtils.success(sysDictDataEntity.getDictCode(), "新增成功");
    }

    /**
     * 修改保存字典数据信息
     * 
     * @param sysDictDataEntity 字典数据信息
     * @return 结果
     */
    @Override
    @Transactional
    public BaseResponse updateDictData(SysDictDataEntity sysDictDataEntity) {
        if (sysDictDataEntity.getDictCode() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int row = dictDataMapper.updateById(sysDictDataEntity);
        if (row > 0) {
            List<SysDictDataEntity> dictDatas = dictDataMapper.selectDictDataByType(sysDictDataEntity.getDictType());
            DictUtils.setDictCache(sysDictDataEntity.getDictType(), dictDatas);
        }
        return ResultUtils.success(sysDictDataEntity.getDictCode(), "修改成功");
    }
}
