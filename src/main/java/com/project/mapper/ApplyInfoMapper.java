package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.ApplyInfoDto;
import com.project.model.entity.ApplyInfoEntity;

import java.util.List;

/**
* @author Destiny
* @description 针对表【apply_info】的数据库操作Mapper
* @createDate 2022-08-29 12:43:45
* @Entity generator.domain.ApplyInfo
*/
public interface ApplyInfoMapper extends BaseMapper<ApplyInfoEntity> {

    /**
     * 分页查询访问申请
     * @param applyInfoDto
     * @return IPage<ApplyInfoEntity>
     */
    IPage<ApplyInfoEntity> selectApplyInfo(ApplyInfoDto applyInfoDto);

    /**
     * 根据Id查询访问申请
     * @param applyId
     * @return ApplyInfoEntity
     */
    ApplyInfoEntity selectApplyInfoById(Long applyId);

    /**
     * 分组查询
     * @param applyInfoDto
     * @return List<ApplyInfoEntity>
     */
    List<ApplyInfoEntity> selectApplyInfoList(ApplyInfoDto applyInfoDto);

    /**
     * 批量删除访问申请
     * @param ids
     * @return int
     */
    int deleteApplyInfoByIds(Long[] ids);
}




