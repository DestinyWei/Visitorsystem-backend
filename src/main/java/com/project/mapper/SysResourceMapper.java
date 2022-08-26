package com.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.SysResourceDto;
import com.project.model.entity.SysResourceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Destiny
* @description 针对表【sys_resource】的数据库操作Mapper
* @createDate 2022-08-26 16:35:53
* @Entity generator.domain.SysResource
*/
public interface SysResourceMapper extends BaseMapper<SysResourceEntity> {

    IPage<SysResourceEntity> selectResouces(SysResourceDto sysResourceDto);
}




