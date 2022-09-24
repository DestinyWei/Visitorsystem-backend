package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.exception.BusinessException;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        applyInfoEntity.setCreateTime(new Date());
        applyInfoEntity.setApplicantId(loginUser.getUserId());
        applyInfoEntity.setApplyStatus("0");
        this.insertInfo(applyInfoEntity);
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
    public BaseResponse removes(Long[] applyIds) {
        int deletes = applyInfoMapper.deleteBatchIds(Arrays.stream(applyIds).collect(Collectors.toList()));
        if (deletes == 0){
            return ResultUtils.error(ErrorCode.DELETE_ERROR, "删除失败,该访问申请不存在或已被删除");
        }
        return ResultUtils.success(deletes, "批量删除成功");
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

    @Override
    public BaseResponse collect(ApplyInfoDto applyInfoDto) {
        // 获得分组情况
        List<ApplyInfoEntity> applyInfoEntities = applyInfoMapper.selectApplyInfoList(applyInfoDto);
        for (ApplyInfoEntity applyInfoEntity : applyInfoEntities) {
            // 获取相同分组下的所有访问申请信息
            String ids = applyInfoEntity.getIds();
            List<String> id = Arrays.stream(ids.split(",")).collect(Collectors.toList());
            List<ApplyInfoEntity> applyInfoList = applyInfoMapper.selectBatchIds(id);
            for (ApplyInfoEntity infoEntity : applyInfoList) {
                // 添加具体字段信息
                this.insertInfo(infoEntity);
            }
            applyInfoEntity.setList(applyInfoList);
        }
        return ResultUtils.success(applyInfoEntities, "分组查询成功");
    }

    private ApplyInfoEntity insertInfo(ApplyInfoEntity applyInfoEntity){
        SysDeptEntity dept = sysDeptMapper.selectById(applyInfoEntity.getDeptId());
        if (dept == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "部门不存在");
        }
        CompanyInfoEntity companyInfo = companyInfoMapper.selectById(applyInfoEntity.getCompanyId());
        if (companyInfo == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "公司不存在");
        }
        SysUserEntity principal = sysUserMapper.selectById(applyInfoEntity.getPrincipalId());
        if (principal == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在");
        }
        SysUserEntity applicant = sysUserMapper.selectById(applyInfoEntity.getApplicantId());
        if (applicant == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在");
        }
        applyInfoEntity.setApplicantName(applicant.getUserName());
        applyInfoEntity.setPrincipalName(principal.getUserName());
        applyInfoEntity.setDeptName(dept.getDeptName());
        applyInfoEntity.setCompanyName(companyInfo.getCompanyName());
        return applyInfoEntity;
    }
}




