package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.SysDictTypeDto;
import com.project.model.entity.SysDictTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典表 数据层
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictTypeEntity> {
    /**
     * 根据条件分页查询字典类型
     * 
     * @param sysDictTypeDto 字典类型信息
     * @return 字典类型集合信息
     */
    IPage<SysDictTypeEntity> selectDictTypePage(SysDictTypeDto sysDictTypeDto);

    /**
     * 根据条件查询字典类型
     *
     * @param sysDictTypeEntity 字典类型信息
     * @return 字典类型集合信息
     */
    List<SysDictTypeEntity> selectDictTypeList(SysDictTypeEntity sysDictTypeEntity);

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    List<SysDictTypeEntity> selectDictTypeAll();

    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    SysDictTypeEntity selectDictTypeById(Long dictId);

    /**
     * 根据字典类型查询信息
     * 
     * @param dictType 字典类型
     * @return 字典类型
     */
    SysDictTypeEntity selectDictTypeByType(String dictType);

    /**
     * 通过字典ID删除字典信息
     * 
     * @param dictId 字典ID
     * @return 结果
     */
    int deleteDictTypeById(Long dictId);

    /**
     * 批量删除字典类型
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteDictTypeByIds(Long[] ids);

    /**
     * 新增字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    int insertDictType(SysDictTypeEntity dictType);

    /**
     * 修改字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    int updateDictType(SysDictTypeEntity dictType);

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    SysDictTypeEntity checkDictTypeUnique(String dictType);
}
