package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.mapper.ApplyInfoMapper;
import com.project.mapper.CompanyInfoMapper;
import com.project.mapper.SysDeptMapper;
import com.project.mapper.SysUserMapper;
import com.project.model.dto.ApplyInfoDto;
import com.project.model.entity.ApplyInfoEntity;
import com.project.model.entity.CompanyInfoEntity;
import com.project.model.entity.SysDeptEntity;
import com.project.model.entity.SysUserEntity;
import com.project.service.ApplyInfoService;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
* @author Destiny
* @description 针对表【apply_info】的数据库操作Service实现
* @createDate 2022-08-29 12:43:45
*/
@Service
public class ApplyInfoServiceImpl extends ServiceImpl<ApplyInfoMapper, ApplyInfoEntity>
    implements ApplyInfoService{

    @Resource
    private ApplyInfoMapper applyInfoMapper;

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public BaseResponse insert(ApplyInfoEntity applyInfoEntity, HttpServletRequest request) {
        SysUserEntity loginUser = SecurityUtils.getLoginUser(request);
        SysDeptEntity dept = sysDeptMapper.selectById(applyInfoEntity.getDeptId());
        if (dept == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "部门不存在");
        }
        CompanyInfoEntity companyInfo = companyInfoMapper.selectById(applyInfoEntity.getCompanyId());
        if (companyInfo == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "公司不存在");
        }
        SysUserEntity user = sysUserMapper.selectById(applyInfoEntity.getPrincipalId());
        if (user == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "用户不存在");
        }
        applyInfoEntity.setCreateTime(new Date());
        applyInfoEntity.setApplicantId(loginUser.getUserId());
        applyInfoEntity.setApplicantName(loginUser.getUserName());
        applyInfoEntity.setApplyStatus("0");
        applyInfoEntity.setDeptName(dept.getDeptName());
        applyInfoEntity.setCompanyName(companyInfo.getCompanyName());
        applyInfoEntity.setPrincipalName(user.getUserName());
        int insert = applyInfoMapper.insert(applyInfoEntity);
        if (insert == 0){
            return ResultUtils.error(ErrorCode.SAVE_ERROR, "新增失败");
        }
        return ResultUtils.success(applyInfoEntity.getId(), "新增成功");
    }

    @Override
    public BaseResponse remove(Long applyId) {
        if (applyId == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int delete = applyInfoMapper.deleteById(applyId);
        if (delete == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败,该访问申请不存在或已被删除");
        }
        return ResultUtils.success(applyId, "删除成功");
    }

    @Override
    public BaseResponse update(ApplyInfoEntity applyInfoEntity) {
        if (applyInfoEntity.getId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        SysDeptEntity dept = sysDeptMapper.selectById(applyInfoEntity.getDeptId());
        if (dept == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "部门不存在");
        }
        CompanyInfoEntity companyInfo = companyInfoMapper.selectById(applyInfoEntity.getCompanyId());
        if (companyInfo == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "公司不存在");
        }
        SysUserEntity user = sysUserMapper.selectById(applyInfoEntity.getPrincipalId());
        if (user == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "用户不存在");
        }
        applyInfoEntity.setDeptName(dept.getDeptName());
        applyInfoEntity.setCompanyName(companyInfo.getCompanyName());
        applyInfoEntity.setPrincipalName(user.getUserName());
        int update = applyInfoMapper.updateById(applyInfoEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改失败,该访问申请不存在或已被删除");
        }
        return ResultUtils.success(applyInfoEntity.getId(), "修改成功");
    }

    @Override
    public BaseResponse search(ApplyInfoDto applyInfoDto) {
        IPage<ApplyInfoEntity> page = applyInfoMapper.selectApplyInfo(applyInfoDto);
        return ResultUtils.success(page, "查询成功");
    }

    @Override
    public BaseResponse detail(Long applyId) {
        ApplyInfoEntity applyInfoEntity = applyInfoMapper.selectApplyInfoById(applyId);
        return ResultUtils.success(applyInfoEntity, "查询详情成功");
    }
}




