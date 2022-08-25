package com.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.SysUserDto;
import com.project.model.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    IPage<SysUserEntity> searchUser(SysUserDto sysUserDto);
}




