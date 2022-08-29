package com.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.model.dto.CompanyInfoDto;
import com.project.model.entity.CompanyInfoEntity;
import com.project.service.CompanyInfoService;
import com.project.mapper.CompanyInfoMapper;
import com.project.util.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author Destiny
* @description 针对表【company_info】的数据库操作Service实现
* @createDate 2022-08-29 12:00:19
*/
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfoEntity>
    implements CompanyInfoService{

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Override
    public BaseResponse insert(CompanyInfoEntity companyInfoEntity) {
        companyInfoEntity.setCreateTime(new Date());
        int insert = companyInfoMapper.insert(companyInfoEntity);
        if (insert == 0){
            return ResultUtils.error(ErrorCode.SAVE_ERROR, "新增失败");
        }
        return ResultUtils.success(companyInfoEntity.getId(), "新增成功");
    }

    @Override
    public BaseResponse remove(CompanyInfoEntity companyInfoEntity) {
        if (companyInfoEntity.getId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int delete = companyInfoMapper.deleteById(companyInfoEntity.getId());
        if (delete == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败");
        }
        return ResultUtils.success(companyInfoEntity.getId(), "删除失败");
    }

    @Override
    public BaseResponse update(CompanyInfoEntity companyInfoEntity) {
        if (companyInfoEntity.getId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int update = companyInfoMapper.updateById(companyInfoEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改失败");
        }
        return ResultUtils.success(companyInfoEntity.getId(), "修改成功");
    }

    @Override
    public BaseResponse search(CompanyInfoDto companyInfoDto) {
        IPage<CompanyInfoEntity> page = companyInfoMapper.selectCompany(companyInfoDto);
        return ResultUtils.success(page, "查询成功");
    }
}




