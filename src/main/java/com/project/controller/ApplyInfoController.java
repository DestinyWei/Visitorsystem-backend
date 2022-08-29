package com.project.controller;

import com.project.common.BaseResponse;
import com.project.model.dto.ApplyInfoDto;
import com.project.model.entity.ApplyInfoEntity;
import com.project.service.ApplyInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 访问申请接口
 * @author: weihaoming
 * @create: 2022-08-25-17:54
 * @version:
 */
@RestController
@RequestMapping("/apply")
public class ApplyInfoController {

    @Resource
    private ApplyInfoService applyInfoService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody ApplyInfoEntity applyInfoEntity, HttpServletRequest request){
        return applyInfoService.insert(applyInfoEntity, request);
    }

    @PostMapping("/remove")
    public BaseResponse remove(@RequestBody ApplyInfoEntity applyInfoEntity){
        return applyInfoService.remove(applyInfoEntity);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody ApplyInfoEntity applyInfoEntity){
        return applyInfoService.update(applyInfoEntity);
    }

    @PostMapping("/search")
    public BaseResponse search(@RequestBody ApplyInfoDto applyInfoDto){
        return applyInfoService.search(applyInfoDto);
    }
}
