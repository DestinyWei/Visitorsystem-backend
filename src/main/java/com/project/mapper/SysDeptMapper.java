package com.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.SysDeptDto;
import com.project.model.entity.SysDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Destiny
* @description 针对表【sys_dept】的数据库操作Mapper
* @createDate 2022-08-26 15:55:39
* @Entity generator.domain.SysDept
*/
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {
    IPage<SysDeptEntity> selectDepts(SysDeptDto sysDeptDto);
}




