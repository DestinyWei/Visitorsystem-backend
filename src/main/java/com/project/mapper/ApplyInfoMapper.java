package com.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.model.dto.ApplyInfoDto;
import com.project.model.entity.ApplyInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Destiny
* @description 针对表【apply_info】的数据库操作Mapper
* @createDate 2022-08-29 12:43:45
* @Entity generator.domain.ApplyInfo
*/
public interface ApplyInfoMapper extends BaseMapper<ApplyInfoEntity> {

    IPage<ApplyInfoEntity> selectApplyInfo(ApplyInfoDto applyInfoDto);

    ApplyInfoEntity selectApplyInfoById(Long applyId);
}




