package com.project.service;

import com.project.common.BaseResponse;
import com.project.model.dto.SysResourceDto;
import com.project.model.entity.SysResourceEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author Destiny
* @description 针对表【sys_resource】的数据库操作Service
* @createDate 2022-08-26 16:35:53
*/
public interface SysResourceService extends IService<SysResourceEntity> {

    /**
     * 系统资源添加
     * @param sysResourceEntity
     * @return BaseResponse
     */
    BaseResponse insert(SysResourceEntity sysResourceEntity, HttpServletRequest request);

    /**
     * 系统资源修改
     * @param sysResourceEntity
     * @return BaseResponse
     */
    BaseResponse remove(SysResourceEntity sysResourceEntity);

    /**
     * 系统资源修改
     * @param sysResourceEntity
     * @return BaseResponse
     */
    BaseResponse update(SysResourceEntity sysResourceEntity);

    /**
     * 系统资源查询
     * @param sysResourceDto
     * @return BaseResponse
     */
    BaseResponse search(SysResourceDto sysResourceDto);
}
