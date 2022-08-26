package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.SysRoleDto;
import com.project.model.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Destiny
* @description 针对表【sys_role】的数据库操作Mapper
* @createDate 2022-08-25 17:41:14
* @Entity generator.domain.SysRole
*/
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
    IPage<SysRoleEntity> selectRole(SysRoleDto sysRoleDto);
}




