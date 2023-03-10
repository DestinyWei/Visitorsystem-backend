package com.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.common.BaseResponse;
import com.project.model.dto.SysDeptDto;
import com.project.model.entity.SysDeptEntity;

import javax.servlet.http.HttpServletRequest;

/**
* @author weihaoming
* @description 针对表【sys_dept】的数据库操作Service
* @createDate 2022-08-26 15:55:39
*/
public interface SysDeptService extends IService<SysDeptEntity> {

    /**
     * 部门新增
     * @param sysDeptEntity
     * @return BaseResponse
     */
    BaseResponse insert(SysDeptEntity sysDeptEntity, HttpServletRequest request);

    /**
     * 部门删除
     * @param deptId
     * @return BaseResponse
     */
    BaseResponse remove(Long deptId);

    /**
     * 部门修改
     * @param sysDeptEntity
     * @return BaseResponse
     */
    BaseResponse update(SysDeptEntity sysDeptEntity);

    /**
     * 分页查询部门
     * @param sysDeptDto
     * @return BaseResponse
     */
    BaseResponse search(SysDeptDto sysDeptDto);

    /**
     * 部门详情
     * @param deptId
     * @return BaseResponse
     */
    BaseResponse detail(Long deptId);
}
