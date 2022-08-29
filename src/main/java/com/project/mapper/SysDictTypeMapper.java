package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.model.entity.SysDictTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典表 数据层
 *
 * @author smalljop
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictTypeEntity> {


    List<SysDictTypeEntity> allDict();
}
