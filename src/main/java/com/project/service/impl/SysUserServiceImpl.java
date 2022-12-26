package com.project.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.BaseResponse;
import com.project.common.ErrorCode;
import com.project.exception.BusinessException;
import com.project.mapper.SysUserMapper;
import com.project.mapper.SysUserRoleMapper;
import com.project.model.dto.SysUserDto;
import com.project.model.entity.SysUserEntity;
import com.project.model.entity.SysUserRoleEntity;
import com.project.model.request.SysUserLoginRequest;
import com.project.model.request.SysUserRegisterRequest;
import com.project.model.request.SysUserUpdatePwdRequest;
import com.project.service.SysUserService;
import com.project.util.JwtUtils;
import com.project.util.ResultUtils;
import com.project.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.project.constant.SysUserConstants.USER_LOGIN_STATE;

/**
 * 用户服务实现类
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity>
        implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "weihaoming";

    @Override
    public BaseResponse userRegister(SysUserRegisterRequest sysUserRegisterRequest) {
        String userAccount = sysUserRegisterRequest.getUserAccount();
        String userPassword = sysUserRegisterRequest.getUserPassword();
        String checkPassword = sysUserRegisterRequest.getCheckPassword();
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < 6 || checkPassword.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户含非法字符");
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }
        // 账户不能重复
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        Long count = sysUserMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 3. 插入数据
        SysUserEntity user = new SysUserEntity();
        user.setUserName(sysUserRegisterRequest.getUserName());
        user.setSex(sysUserRegisterRequest.getSex());
        user.setIdNumber(sysUserRegisterRequest.getIdNumber());
        user.setPhone(sysUserRegisterRequest.getPhone());
        user.setEmail(sysUserRegisterRequest.getEmail());
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setType("0");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setUserStatus("0");
        user.setIsDelete(0);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SAVE_ERROR, "未注册成功");
        }
        // 添加普通用户角色
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setUserId(user.getUserId());
        sysUserRoleEntity.setRoleId(2L);
        sysUserRoleMapper.insert(sysUserRoleEntity);
        return ResultUtils.success(user.getUserId(), "用户注册成功");
    }

    @Override
    public BaseResponse userLogin(SysUserLoginRequest sysUserLoginRequestRequest, HttpServletRequest request) {
        String userAccount = sysUserLoginRequestRequest.getUserAccount();
        String userPassword = sysUserLoginRequestRequest.getUserPassword();
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "账户/密码为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户长度小于4");
        }
        if (userPassword.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度小于6");
        }
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户含非法字符");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount)
                    .eq("user_password", encryptPassword);
        SysUserEntity user = sysUserMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在,请检查账号密码是否正确");
        }
        // 3. 用户脱敏
        SysUserEntity safetyUser = getSafetyUser(user);
        // 4. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        JSONObject json = new JSONObject();
        // String token = JwtUtils.tokenPrefix + JwtUtils.createToken(safetyUser.getUserId().toString()); // 规范格式
        String token = JwtUtils.createToken(safetyUser.getUserId().toString());
        safetyUser.setToken(token);
        if (!StringUtils.isEmpty(token)) {
            json.put("token", token) ;
        }
        log.info("Token为:" + token);
        Cookie cookie = new Cookie(safetyUser.getUserId().toString(), request.getSession().toString());
        safetyUser.setCookie(cookie);
        return ResultUtils.success(safetyUser, "用户登录成功");
    }

    /**
     * 用户脱敏
     *
     * @param originUser 未脱敏用户对象
     */
    @Override
    public SysUserEntity getSafetyUser(SysUserEntity originUser) {
        if (originUser == null) {
            return null;
        }
        SysUserEntity safetyUser = new SysUserEntity();
        safetyUser.setUserId(originUser.getUserId());
        safetyUser.setUserName(originUser.getUserName());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setSex(originUser.getSex());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setType(originUser.getType());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        return safetyUser;
    }

    /**
     * 用户注销
     *
     * @param request
     */
    @Override
    public BaseResponse userLogout(HttpServletRequest request) {
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return ResultUtils.success(1, "登出成功");
    }

    @Override
    @Transactional
    public BaseResponse update(SysUserEntity sysUserEntity) {
        if (sysUserEntity.getUserId() == null){
            return ResultUtils.error(ErrorCode.NULL_ERROR, "Id为空");
        }
        int update = sysUserMapper.updateById(sysUserEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改失败,该用户不存在或已被删除");
        }
        return null;
    }


    @Override
    public BaseResponse search(SysUserDto sysUserDto) {
        QueryWrapper<SysUserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", sysUserDto.getCurrentUserId()).orderBy(true, true, "role_id");
        Long roleId = sysUserRoleMapper.selectOne(queryWrapper).getRoleId();
        sysUserDto.setRoleType(roleId.toString());
        IPage<SysUserEntity> page = sysUserMapper.searchUser(sysUserDto);
        return ResultUtils.success(page, "查询成功");
    }

    @Override
    @Transactional
    public BaseResponse updatePwd(SysUserUpdatePwdRequest sysUserUpdatePwdRequest, HttpServletRequest request) {
        SysUserEntity loginUser = SecurityUtils.getLoginUser(request);
        String userRawPassword = loginUser.getUserPassword();
        Long userId = loginUser.getUserId();
        if (!userRawPassword.equals(sysUserUpdatePwdRequest.getRawPassword())){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "密码校验不正确");
        }
        if (!sysUserUpdatePwdRequest.getUserPassword().equals(sysUserUpdatePwdRequest.getCheckPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + sysUserUpdatePwdRequest.getUserPassword()).getBytes());
        SysUserEntity sysUserEntity = sysUserMapper.selectById(userId);
        sysUserEntity.setUserPassword(encryptPassword);
        int update = sysUserMapper.updateById(sysUserEntity);
        if (update == 0){
            return ResultUtils.error(ErrorCode.UPDATE_ERROR, "修改密码失败");
        }
        return ResultUtils.success("修改密码成功");
    }

    @Override
    public BaseResponse detail(Long userId) {
        SysUserEntity sysUserEntity = sysUserMapper.selectById(userId);
        return ResultUtils.success(sysUserEntity, "查询用户详情成功");
    }


}