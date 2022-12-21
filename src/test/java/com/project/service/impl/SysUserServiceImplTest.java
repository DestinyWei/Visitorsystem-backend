package com.project.service.impl;

import com.project.model.dto.SysUserDto;
import com.project.model.entity.SysUserEntity;
import com.project.model.request.SysUserLoginRequest;
import com.project.model.request.SysUserRegisterRequest;
import com.project.model.request.SysUserUpdatePwdRequest;
import com.project.service.SysUserService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.project.constant.SysUserConstants.USER_LOGIN_STATE;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@Rollback
class SysUserServiceImplTest {

    @Resource
    private SysUserService sysUserService;

    @Resource
    HttpServletRequest request;

    private static final SysUserEntity user = new SysUserEntity();

    private static final String SALT = "weihaoming";

    @BeforeAll
    public static void setParams() {
        //测试前注意修改account
        user.setUserId(13L);
        user.setUserName("Pymjl");
        user.setUserStatus("0");
        user.setPhone("11111111111");
        user.setType("0");
        user.setSex("男");
        user.setUserPassword("123456");
        user.setUserAccount("Pymjl");
        user.setIsDelete(0);
    }

    @Test
    @Order(1)
    void userRegister() {
        SysUserRegisterRequest sysUserRegisterRequest = new SysUserRegisterRequest();
        sysUserRegisterRequest.setUserAccount(user.getUserAccount());
        sysUserRegisterRequest.setUserPassword(user.getUserPassword());
        sysUserRegisterRequest.setCheckPassword(user.getUserPassword());

        System.out.println(sysUserService.userRegister(sysUserRegisterRequest));

    }

    @Test
    @Order(1)
    void userLogin() {
        SysUserLoginRequest sysUserLoginRequest = new SysUserLoginRequest();
        sysUserLoginRequest.setUserAccount(user.getUserAccount());
        sysUserLoginRequest.setUserPassword(user.getUserPassword());

        System.out.println(sysUserService.userLogin(sysUserLoginRequest, request));

        System.out.println("登陆成功==》"+request.getSession().getAttribute(USER_LOGIN_STATE));
    }

    @Test
    @Order(3)
    void getSafetyUser() {
        System.out.println(sysUserService.getSafetyUser(user));
    }

    @Test
    @Order(4)
    void userLogout() {
        sysUserService.userLogout(request);
        Assertions.assertNull(request.getSession().getAttribute(USER_LOGIN_STATE));
    }

    @Test
    @Order(5)
    void update() {
        System.out.println(sysUserService.update(user));
    }

    @Test
    @Order(6)
    void search() {
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setCurrentUserId(6L);
        System.out.println(sysUserService.search(sysUserDto));
    }

    @Test
    @Order(2)
    void detail() {
        System.out.println(sysUserService.detail(user.getUserId()));
    }
}