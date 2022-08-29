package com.project.controller;

import com.project.common.BaseResponse;
import com.project.model.dto.CompanyInfoDto;
import com.project.model.entity.CompanyInfoEntity;
import com.project.service.CompanyInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 公司信息接口
 * @author: weihaoming
 * @create: 2022-08-25-17:54
 * @version:
 */
@RestController
@RequestMapping("/company")
public class CompanyInfoController {

    @Resource
    private CompanyInfoService companyInfoService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody CompanyInfoEntity companyInfoEntity){
        return companyInfoService.insert(companyInfoEntity);
    }

    @PostMapping("/remove")
    public BaseResponse remove(@RequestBody CompanyInfoEntity companyInfoEntity){
        return companyInfoService.remove(companyInfoEntity);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody CompanyInfoEntity companyInfoEntity){
        return companyInfoService.update(companyInfoEntity);
    }

    @PostMapping("/search")
    public BaseResponse search(@RequestBody CompanyInfoDto companyInfoDto){
        return companyInfoService.search(companyInfoDto);
    }
}
