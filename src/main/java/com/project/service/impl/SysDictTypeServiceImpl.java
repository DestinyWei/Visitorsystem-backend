package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.constant.SysUserConstants;
import com.project.exception.ServiceException;
import com.project.mapper.SysDictTypeMapper;
import com.project.model.dto.SysDictTypeDto;
import com.project.model.entity.SysDictTypeEntity;
import com.project.model.entity.Ztree;
import com.project.service.SysDictTypeService;
import com.project.text.Convert;
import com.project.model.entity.SysDictDataEntity;
import com.project.mapper.SysDictDataMapper;
import com.project.util.DictUtils;
import com.project.util.ResultUtils;
import com.project.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {
    @Resource
    private SysDictTypeMapper dictTypeMapper;

    @Resource
    private SysDictDataMapper dictDataMapper;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        loadingDictCache();
    }

    /**
     * 根据条件分页查询字典类型
     * 
     * @param sysDictTypeDto 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public BaseResponse selectDictTypeList(SysDictTypeDto sysDictTypeDto) {
        IPage<SysDictTypeEntity> page = dictTypeMapper.selectDictTypePage(sysDictTypeDto);
        return ResultUtils.success(page, "分页查询成功");
    }

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    @Override
    public BaseResponse selectDictTypeAll() {
        List<SysDictTypeEntity> sysDictTypeEntities = dictTypeMapper.selectDictTypeAll();
        return ResultUtils.success(sysDictTypeEntities, "根据所有字典类型成功");
    }

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public BaseResponse selectDictDataByType(String dictType) {
        List<SysDictDataEntity> dictDatas = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return ResultUtils.success(dictDatas);
        }
        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas)) {
            DictUtils.setDictCache(dictType, dictDatas);
            return ResultUtils.success(dictDatas);
        }
        return ResultUtils.success("没有该字典数据");
    }

    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public BaseResponse selectDictTypeById(Long dictId) {
        SysDictTypeEntity sysDictTypeEntity = dictTypeMapper.selectDictTypeById(dictId);
        return ResultUtils.success(sysDictTypeEntity, "根据字典类型ID查询信息成功");
    }

    /**
     * 根据字典类型查询信息
     * 
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public BaseResponse selectDictTypeByType(String dictType) {
        SysDictTypeEntity sysDictTypeEntity = dictTypeMapper.selectDictTypeByType(dictType);
        return ResultUtils.success(sysDictTypeEntity, "根据字典类型查询信息成功");
    }

    /**
     * 批量删除字典类型
     * 
     * @param ids 需要删除的数据
     */
    @Override
    public BaseResponse deleteDictTypeByIds(String ids) {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            SysDictTypeEntity dictType = dictTypeMapper.selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
            dictTypeMapper.deleteDictTypeById(dictId);
            DictUtils.removeDictCache(dictType.getDictType());
        }
        return ResultUtils.success("批量删除字典类型成功");
    }

    /**
     * 加载字典缓存数据
     */
    @Override
    public void loadingDictCache() {
        SysDictDataEntity dictData = new SysDictDataEntity();
        dictData.setStatus("0");
        Map<String, List<SysDictDataEntity>> dictDataMap = dictDataMapper.selectDictDataList(dictData).stream().collect(Collectors.groupingBy(SysDictDataEntity::getDictType));
        for (Map.Entry<String, List<SysDictDataEntity>> entry : dictDataMap.entrySet()) {
            DictUtils.setDictCache(entry.getKey(), entry.getValue().stream().sorted(Comparator.comparing(SysDictDataEntity::getDictSort)).collect(Collectors.toList()));
        }
    }

    /**
     * 清空字典缓存数据
     */
    @Override
    public void clearDictCache() {
        DictUtils.clearDictCache();
    }

    /**
     * 重置字典缓存数据
     */
    @Override
    public void resetDictCache() {
        clearDictCache();
        loadingDictCache();
    }

    /**
     * 新增保存字典类型信息
     * 
     * @param sysDictTypeEntity 字典类型信息
     * @return 结果
     */
    @Override
    public BaseResponse insertDictType(SysDictTypeEntity sysDictTypeEntity) {
        int row = dictTypeMapper.insertDictType(sysDictTypeEntity);
        if (row > 0) {
            DictUtils.setDictCache(sysDictTypeEntity.getDictType(), null);
        }
        return ResultUtils.success(sysDictTypeEntity.getDictId(), "新增成功");
    }

    /**
     * 修改保存字典类型信息
     * 
     * @param sysDictTypeEntity 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public BaseResponse updateDictType(SysDictTypeEntity sysDictTypeEntity) {
        if (sysDictTypeEntity.getDictId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        SysDictTypeEntity oldDict = dictTypeMapper.selectDictTypeById(sysDictTypeEntity.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), sysDictTypeEntity.getDictType());
        int row = dictTypeMapper.updateDictType(sysDictTypeEntity);
        if (row > 0) {
            List<SysDictDataEntity> dictDatas = dictDataMapper.selectDictDataByType(sysDictTypeEntity.getDictType());
            DictUtils.setDictCache(sysDictTypeEntity.getDictType(), dictDatas);
        }
        return ResultUtils.success(sysDictTypeEntity.getDictId(), "修改成功");
    }

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictTypeEntity dict) {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictTypeEntity dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue()) {
            return SysUserConstants.DICT_TYPE_NOT_UNIQUE;
        }
        return SysUserConstants.DICT_TYPE_UNIQUE;
    }

    /**
     * 查询字典类型树
     * 
     * @param dictType 字典类型
     * @return 所有字典类型
     */
    @Override
    public List<Ztree> selectDictTree(SysDictTypeEntity dictType) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysDictTypeEntity> dictList = dictTypeMapper.selectDictTypeList(dictType);
        for (SysDictTypeEntity dict : dictList) {
            if (SysUserConstants.DICT_NORMAL.equals(dict.getStatus())) {
                Ztree ztree = new Ztree();
                ztree.setId(dict.getDictId());
                ztree.setName(transDictName(dict));
                ztree.setTitle(dict.getDictType());
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    public String transDictName(SysDictTypeEntity dictType) {
        StringBuffer sb = new StringBuffer();
        sb.append("(" + dictType.getDictName() + ")");
        sb.append("&nbsp;&nbsp;&nbsp;" + dictType.getDictType());
        return sb.toString();
    }
}
